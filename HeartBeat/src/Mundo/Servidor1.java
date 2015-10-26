package Mundo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

//Manda el heartbeat
public class Servidor1
{
	public static MulticastSocket Csock;
    public static InetAddress maddr;
    public static int port;
    
	public static void main(String[] args)
	{
		port=8888;
		try 
		{
			Csock= new MulticastSocket(port);			
			maddr = InetAddress.getByName("224.0.0.3");
			Csock.connect(maddr, port);
			HeartBeat h= new HeartBeat(Csock, maddr, port);
			System.out.println("Inicio"+maddr.getHostAddress());
			h.run();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("No crea el socket");
			e.printStackTrace();
		}
	}
	

}
