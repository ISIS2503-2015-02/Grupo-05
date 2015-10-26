package Mundo;

import java.net.*;
import java.io.*;

public class HeartBeat extends Thread
{
	// sends a heartbeat message to the multicast group every 120 seconds
	
    public HeartBeat (MulticastSocket Csock, InetAddress maddr,int port)
    { 
        this.Csock = Csock;
        this.maddr= maddr;
        this.port = port;
    }
    

    public static MulticastSocket Csock;
    public static InetAddress maddr;
    public static int port;
    private DatagramPacket hbMsg ;
    static private long TmHB = 120000;  //heartbeat frequency in milliseconds
    
    public void run(){
        // setup the hb datagram packet then run forever
        // setup the line to ignore the loopback we want to get it too
        String line = "5|";
        
        hbMsg = new DatagramPacket(line.getBytes(),
                                   line.length(),
                                   maddr,
                                   port);
                                  
                                  
        // continually loop and send this packet every TmHB seconds
        while (true)
        {
            try
            {
                Csock.send(hbMsg);
                sleep(TmHB);
              }
            catch (IOException e){System.err.println("Server can't send heartbeat");
                                  System.exit(-1);
            }
            catch (InterruptedException e){}
        }
    }// end run
    

   
}// end class