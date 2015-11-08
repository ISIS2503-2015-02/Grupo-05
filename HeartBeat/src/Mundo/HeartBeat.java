package Mundo;

import java.net.*;
import java.io.*;

public class HeartBeat extends Thread
{
	// sends a heartbeat message to the multicast group every 120 seconds
	
    public HeartBeat (PrintStream p)
    { 
       this.p=p;
    }
    

    public static PrintStream p;
    static private long TmHB = 120000;  //heartbeat frequency in milliseconds
    
    public void run(){
        // setup the hb datagram packet then run forever
        // setup the line to ignore the loopback we want to get it too
        String line = "EstoyVivo";
                                  
                                  
        // continually loop and send this packet every TmHB seconds
        while (true)
        {
            try
            {
                p.println(line);
                sleep(TmHB);
             }
            catch (InterruptedException e){}
        }
    }// end run
    

   
}// end class