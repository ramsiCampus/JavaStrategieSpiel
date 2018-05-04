package net.ictcampus.rts.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;

import javax.swing.JOptionPane;

import net.ictcampus.rts.model.*;

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
//        ServerController SC = new ServerController();
//        System.out.println(SC.receiveCommands());
//        SC.sendMessage("server msg");
//        ServerSocketFactory.closeServerSocket(ServerSocketFactory.getServerSocket());
//      
        Player testPlayer1 = new Player("Lurith", 0);
        Player testPlayer2 = new Player("Johanna", 1);
        Mensch protoMensch = new Mensch(testPlayer1, 10);
        Spiel TestSpiel = new Spiel(20, 10 , testPlayer1, testPlayer2, protoMensch);
        
        
    	ServerSpielController ssc = new ServerSpielController(2);
    	//ssc.getCommands();
    	//ssc.printCommands();
    	ssc.sendGame(TestSpiel);
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
