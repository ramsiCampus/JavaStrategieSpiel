package net.ictcampus.rts.controller;

import java.util.ArrayList;
import java.util.List;

import net.ictcampus.rts.model.SpielFeld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
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
    private List<Socket> connections = new ArrayList<Socket>();

    // -------------------------------Constructor--------------------------------//
    public ServerController() {
        listener = ServerSocketFactory.createServerSocket();
        try {
        	System.out.println(listener.getLocalPort()+" is the local port.");
        	System.out.println(listener.getInetAddress().toString());
        	connections.add(listener.accept());
        	connections.add(listener.accept());
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//
    public String receiveCommands() throws IOException {
    	String strToReturn = "";
    	for(int i=0; i<connections.size(); i++) {
	    	DataInputStream dis = new DataInputStream(connections.get(i).getInputStream());
	    	InputStreamReader isr = new InputStreamReader(dis);
	    	BufferedReader br = new BufferedReader(isr);
	    	
	    	char[] cbuf = new char[30];
	    	br.read(cbuf, 0, cbuf.length);
	    	strToReturn = strToReturn + new String(cbuf).trim();
	    	if(i<connections.size()-1){
	    		strToReturn = strToReturn + "#";
	    	}
    	}
    	return strToReturn;
    }
    
    public void sendMessage(String message) throws IOException {
    	for(int i=0; i<connections.size(); i++) {
	    	DataOutputStream dos = new DataOutputStream(connections.get(i).getOutputStream());
	    	OutputStreamWriter osw = new OutputStreamWriter(dos);
	    	BufferedWriter bw = new BufferedWriter(osw);
	    	
	    	bw.write(message);
	    	bw.flush();
	    	
    	}
    	
    }
    
    public void sendGameState(SpielFeld spielFeld) throws IOException {
    	for(int i=0; i<connections.size(); i++) {
    		DataOutputStream dos = new DataOutputStream(connections.get(i).getOutputStream());
	    	ObjectOutputStream osw = new ObjectOutputStream(dos);
	    	
	    	osw.writeObject(spielFeld);
	    	osw.flush();
	    	
    	}
    	
    }

    // ------------------------------Getter_Setter------------------------------//
}
