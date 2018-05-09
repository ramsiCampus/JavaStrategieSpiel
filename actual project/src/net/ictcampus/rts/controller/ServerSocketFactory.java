package net.ictcampus.rts.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerSocketFactory, öffnet Serversockets
 * 
 * @author Johanna
 * @version 1.0
 */

public class ServerSocketFactory {
    // ---------------------------variable_declaration---------------------------//
    private static ServerSocket listener;
    private static ServerSocket dataListener;
    private static int PORT = 54271;
    private static String hostname = "nkerstingk";
    //private static String hostname = "nkochjo";
    //private static String hostname = "localhost";

    // -------------------------------Constructor--------------------------------//

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//
    /**
     * createServerSocket() erstellt Serversocket
     * @return listener
     */
    public static ServerSocket createServerSocket() {
    	
        try {
            listener = new ServerSocket(PORT,1, InetAddress.getByName(hostname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listener;
    }
    
    /**
     * ServerSocket() überladen, man kann einen Port angeben
     * @param porterino (int)
     * @return
     */
    public static ServerSocket createServerSocket(int porterino) {
        
        try {
            dataListener = new ServerSocket(porterino,1, InetAddress.getByName(hostname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataListener;
    }
    
    /**
     * getSocket() holt ServerSocket, falls es keinen hat, wird einer erstellt
     * @return ServerSocket
     */
    public static ServerSocket getServerSocket(){
        if (listener == null) {
            listener = createServerSocket();
        }
        return listener;
    }
    
    /**
     * closeSocket() schliesst ServerSocket
     * @param Serversocket
     */
    public static void closeServerSocket(ServerSocket listenerToClose) {
        try {
            listenerToClose.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // ------------------------------Getter_Setter------------------------------//
}

