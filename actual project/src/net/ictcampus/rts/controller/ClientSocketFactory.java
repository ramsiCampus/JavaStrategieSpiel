package net.ictcampus.rts.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ClientSocketFactory, erstellt, holt und schliesst Clientsockets
 * 
 * @author Johanna
 * @version 1.0
 */

public class ClientSocketFactory {
  //---------------------------variable_declaration---------------------------//
    private static Socket socket;
    private static int PORT = 54269;
    //private static String serverAddress = "172.16.2.133";
    private static String serverAddress = "127.0.0.1";
  //-------------------------------Constructor--------------------------------//

  //-----------------------------------Main-----------------------------------//

  //---------------------------------Methods---------------------------------//
    /**
     * createSocket() erstellt ClientSocket
     * @return socket
     */
    public static Socket createClientSocket() {
        try {
        	System.out.println("Creating socket 2");
            socket = new Socket(serverAddress, PORT);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Server found");
        return socket;
    }
    
    /**
     * getSocket() holt ClientSocket, falls es keinen hat, wird einer erstellt
     * @return socket
     */
    public static Socket getClientSocket(){
        if (socket == null) {
        	System.out.println("Creating socket 1");
            socket = createClientSocket();
            System.out.println("Socket created");
        }
        return socket;
    }
    
    /**
     * closeSocket() schliesst ClientSocket
     * @param socket
     */
    public static void closeClientSocket(Socket socketToClose) {
        try {
            socketToClose.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

  //------------------------------Getter_Setter------------------------------//
}