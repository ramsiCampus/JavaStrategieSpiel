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
    private static int PORT = 4269;
    private static String serverAddress = "172.16.2.133";
  //-------------------------------Constructor--------------------------------//

  //-----------------------------------Main-----------------------------------//

  //---------------------------------Methods---------------------------------//
    /**
     * createSocket() erstellt ClientSocket
     * @return socket
     */
    public static Socket createClientSocket() {
        try {
            socket = new Socket(serverAddress, PORT);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return socket;
    }
    
    /**
     * getSocket() holt ClientSocket, falls es keinen hat, wird einer erstellt
     * @return socket
     */
    public static Socket getClientSocket(){
        if (socket == null) {
            socket = createClientSocket();
        }
        return socket;
    }
    
    /**
     * closeSocket() schliesst ClientSocket
     * @param socket
     */
    public static void closeClientSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

  //------------------------------Getter_Setter------------------------------//
}