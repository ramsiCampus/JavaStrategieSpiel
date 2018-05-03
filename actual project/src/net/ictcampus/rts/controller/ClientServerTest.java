package net.ictcampus.rts.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;

import javax.swing.JOptionPane;

/**
 * ClientServerTest, zum Verbindung zwischen Server und Client testen
 * @author kochjo
 * @version 1.0
 */

public class ClientServerTest {

    // ---------------------------variable_declaration---------------------------//

    // -------------------------------Constructor--------------------------------//

    // -----------------------------------Main-----------------------------------//
    public static void main(String[] args) throws IOException {
        ServerController SC = new ServerController();
        ClientController CC = new ClientController();
       // servermet();
       // clientmet();
        ClientSocketFactory.closeClientSocket(ClientSocketFactory.getClientSocket());
        ServerSocketFactory.closeServerSocket(ServerSocketFactory.getServerSocket());
        

    }

    // ---------------------------------Methods---------------------------------//
    public static void servermet() throws IOException {
        try {
            while (true) {
                Socket socket = ClientSocketFactory.getClientSocket();
                socket = (ServerSocketFactory.getServerSocket()).accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date(0).toString());
                } finally {
                    socket.close();
                }
            }
        } finally {
            (ServerSocketFactory.getServerSocket()).close();
        }

    }
    
    public static void clientmet() throws IOException {
        BufferedReader input =
                new BufferedReader(new InputStreamReader((ClientSocketFactory.getClientSocket()).getInputStream()));
            String answer = input.readLine();
            JOptionPane.showMessageDialog(null, answer);

    }

    // ------------------------------Getter_Setter------------------------------//
}
