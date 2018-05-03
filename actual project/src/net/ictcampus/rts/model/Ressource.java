//packages
package net.ictcampus.rts.model;

//imports

/**
 * Klasse Ressource,
 * 
 * @author lauwrensw
 *
 */

public class Ressource extends Item {

    // ---------------------------variable_declaration---------------------------//

    private int anzahl;

    // -------------------------------Constructor--------------------------------//

    public Ressource(String name, int anzahl) {
        super(name);
        this.anzahl = anzahl;
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    // ------------------------------Getter_Setter------------------------------//

    public int getAnzahl() {
        return anzahl;
    }
    
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
    
}
