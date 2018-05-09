
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
    private Player besitzer;
    private Ressource preis = new Ressource("Geld", 10);
    private int ausdauer = 1;

    // -------------------------------Constructor--------------------------------//

    public Mensch(Player besitzer, int preisZahl) {
        super();

        this.besitzer = besitzer;
        preis.setAnzahl(preisZahl);

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    public boolean bewegen(int xGoal, int yGoal) {
        int distanz = Math.abs(xPos - xGoal) + Math.abs(yPos - yGoal);
        if (distanz <= this.ausdauer) {
            this.xPos = xGoal;
            this.yPos = yGoal;
            return true;
        }
        return false;
    }

    // public void aufnehmen(Feld feld) {
    // int geld = 0;
    // List<Item> loot = feld.getLoot();
    // for(Item it : loot) {
    // if(it instanceof Ressource) {
    // if(it.getName().equals("Geld")) {
    // geld =((Ressource) it).getAnzahl();
    // if (geld > 50) {
    // looteGeld(50);
    // ((Ressource) it).setAnzahl(geld-50);
    // }
    // else {
    // looteGeld(geld);
    // ((Ressource) it).setAnzahl(0);
    // //loot.remove(0); //hard-coded für Felder die nur Geld haben
    // //JÜNGE! Da dörfsch nöd! Du chasch nöd Sache lösche
    // //wod no am drüberiteriere bisch
    // }
    // }
    // }
    // }
    //
    // }

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
                        loot.remove(i); // so tuet mer richtig lösche, oder mit Iterator
                        break;
                    }
                }
            }
        }

    }

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

    public int checkTasche(String itemName) {
        int anzahl = 0;
        for (Item i : tasche) {
            if (i instanceof Ressource) {
                if (i.getName().equals(itemName))
                    anzahl += ((Ressource) i).getAnzahl();
            }
        }

        return anzahl;
    }

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
