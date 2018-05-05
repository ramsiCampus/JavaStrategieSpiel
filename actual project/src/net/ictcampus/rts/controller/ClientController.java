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
    private Socket dataSocket;
    private DataOutputStream os;
    private OutputStreamWriter osw;
    private BufferedWriter bw;
    private Thread protocol;
    
    private static volatile boolean clientIsReady;
    private static volatile boolean gameIsReady;
    private static volatile String message;
    private static volatile String cmd;
    private static volatile Spiel netzSpiel;
    private static volatile boolean done = false;
    private Player player;
    private SmallSerial zurli;

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
        dataSocket = ClientSocketFactory.createClientSocket(54270);
        clientIsReady = true;
        message = this.getMessageFromServer();
        System.out.println(message);
        player = new Player(name, Integer.parseInt(message));
        message = "";
        gameIsReady = false;
        System.out.println("OK GOT IT");
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
	    	
	    	char[] cbuf = new char[1];
	    	br.read(cbuf);
	    	msg = new String(cbuf);
	    	System.out.println("msg = "+msg);

    	} catch (IOException e) {
    	    msg = "";
    	}
    	return msg.trim();
    }
    
    
    public Spiel getGameStateFromServer() {
        Spiel netzspiel = null;    
        try {
            //DataInputStream dis = new DataInputStream(socket.getInputStream());
            ObjectInputStream istream = new ObjectInputStream(dataSocket.getInputStream());
            
             Object temp = istream.readObject();
             System.out.println("::gelesen");
             netzspiel = (Spiel)temp;
        } catch (IOException e){
            System.out.println("I O Exception in getGameStateFromServer");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Dini Muetter esch en respektabli Frau");
        return netzspiel;
        
    }
    
    /**
     * testemethode
     */
    public SmallSerial getZurli() {
        //SmallSerial zurli = null;    
        try {
            //DataInputStream dis = new DataInputStream(socket.getInputStream());
            ObjectInputStream istream = new ObjectInputStream(dataSocket.getInputStream());
            
             Object temp = istream.readObject();
             System.out.println("::gelesen");
             zurli = (SmallSerial)temp;
        } catch (IOException e){
            System.out.println("I O Exception in getGameStateFromServer");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Dini Muetter esch en respektabli Frau");
        return zurli;
        
    }
    
    /**
     * empfängt messages vom Server und vollführt die entsprechende Aktion des Protokolls
     */
    public void run() {
        
        while(!done) {
            message = getMessageFromServer();
            if(message.equals("0")) {
                if(ClientController.clientIsReady) {
                    String response = Integer.toString(player.getID())+",0,0,0,0,0,0";
                    this.sendCommand(response);
                } else {
                    String response = Integer.toString(player.getID())+",-1,0,0,0,0,0";
                    this.sendCommand(response);
                }
            }
            else if(message.equals("1")) {
                this.sendCommand(cmd);
                ClientController.clientIsReady = false;
            }
            else if(message.equals("2")) {
                ClientController.netzSpiel = getGameStateFromServer();
//                zurli = getZurli();
                gameIsReady = true;
//                System.out.println(zurli.name);
//                System.out.println(zurli.zahl);
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
        ClientController.gameIsReady = false;
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
