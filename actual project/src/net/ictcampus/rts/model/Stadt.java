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

    public Stadt(String name, int xPos, int yPos, Player spieler) {
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
     * @param mensch
     *            Welcher Menschen typ soll gekauft werden
     * @return boolean ob Transaktion geglückt
     */
    public boolean menschKaufen(Mensch mensch) {
        int preisM = mensch.getPreis().getAnzahl();
        if (preisM > this.checkVorrat("Geld")) {
            return false;
        }
        for (int i = 0; i < preisM; i++) {
            for (Item item : vorrat) {
                if (item instanceof Ressource) {
                    if (item.getName().equals("Geld")) {
                        int saldo = this.checkVorrat("Geld") - preisM;
                        ((Ressource) item).setAnzahl(saldo);
                    }
                }
            }
            Mensch newBuerger = new Mensch(this.besitzer, preisM);
            volk.add(newBuerger);
        }
        return true;
    }

    public void vorratErzeugen(String ressourceName, int menge) {
        Item r = new Ressource(ressourceName, menge);
        vorrat.add(r);
    }

    public void vorratAddieren(String ressourceName, int menge) {
        if (checkVorrat(ressourceName) == 0) {
            vorratErzeugen(ressourceName, menge);
        }
        else {
            for (Item i : vorrat) {
                if (i instanceof Ressource) {
                    if (i.getName().equals(ressourceName)) {
                        int mengeNeu = ((Ressource) i).getAnzahl() + menge;
                        ((Ressource) i).setAnzahl(mengeNeu);
                    }
                }
            }
        }
    }

    public void wirdBetreten(Mensch mensch) {

    }

    public int checkVorrat(String itemName) {
        int anzahl = 0;
        for (Item i : vorrat) {
            if (i instanceof Ressource) {
                if (i.getName().equals(itemName))
                    anzahl += ((Ressource) i).getAnzahl();
            }
        }

        return anzahl;
    }

    // ------------------------------Getter_Setter------------------------------//
}
