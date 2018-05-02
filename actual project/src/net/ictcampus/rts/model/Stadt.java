//packages
package net.ictcampus.rts.model;

//imports

import java.util.List;

/**
 * Klasse Stadt,
 * 
 * @author lauwrensw
 *
 */

public class Stadt extends GameObject {

    // ---------------------------variable_declaration---------------------------//

    private int xPos;
    private int yPos;
    private String name;
    private List<Mensch> volk;
    private List<Item> vorrat;
    private Player besitzer;
    private int preis;

    // -------------------------------Constructor--------------------------------//

    public Stadt(int xPos, int yPos, Player spieler) {
        super();

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    public void menschKaufen(Mensch mensch) {

    }

    public void vorratErzeugen(Ressource ressource, int menge) {

    }

    public void vorratAddieren(Ressource ressource, int menge) {

    }

    public void wirdBetreten(Mensch mensch) {

    }

    public int checkVorrat(Ressource ressource) {
        return 0;

    }

    // ------------------------------Getter_Setter------------------------------//
}
