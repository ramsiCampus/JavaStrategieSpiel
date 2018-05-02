package net.ictcampus.rts.controller;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

/**
 * ClientController, Verbindet sich mit dem Server, sendet Befehle an den Server
 * und empfängt den Spielstand
 * 
 * @author Johanna
 * @version 1.0
 */
public class ClientController {

    // ---------------------------variable_declaration---------------------------//
    // socket (Client)
    private Socket socket;
    private static int PORT = 4269;
    private String serverAddress = "127.0.0.1";
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
        try {
            socket = new Socket(serverAddress, PORT);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            // Stream öffnen zum param senden 
            os = new DataOutputStream(socket.getOutputStream());
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            bw.write(eins);
            bw.write(zwei);
            
            bw.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // Close Socket
            // TODO notwendig???
            //nope ist es nicht
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
    // ------------------------------Getter_Setter------------------------------//
    // public Spiel getSpielStatus() {
    // return Spiel;
    // }
}
