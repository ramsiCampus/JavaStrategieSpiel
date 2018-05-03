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
    private Mensch protMensch;
    private int stadtPreis;

    // -------------------------------Constructor--------------------------------//

    public Spiel(int xLength, int yLength, Player spieler1, Player spieler2) {

        this.spielFeld = new SpielFeld(xLength, yLength);
        this.spieler.add(spieler1);
        this.spieler.add(spieler2);
        this.stadtPreis = 1000;

        for (Player i : spieler) {

            int x = 8;
            int y = 3;
            initStadt(i, x, y, 9000, "test");
            x = x + 3;
            y = y + 4;
        }

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    private void initStadt(Player spieler, int xPosStadt, int yPosStadt, int startKapital,
            String stadtName) {
        
        Stadt stadt = new Stadt(stadtName, xPosStadt, yPosStadt, spieler);
        spielFeld.getStaedte().add(stadt);

    }
    
    public int getStadtPreis(){
        
        return stadtPreis;
    }

    // ------------------------------Getter_Setter------------------------------//

    public SpielFeld getSpielFeld() {
        return spielFeld;
    }

    public List<Player> getSpieler() {
        return spieler;
    }

    public void setProtMensch(Mensch protMensch) {
        this.protMensch = protMensch;
    }

    public int getMenschPreis() {
        return this.protMensch.getPreis().getAnzahl();
    }

}
