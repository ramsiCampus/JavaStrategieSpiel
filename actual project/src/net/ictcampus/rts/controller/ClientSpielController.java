package net.ictcampus.rts.controller;

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
    Boolean ready = false;
    Boolean isready = false;
    String command;
    Player activePlayer;
    Spiel spiel;

    // -------------------------------Constructor--------------------------------//

    public ClientSpielController() {
        cltCtrl = new ClientController("DiniMuetter");
        activePlayer = cltCtrl.getPlayer();
        while(!cltCtrl.isGameIsReady()) {
            spiel = cltCtrl.getNetzSpiel();
        }
        //tF = new Testframe();
        supiDupiGame();

    }
    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//
    /**
     * waitForCommand() wartet auf die Befehle vom GUI und sendet diese
     * anschliessend an den Server weiter wenn ready = true
     */
    public void waitForCommand() {
        while (!ready) {
            ready = tF.getReady();
            if (ready) {
                command = tF.getCommand();
                cltCtrl.sendCommand(command);
                ready = false;
                break;
            }
        }
    }
    
    public void waitForGame() {
        while (!isready) {
            isready = cltCtrl.isGameIsReady();
            if(isready) {
                spiel = cltCtrl.getNetzSpiel();
                break;
            }
        }
    }

    /**
     * supiDupiGame() holt das Spiel von der Logik, sendet es weiter an das GUI und
     * wartet anschliessend darauf, dass alle Spieler ready sind, um die Aktionen an
     * den Server zu senden. Anschliessend geht es wieder von vorne los.
     */

    public void supiDupiGame() {
        while (true) {
            // Spiel an GUI senden
            waitForCommand();
            waitForGame();
        }
    }

    // ------------------------------Getter_Setter------------------------------//
    public Spiel getSpiel() {
        return spiel;
    }

}
