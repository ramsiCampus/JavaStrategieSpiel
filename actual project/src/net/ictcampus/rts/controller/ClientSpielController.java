package net.ictcampus.rts.controller;

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
    String command;

    // -------------------------------Constructor--------------------------------//

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//
    /**
     * waitForCommand() wartet auf die Befehle vom GUI und sendet diese
     * anschliessend an den Server weiter
     */
    public void waitForCommand() {
        while (!ready) {
            command = tF.getCommand();
            ready = tF.getReady();
            if (ready) {
                ready = false;
                break;
            }
        }
        cltCtrl.sendCommand(command);
    }
    
    public void getPlayerID() {
        
    }
}
