package Mundo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {


    public static void main(String [] args) {

        ServerSocket  s; //Socket servidor
        Socket  sc; //Socket cliente

        BufferedReader  b; //Canal de Lectura

        String  mensaje;

        try {
            //Creo el socket server
            s = new ServerSocket (9999);

            //Invoco el metodo accept del socket servidor, me devuelve una referencia al socket cliente
            sc = s.accept();

            //Obtengo una referencia a los canales de escritura y lectura del socket cliente
            b = new BufferedReader ( new InputStreamReader  ( sc.getInputStream() ) );

            while ( true ) {
                //Leo lo que escribio el socket cliente en el canal de lectura
                mensaje = b.readLine();
                System .out.println(mensaje);
                if(5<2)
                {
                	break;
                }
            }
            

            b.close();

            sc.close();
            s.close();
        } catch (IOException  e) {
            System .out.println("ALERTA!!!!!!!!");
        }
    }

}