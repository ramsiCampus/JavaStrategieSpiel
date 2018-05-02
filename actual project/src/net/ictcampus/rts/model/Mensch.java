
//packages
package net.ictcampus.rts.model;
//imports

import java.util.ArrayList;
import java.util.List;

/**
 * Mensch,
 * 
 * @author lauwrensw
 * @version 1.0
 */
public class Mensch extends GameObject {

    // ---------------------------variable_declaration---------------------------//

    private int xPos;
    private int yPos;
    private int lebenspunkt;
    private List<Item> tasche = new ArrayList<Item>();
    private Player besitzer = new Player();
    private Ressource preis;

    // -------------------------------Constructor--------------------------------//

    public Mensch(Player besitzer, int preisZahl) {
        super();
        besitzer = this.besitzer;
        Ressource r = new Ressource("Geld",10);
        r.setAnzahl(preisZahl);
        this.preis = r;
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    public boolean bewegen(int xPos, int yPos) {
        return true;
    }

    public void aufnehmen(Item tasche) {

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

}
