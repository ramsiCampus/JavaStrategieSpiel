package net.ictcampus.rts.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
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
    private static Socket dataSocket;
    private static final int PORT = 54271;
    //private static String serverAddress = "172.16.2.133";
    //private static String serverAddress = "172.16.2.219";
    private static String serverAddress = "127.0.0.1";
  //-------------------------------Constructor--------------------------------//

  //-----------------------------------Main-----------------------------------//

  //---------------------------------Methods---------------------------------//
    /**
     * createClientSocket() erstellt Socket
     * @return socket
     */
    public static Socket createClientSocket() {
        boolean notConnected = true;
    	while(notConnected) {
	    	try {
	            socket = new Socket(serverAddress, PORT);
	            notConnected = false;
	        } catch (UnknownHostException e) {
	            notConnected = false;
	            e.printStackTrace();
	        } catch (IOException e) {
	            notConnected = true;
	            e.printStackTrace();
	        }
    	}
        
        return socket;
    }
    
    /**
     * createClientSocket() überladen mit einer angabe für port
     * @param porterino int für port
     * @return socket
     */
    public static Socket createClientSocket(int porterino) {
        boolean notConnected = true;
        while(notConnected) {
            try {
                dataSocket = new Socket(serverAddress, porterino);
                notConnected = false;
            } catch (UnknownHostException e) {
                notConnected = false;
                e.printStackTrace();
            } catch (IOException e) {
                notConnected = true;
                e.printStackTrace();
            }
        }
        
        return dataSocket;
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
    public static void closeClientSocket(Socket socketToClose) {
        try {
            socketToClose.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  //------------------------------Getter_Setter------------------------------//
}