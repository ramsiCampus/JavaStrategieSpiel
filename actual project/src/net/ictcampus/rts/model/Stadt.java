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

    public Stadt(String name,int xPos, int yPos, Player spieler) {
        super();
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.besitzer = spieler;
        
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * 
     * @param mensch Welcher Menschen typ soll gekauft werden
     * @return boolean ob Transaktion geglückt
     */
    public boolean menschKaufen(Mensch mensch) {
        int preisM = mensch.getPreis().getAnzahl();    
        if(preisM > this.checkVorrat("Geld")) {
            return false;
        }
        for(int i = 0; i<preis;i++) {
            vorrat.remove(vorrat.size()-1);
            Mensch newBurger = new Mensch(this.besitzer, preisM);
            volk.add(newBurger);
        }
        return true;
    }

    public void vorratErzeugen(Ressource ressource, int menge) {

    }

    public void vorratAddieren(Ressource ressource, int menge) {

    }

    public void wirdBetreten(Mensch mensch) {

    }

    public int checkVorrat(String itemName) {
        int anzahl = 0;
        for(Item i : vorrat) {
            if(i instanceof Ressource) {
                if(i.getName().equals(itemName))
                anzahl += ((Ressource)i).getAnzahl();
            }
        }
        
        return anzahl;
    }

    // ------------------------------Getter_Setter------------------------------//
}
