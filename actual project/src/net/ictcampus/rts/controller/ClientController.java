package net.ictcampus.rts.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import net.ictcampus.rts.model.Player;
import net.ictcampus.rts.model.Spiel;
import net.ictcampus.rts.model.SpielFeld;

/**
 * ClientController, Verbindet sich mit dem Server, sendet Befehle an den Server
 * und empfängt den Spielstand
 * 
 * @author Johanna
 * @version 2.0
 */
public class ClientController extends Thread{

    // ---------------------------variable_declaration---------------------------//
    // socket (Client)
    private Socket socket;
    private DataOutputStream os;
    private OutputStreamWriter osw;
    private BufferedWriter bw;
    
    private static boolean clientIsReady;
    private static boolean gameIsReady;
    private static String message;
    private static String cmd;
    private static Spiel netzSpiel;
    private boolean done = false;
    private Player player;

    // -------------------------------Constructor--------------------------------//
    /**
     * ClientController Constructor
     * 
     * @param serverAddress
     *            IP-Adresse Server
     */
    public ClientController(String name) {
        // Setup networking
        socket = ClientSocketFactory.createClientSocket();
        clientIsReady = true;
        message = this.getMessageFromServer();
        player = new Player(name, Integer.parseInt(message));
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * 
     * @param playerID
     * @param action
     * @param xCoord
     * @param yCoord
     * @param anzahl
     * @return Ob die Funktion erfolgreich geendet hat oder nicht
     */
    public boolean sendCommand(String command) {
        try {
            // Stream öffnen zum param senden
            socket = ClientSocketFactory.getClientSocket();
            os = new DataOutputStream(socket.getOutputStream());
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            // Variabeln werden gesammelt um anschliessend versendet zu werden
            // deprecated: String condensedCommand = ""+playerID+":"+action+":"+xCoord+":"+yCoord+":"+anzahl;
            bw.write(command);

            bw.flush();
            System.out.println(socket.getLocalPort());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
                
        return true;
    }
    
    /**
     * Empfängt eine Nachricht (was nur eine Zahl ist, siehe Protokoll) vom Server
     * @return die Nachricht
     */
    public String getMessageFromServer() {
        String msg = "";
    	try {
    		DataInputStream dis = new DataInputStream(socket.getInputStream());
	    	InputStreamReader isr = new InputStreamReader(dis);
	    	BufferedReader br = new BufferedReader(isr);
	    	
	    	char[] cbuf = new char[10];
	    	br.read(cbuf, 0, 10);
	    	msg = new String(cbuf);
	    	System.out.print(message);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return msg;
    }
    
    
    public Spiel getGameStateFromServer() {
        Spiel uebertragenesSpiel = null;    
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            ObjectInputStream istream = new ObjectInputStream(dis);
            
            uebertragenesSpiel = (Spiel)istream.readObject();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return uebertragenesSpiel;
        
    }
    
    /**
     * empfängt messages vom Server und vollführt die entsprechende Aktiion des Protokolls
     */
    public void run() {
        
        while(!done) {
            message = getMessageFromServer();
            
            if(message.equals("0")) {
                if(this.clientIsReady) {
                    String response = Integer.toString(player.getID())+",0,0,0,0,0,0";
                    gameIsReady = false;
                    this.sendCommand(response);
                } else {
                    String response = Integer.toString(player.getID())+",-1,0,0,0,0,0";
                    this.sendCommand(response);
                }
            }
            else if(message.equals("1")) {
                this.sendCommand(cmd);
                this.clientIsReady = false;
            }
            else if(message.equals("2")) {
                this.netzSpiel = getGameStateFromServer();
                gameIsReady = true;
            }
            
        }
    }
    
    public void close() {
        done = true;
    }
    // ------------------------------Getter_Setter------------------------------//
    

    public static void setReady(boolean isReady) {
        ClientController.clientIsReady = isReady;
    }
    
    public static void setMessage(String message) {
        ClientController.message = message;
    }
    
    public static String getMessage() {
        return message;
    }

    public static Spiel getNetzSpiel() {
        return netzSpiel;
    }

    public static void setCmd(String cmd) {
        ClientController.cmd = cmd;
        ClientController.clientIsReady = true;
    }

    public static boolean isClientIsReady() {
        return clientIsReady;
    }

    public static void setClientIsReady(boolean clientIsReady) {
        ClientController.clientIsReady = clientIsReady;
    }

    public static boolean isGameIsReady() {
        return gameIsReady;
    }

    public Player getPlayer() {
        return player;
    }

    public static void setGameIsReady(boolean gameIsReady) {
        ClientController.gameIsReady = gameIsReady;
    }
    
    
}
