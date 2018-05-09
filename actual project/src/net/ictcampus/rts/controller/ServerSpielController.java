package net.ictcampus.rts.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.ictcampus.rts.model.Spiel;
import net.ictcampus.rts.model.SpielLogik;

/**
 * ServerSpielController, Server erstellt Serversockets, verbindet sich mit
 * Clients, startet das Spiel und lässt anschliessend Clients damit spielen. Er
 * empfängt Befehle (Strings) von den Clients, welche er anschliessend mit der Spiellogik
 * ausführt und dann den Clients das Spiel-Objekt aktualisiert zurücksendet.
 * 
 * @author kerstingk
 * @version 2.0
 */
public class ServerSpielController {
    // ---------------------------variable_declaration---------------------------//

    private int anzSpieler = 2;
    private static final int COMMANDCOUNT = 7;
    SpielLogik spielLogik;
    ServerController srvCtrl;
    int[][] commands;

    // -------------------------------Constructor--------------------------------//
    /**
     * ServerSpielController() Konstruktor für das Spiel serverseitig, man muss
     * angeben wie viele Spieler sich verbinden
     * 
     * @param anzSpieler
     */
    public ServerSpielController(int anzSpieler) {
        this.anzSpieler = anzSpieler;
        this.srvCtrl = new ServerController(anzSpieler);
        this.commands = new int[anzSpieler][COMMANDCOUNT];
        System.out.println("Initializing game");
        initGame();
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//
    /**
     * getCommands() Server empfängt String, welcher alle Befehle von Spielern
     * beinhaltet und splitet diesen zuerst nach Spieler und dann nach Argumente auf
     */
    public void getCommands() {
        String allCommands = "";
        try {
            allCommands = this.srvCtrl.receiveCommands(); // hier werden die Commands von allen
                                                          // Spielern empfangen
            String[] commandsByPlayer = allCommands.split("#");
            for (int i = 0; i < anzSpieler; i++) {
                String[] playerCommands = commandsByPlayer[i].split(",");
                for (int j = 0; j < COMMANDCOUNT; j++) {
                    commands[i][j] = Integer.parseInt(playerCommands[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    /**
     * initGame() Server erstellt das Game
     */
    public void initGame() {
        this.spielLogik = new SpielLogik(anzSpieler);
        System.out.println("spielLogik erstellt");
        hereComesTheGame();
        System.out.println("Game initialised and sent");
    }

    /**
     * printCommands() druckt die Befehle der Spieler in Konsole (Debug..)
     */
    public void printCommands() {
        for (int i = 0; i < anzSpieler; i++) {
            for (int j = 0; j < COMMANDCOUNT; j++) {
                System.out.print(commands[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * sendMessageToAll() Server sendet Nachricht an alle Clients
     * 
     * @param message
     *            (String)
     */
    public void sendMessageToAll(String message) {
        try {
            this.srvCtrl.sendMessageToAll(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sendGameToAll() Server sendet Spiel an alle Clients
     * 
     * @param game
     *            (Spiel)
     */
    public void sendGameToAll(Spiel game) {
        try {
            this.srvCtrl.sendGameStateToAll(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sendObject() Testmethode, Server sendet "kleines" Objekt an Client
     * 
     * @param sm
     *            (SmallSerial)
     */
    public void sendObject(SmallSerial sm) {
        try {
            this.srvCtrl.sendNormalObject(sm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * areClientsReady() Sendet eine 0 an alle Clients und nimmt ihre Antort auf,
     * nur falls alle Clients ready sind (Aktion = 0) wird true zurückgegeben. Sonst
     * false
     * 
     * @return true wenn alle Clients ready sind, sonst false
     */
    public boolean areClientsReady() {
        sendMessageToAll("0");
        getCommands();
        for (int i = 0; i < this.anzSpieler; i++) {
            if (this.commands[i][1] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * areClientsReadyToGetGame() wenn Clients bereit sind zum das Spiel empfangen,
     * antworten sie mit einer "0"
     * 
     * @return
     */
    public boolean areClientsReadyToGetGame() {
        sendMessageToAll("2");
        getCommands();
        for (int i = 0; i < this.anzSpieler; i++) {
            if (this.commands[i][1] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * getPlayCommands() Sendet eine 1 and alle Clients und nimmt dann die von den
     * Spielern gesendeten Commands entgegen. Diese Funktion darf erst nach
     * areClientsReady() ausgeführt werden, da sonst falsche Commands
     * entgegengenommen werden.
     */
    public void getPlayCommands() {
        sendMessageToAll("1");
        getCommands();
    }

    /**
     * hereComesTheGame() Sendet eine 2 an alle Clients und sendet anschliessend das
     * Spiel an alle Clients. Wird nach ausführen der commands auf dem Spiel
     * ausgeführt.
     */
    public void hereComesTheGame() {
        System.out.println("entering sending game");
        while (!this.areClientsReadyToGetGame()) {
            System.out.println("Waiting for Clients to get game");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error while waiting to send the game");
            }
        }
        System.out.println("sending game");
        sendGameToAll(spielLogik.getSpiel());
        System.out.println("DAT FELD auf dem Server: "
                + spielLogik.getSpiel().getSpielFeld().getFelder()[11][8].countPlayerEinheiten(0));
    }

    /**
     * play() Spielmethode
     */
    public void play() {
        // anstatt while true, könnte man in der Spiellogik prüfen ob das Spiel vorbei
        // ist und
        // dann anhand von jenem Zustand die Schlaufe beenden.
        while (true) {
            if (areClientsReady()) {
                getPlayCommands();
                for (int i = 0; i < anzSpieler; i++) {
                    spielLogik.commandAusfuehren(commands[i]);
                }
                hereComesTheGame();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error while sleeping between areClientsReady on the server.");
            }

        }
    }

    // -----------------------------Getter_Setter------------------------------------//

    public int getAnzSpieler() {
        return anzSpieler;
    }

    public SpielLogik getSpielLogik() {
        return spielLogik;
    }

    public void setSpielLogik(SpielLogik spielLogik) {
        this.spielLogik = spielLogik;
    }

    public ServerController getSrvCtrl() {
        return srvCtrl;
    }

    public void setSrvCtrl(ServerController srvCtrl) {
        this.srvCtrl = srvCtrl;
    }

    public void setCommands(int[][] commands) {
        this.commands = commands;
    }

}
