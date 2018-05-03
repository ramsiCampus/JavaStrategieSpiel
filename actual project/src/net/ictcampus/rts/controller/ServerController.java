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
            try {
            	connection = listener.accept();
            	
                //new Handler(listener.accept()).start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
    	
    	
    	byte[] cbuf = new byte[10];
    	String ops = "";
    	
    	dis.readFully(cbuf, 0, 10);
    	for(int i = 0; i<cbuf.length; i++) {
    		ops = ops+((char)cbuf[i]);
    	}
    	//br.read(charbuffer, 0, 1/*charbuffer.length*/);
    	System.out.print(ops);
    }

    // ------------------------------Getter_Setter------------------------------//
}
