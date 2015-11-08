package Mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
public class Servidor2 {

    public static void main(String [] args) {

        Socket  s;
        PrintStream  p;


        String  host = "localhost";
        int port = 9999;
        String  respuesta;
        try {

        	//Creo una conexion al socket servidor
        	s = new Socket (host,port);

        	//Creo las referencias al canal de escritura y lectura del socket
        	p = new PrintStream (s.getOutputStream());

        	HeartBeat h=new HeartBeat(p);
        	h.run();

        	p.close();
        	s.close();

        } catch (UnknownHostException  e) {
        	System .out.println("No puedo conectarme a " + host + ":" + port);
        } catch (IOException  e) {
        	System .out.println("Error de E/S en " + host + ":" + port);
        }
    }
}
