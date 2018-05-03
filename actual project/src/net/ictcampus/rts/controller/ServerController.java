package net.ictcampus.rts.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *ServerController, öffnet Serversocket...
 * 
 * @author Johanna
 * @version 1.0
 */
public class ServerController {
    // ---------------------------variable_declaration---------------------------//
    private ServerSocket listener;
    private Socket connection;

    // -------------------------------Constructor--------------------------------//
    public ServerController() {
        listener = ServerSocketFactory.createServerSocket();
        try {
            while(true) {
                try {
                	connection = listener.accept();
                	
                    //new Handler(listener.accept()).start();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }finally {
            ServerSocketFactory.closeServerSocket(listener);
        }
    }
    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//
    public void printCommand() throws IOException{
    	DataInputStream dis = new DataInputStream(connection.getInputStream());
    	InputStreamReader isr = new InputStreamReader(dis);
    	BufferedReader br = new BufferedReader(isr);
    	
    	System.out.print(br.read());
    }

    // ------------------------------Getter_Setter------------------------------//
}
