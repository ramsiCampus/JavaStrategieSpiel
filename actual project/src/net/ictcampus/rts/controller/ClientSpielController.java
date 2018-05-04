package net.ictcampus.rts.controller;

import net.ictcampus.rts.view.Testframe;

/**
 * ClientSpielController
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
    public void waitForInput() {
        while(!ready) {
            command = tF.getCommand();
            ready = tF.getReady();
            if (ready) {
                break;
            }
        }
        
    }
}
