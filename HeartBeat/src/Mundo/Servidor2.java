package Mundo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

//Recibe
public class Servidor2 
{
	public static MulticastSocket Csock;
    public static InetAddress maddr;
    public static int port;
    
	public static void main(String[] args)
	{
		port=80;
		try 
		{
			Csock= new MulticastSocket(port);			
			maddr = InetAddress.getByName("127.0.0.1");
			Csock.connect(maddr, port);
			System.out.println("Inicio"+maddr.getHostAddress());
			Csock.setSoTimeout(120001);
			String n="";
			DatagramPacket hbMsg= new DatagramPacket(n.getBytes(), n.length());
			Csock.receive(hbMsg);
			if(!hbMsg.getData().toString().equals("EstoyVivo"))
			{
				System.exit(0);
			}
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("No crea el socket");
			e.printStackTrace();
		}
	}
	

}
