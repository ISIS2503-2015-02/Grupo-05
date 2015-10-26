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
		port= 8888;
		try 
		{
			Csock= new MulticastSocket(port);			
			maddr = InetAddress.getByName("224.0.0.3");
			
			Csock.joinGroup(maddr);
			System.out.println("Inicio"+maddr.getHostAddress());
			Csock.setSoTimeout(120001);
			String n="EstoyVivo";
			while(true)
			{
				DatagramPacket hbMsg= new DatagramPacket(n.getBytes(), n.length());
				
				Csock.receive(hbMsg);
				
			}
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("No crea el socket");
			e.printStackTrace();
		}
	}
	

}
