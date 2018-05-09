package net.ictcampus.rts.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private ObjectInputStream istream;
    
    private BufferedReader br;
    private BufferedWriter bw;

    
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
    	gameIsReady = false;
    	clientIsReady = false;
    	done = false;
        // Setup networking
        socket = ClientSocketFactory.createClientSocket();
        dataSocket = ClientSocketFactory.createClientSocket(54270);
        System.out.println("sldfjklsjd");
        try{
        	DataInputStream dis = new DataInputStream(socket.getInputStream());
	    	InputStreamReader isr = new InputStreamReader(dis);
	    	br = new BufferedReader(isr);
	    	
	    	DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
	    	
        	InputStream ipstr = new DataInputStream(dataSocket.getInputStream());
        	istream = new ObjectInputStream(new BufferedInputStream(ipstr));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        System.out.println("dddddddddddddddd");
        message = this.getMessageFromServer();
        System.out.println(message);
        player = new Player(name, Integer.parseInt(message));
        message = "";
        
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
            bw.write(command);
            bw.flush();

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
	    	char[] cbuf = new char[1];
	    	br.read(cbuf);
	    	msg = new String(cbuf);
	    	//System.out.println("msg = "+msg);
    	} catch (Exception e) {
    	    msg = "";
    	    e.printStackTrace();
    	}
    	return msg.trim();
    }
    /**
     * Client empfängt das Spiel vom Server
     * @return das Spiel
     */
    
    public Spiel getGameStateFromServer() {
        Spiel netzspiel = null;    
        try {
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
     * testmethode mit einem "kleinen" testobjekt zum überprüfen, ob das versenden im Datastream funktioniert
     * @return testobjekt
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
            else if(message.equals("1") && ClientController.clientIsReady) {
                this.sendCommand(ClientController.cmd);
                System.out.println("Comand is being sent: "+ClientController.cmd);
                ClientController.clientIsReady = false;
            }
            else if(message.equals("2")) {
            	System.out.println("2 erhalten");
            	if(!gameIsReady) {
                    String response = Integer.toString(player.getID())+",0,0,0,0,0,0";
                    this.sendCommand(response);
                    ClientController.netzSpiel = getGameStateFromServer();
                    gameIsReady = true;
                } else {
                    String response = Integer.toString(player.getID())+",-1,0,0,0,0,0";
                    this.sendCommand(response);
                }
                
//                System.out.println(zurli.name);
//                System.out.println(zurli.zahl);
            }
        }
        System.out.println("gzgzgzgzgzgzgzgzg");
        System.out.println(done);
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
