package net.ictcampus.rts.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * ClientController, Verbindet sich mit dem Server, sendet Befehle an den Server
 * und empf�ngt den Spielstand
 * 
 * @author Johanna
 * @version 2.0
 */
public class ClientController {

    // ---------------------------variable_declaration---------------------------//
    // socket (Client)
    private Socket socket;
    private DataOutputStream os;
    private OutputStreamWriter osw;
    private BufferedWriter bw;

    // -------------------------------Constructor--------------------------------//
    /**
     * ClientController Constructor
     * 
     * @param serverAddress
     *            IP-Adresse Server
     */
    public ClientController() {
        // Setup networking
        socket = ClientSocketFactory.createClientSocket(false);
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * sendCommand sendet beide param an server
     * 
     * @param eins
     *            int, kei Ahnig
     * @param zwei
     *            int, kei Ahnig
     * @return boolean kei Ahnig
     */
    public boolean sendCommand(int eins, int zwei) {
        try {
            // Stream �ffnen zum param senden
            socket = ClientSocketFactory.getClientSocket();
            os = new DataOutputStream(socket.getOutputStream());
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            // Variabeln werden gesammelt um anschliessend versendet zu werden
            bw.write("Hallo Welt".toCharArray());

            bw.flush();
            System.out.println(socket.getLocalPort());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
                
        return true;
    }
    
    public void getMessage() {
    	try {
    		DataInputStream dis = new DataInputStream(socket.getInputStream());
	    	InputStreamReader isr = new InputStreamReader(dis);
	    	BufferedReader br = new BufferedReader(isr);
	    	
	    	char[] cbuf = new char[10];
	    	br.read(cbuf, 0, 10);
	    	System.out.print(new String(cbuf));

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    // ------------------------------Getter_Setter------------------------------//
}
