//packages
package net.ictcampus.rts.model;

import java.util.ArrayList;

//imports

import java.util.List;

/**
 * Klasse Spiel,
 * 
 * @author lauwrensw
 *
 */

public class Spiel {

    // ---------------------------variable_declaration---------------------------//
    private SpielFeld spielFeld;
    private List<Player> spieler = new ArrayList<Player>();

    // -------------------------------Constructor--------------------------------//

    public Spiel(int xLength, int yLength, Player spieler1, Player spieler2) {

        this.spielFeld = new SpielFeld(xLength, yLength);
        this.spieler.add(spieler1);
        this.spieler.add(spieler2);
        
        for(Player i : spieler){
            
            int x = 8;
            int y = 3;            
            initStadt(i, x, y, 9000, "test");            
            x = x+3;
            y= y + 4;
        }
        
        

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    private void initStadt(Player spieler,int xPosSstadt, int yPosStadt, int startGeld, String stadtName){
        
        spielFeld.stadtBauen(xPosSstadt, yPosStadt, spieler, startGeld, stadtName);
        
    }

    // ------------------------------Getter_Setter------------------------------//

    public SpielFeld getSpielFeld() {
        return spielFeld;
    }

    public List<Player> getSpieler() {
        return spieler;
    }
}
