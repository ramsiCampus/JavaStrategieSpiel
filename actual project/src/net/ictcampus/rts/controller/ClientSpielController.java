package net.ictcampus.rts.controller;

import java.util.concurrent.TimeUnit;

import net.ictcampus.rts.model.Player;
import net.ictcampus.rts.model.Spiel;
import net.ictcampus.rts.view.Testframe;

/**
 * ClientSpielController
 * 
 * @author kochjo
 * @version 1.0
 */
public class ClientSpielController {
    // ---------------------------variable_declaration---------------------------//
    ClientController cltCtrl;
    Testframe tF;
    Boolean ready;
    String command;
    Player activePlayer;
    Spiel spiel;
    Thread protocol;

    // -------------------------------Constructor--------------------------------//

    /**
     * ClientSpielController() Konstruktor, Client verbindet sich mit dem Server und man kann das Spiel spielen
     */
    public ClientSpielController() {
        cltCtrl = new ClientController("DiniMuetter");
        activePlayer = cltCtrl.getPlayer();
        ready = false;
        cltCtrl.start();
        System.out.println("soifosdjf");
        while(!ClientController.isGameIsReady())
        {}
        
        spiel = ClientController.getNetzSpiel();
        System.out.println("Spiel ready");
        tF = new Testframe(spiel, activePlayer.getID());
        System.out.println("GUI GESTARTET!");
        supiDupiGame();


    }
    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//
    /**
     * waitForCommand() wartet auf die Befehle vom GUI und sendet diese
     * anschliessend an den ClientController welcher es weiter an den Server
     * sendet wenn ready = true
     */
    public void waitForCommand() {
        while (!ready) {
            ready = tF.getReady();
        }
        command = tF.getCommand();
        System.out.println("Command is "+command);
        ClientController.setCmd(command);
        ready = false;
    }
    
    public void waitForGame() {
        while (!ClientController.isGameIsReady()) {
        	try {TimeUnit.MILLISECONDS.sleep(1000);}//wait 1 seconds before trying again
			catch (Exception e) {e.printStackTrace();}
        }
        spiel = cltCtrl.getNetzSpiel();
    }

    /**
     * supiDupiGame() holt das Spiel von der Logik, sendet es weiter an das GUI und
     * wartet anschliessend darauf, dass alle Spieler ready sind, um die Aktionen an
     * den Server zu senden. Anschliessend geht es wieder von vorne los.
     */

    public void supiDupiGame() {
        while (true) {
            waitForCommand();
            waitForGame();
            tF.setSpiel(spiel);
        }
    }

    // ------------------------------Getter_Setter------------------------------//
    public Spiel getSpiel() {
        return spiel;
    }

}
