
//packages
package net.ictcampus.rts.model;
//imports

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse Mensch
 * 
 * @author lauwrensw
 * @version 2.0
 */
public class Mensch extends GameObject {

    // ---------------------------variable_declaration---------------------------//

    private int xPos;
    private int yPos;
    private int lebenspunkt;
    private List<Item> tasche = new ArrayList<Item>();
    private Player besitzer;
    private Ressource preis = new Ressource("Geld", 10);
    private int ausdauer = 1;

    // -------------------------------Constructor--------------------------------//

    /**
     * Konstruktor Mensch
     *
     * Setzt der Beistzer und die Preis eines Mensch Objektes
     *
     * @param besitzer
     *            welches Objekt Player der Besitzer ist
     * @param preisZahl
     *            wieviel ein Mensch Objekt kostet
     */
    public Mensch(Player besitzer, int preisZahl) {
        super();
        this.besitzer = besitzer;
        preis.setAnzahl(preisZahl);
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * Methode bewegen
     * 
     * Überprüft ob Mensch Objekt genug Ausdauer hat um dorthin zu bewegen
     * 
     * @param xGoal
     *            Ziel x-Koordinate
     * 
     * @param yGoal
     *            Ziel y-Koordinate
     * @return
     */

    public boolean bewegen(int xGoal, int yGoal) {
        int distanz = Math.abs(xPos - xGoal) + Math.abs(yPos - yGoal);
        if (distanz <= this.ausdauer) {
            this.xPos = xGoal;
            this.yPos = yGoal;
            return true;
        }
        return false;
    }

    /**
     * Methode aufnehmen
     * 
     * Überprüft ob Loot auf dem Feld liegt und nimmt sie mit Methode
     * looteGeld() auf
     * 
     * @param feld
     */

    public void aufnehmen(Feld feld) {
        int geld = 0;
        boolean leer = false;
        List<Item> loot = feld.getLoot();
        for (int i = 0; i < loot.size(); i++) {
            if (loot.get(i) instanceof Ressource) {
                if (loot.get(i).getName().equals("Geld")) {
                    geld = ((Ressource) loot.get(i)).getAnzahl();
                    if (geld > 50) {
                        looteGeld(50);
                        ((Ressource) loot.get(i)).setAnzahl(geld - 50);
                    }
                    else {
                        looteGeld(geld);
                        leer = true;
                        loot.remove(i);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Methode looteGeld
     * 
     * Überprüft ob Objekt Mensch schon Geld in der Tasche hat, ruft Methode
     * checkTasche und erzeugeInBeutel auf. Nimmt Loot vom Feld auf.
     * 
     * @param menge
     */

    private void looteGeld(int menge) {
        if (checkTasche("Geld") == 0) {
            erzeugeInBeutel("Geld", menge);
        }
        else {
            for (Item i : tasche) {
                if (i instanceof Ressource) {
                    if (i.getName().equals("Geld")) {
                        int mengeNeu = (checkTasche("Geld") + menge);
                        ((Ressource) i).setAnzahl(mengeNeu);
                    }
                }
            }
        }
    }

    /**
     * Methode checkTasche
     * 
     * Returnt Anzahl Ressourcen des itemNames
     *
     * @param itemName
     *            Name der gesuchten Ressourcen
     * @return
     */

    private int checkTasche(String itemName) {
        int anzahl = 0;
        for (Item i : tasche) {
            if (i instanceof Ressource) {
                if (i.getName().equals(itemName))
                    anzahl += ((Ressource) i).getAnzahl();
            }
        }
        return anzahl;
    }

    /**
     * Methode erzeugeInBeutel
     * 
     * Erzeugt gewünschte Anzahl Ressourcen in Item tasche
     * 
     * @param ressourceName
     *            Zu erzeugende Ressource
     * @param menge
     *            Anzahl Ressource
     */

    private void erzeugeInBeutel(String ressourceName, int menge) {
        Item newRessource = new Ressource(ressourceName, menge);
        this.tasche.add(newRessource);
    }

    // ------------------------------Getter_Setter------------------------------//

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getLebenspunkt() {
        return lebenspunkt;
    }

    public void setLebenspunkt(int lebenspunkt) {
        this.lebenspunkt = lebenspunkt;
    }

    public List<Item> getTasche() {
        return tasche;
    }

    public void setTasche(List<Item> tasche) {
        this.tasche = tasche;
    }

    public Player getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Player besitzer) {
        this.besitzer = besitzer;
    }

    public Ressource getPreis() {
        return preis;
    }

    public void setPreis(Ressource preis) {
        this.preis = preis;
    }

    public int getAusdauer() {
        return ausdauer;
    }

    public void setAusdauer(int ausdauer) {
        this.ausdauer = ausdauer;
    }

}
