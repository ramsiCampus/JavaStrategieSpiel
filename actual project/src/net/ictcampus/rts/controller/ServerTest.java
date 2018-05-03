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

public class ServerTest {

    // ---------------------------variable_declaration---------------------------//

    // -------------------------------Constructor--------------------------------//

    // -----------------------------------Main-----------------------------------//
    public static void main(String[] args) throws IOException {
        ServerController SC = new ServerController();
        SC.printCommand();
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
    
}
