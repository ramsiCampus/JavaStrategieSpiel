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
    private static int PORT = 54269;

    // -------------------------------Constructor--------------------------------//

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//
    /**
     * createServerSocket() erstellt Serversocket
     * @return listener
     */
    public static ServerSocket createServerSocket() {
        try {
            listener = new ServerSocket(PORT,1, InetAddress.getByName("nkerstingk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listener;
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

