package net.ictcampus.rts.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import net.ictcampus.rts.model.*;

/**
 * ClientServerTest, zum Verbindung zwischen Server und Client testen
 * 
 * @author kochjo
 * @version 1.0
 */

public class ServerTest {

    // ---------------------------variable_declaration---------------------------//

    // -------------------------------Constructor--------------------------------//

    // -----------------------------------Main-----------------------------------//
    /**
     * mainmethode, es werden Spieler erstellt, ein Spiel der Serververbindet sich
     * mit den Clients und lässt anschliessend die methode play() laufen
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Player testPlayer1 = new Player("Lurith", 0);
        Player testPlayer2 = new Player("Johanna", 1);
        Mensch protoMensch = new Mensch(testPlayer1, 10);
        Spiel TestSpiel = new Spiel(20, 10, testPlayer1, testPlayer2, protoMensch);

        ServerSpielController ssc = new ServerSpielController(1);
        int i = 7;
        System.out.println("Irgenöppis");

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ssc.play();
        System.out.println("Test over");

    }

    // ---------------------------------Methods---------------------------------//

}
