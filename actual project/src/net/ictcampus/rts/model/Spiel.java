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

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    public boolean commandAusfuehren(int int1, int int2) {
        return false;

    }

//    public void spielerHinzufuegen(Player spieler) {
//
//    }

    // ------------------------------Getter_Setter------------------------------//

    public SpielFeld getSpielFeld() {
        return spielFeld;
    }

    public List<Player> getSpieler() {
        return spieler;
    }
}
