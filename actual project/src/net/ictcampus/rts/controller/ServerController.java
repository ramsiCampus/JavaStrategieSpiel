package net.ictcampus.rts.controller;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *ServerController, öffnet Serversocket...
 * 
 * @author Johanna
 * @version 1.0
 */
public class ServerController {
    // ---------------------------variable_declaration---------------------------//
    private ServerSocket listener;

    // -------------------------------Constructor--------------------------------//
    public ServerController() {
        listener = ServerSocketFactory.createServerSocket();
        try {
            while(true) {
                try {
                    new Handler(listener.accept()).start();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }finally {
            ServerSocketFactory.closeServerSocket(listener);
        }
    }
    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    // ------------------------------Getter_Setter------------------------------//
}
