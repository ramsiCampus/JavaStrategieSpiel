//packages

package net.ictcampus.rts.model;

//imports

import java.util.List;
import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Feld
 * 
 * @author lauwrensw
 * @version 2.0
 */

public class Feld implements Serializable {

    // ---------------------------variable_declaration---------------------------//
    private int xPos;
    private int yPos;
    private List<Armee> einheiten = new ArrayList<Armee>();
    private List<Item> loot = new ArrayList<Item>();
    private Stadt stadt;

    // -------------------------------Constructor--------------------------------//

    /**
     * Konstruktor Feld
     * 
     * Setzt die Position des Feldes
     * 
     * @param xPos
     *            xPosition des Feldes
     * @param yPos
     *            yPosition des Feldes
     */
    public Feld(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * Methode wirdBetreten
     * 
     * �berpr�ft ob der Spieler auch der Besitzer der Armee ist und f�gt die
     * beide Armee zu einer Enheit zusammen.
     * 
     * Methode lootAufnehmen aus der Klasse Mensch wird f�r jedes Mensch Objekt
     * ausgef�hrt
     * 
     * @param armee
     *            Objeckt Armee als Parameter wird mitgegeben
     * 
     */

    public void wirdBetreten(Armee armee) {
        for (Mensch n : armee.getArmee()) {
            if (!lootAufnehmen(n)) {
                break;
            }
        }

        if (einheiten.size() > 0) {

            for (Armee i : einheiten) {
                if (i.getArmee().size() > 0) {
                    if (i.getBesitzer().equals(armee.getBesitzer())) {
                        List<Mensch> klon = new ArrayList<Mensch>();
                        for (Mensch m : armee.getArmee()) {
                            klon.add(m);
                        }
                        i.menschenHinzufuegen(klon);

                        System.out.println("armee transfer");
                        return;
                    }
                }
            }

            // possible pvp
        }
        einheiten.add(armee);
    }

    /**
     * Methode removeArmee
     * 
     * Entfernt Armee Objekte aus der Liste einheiten.
     * 
     * @param armee
     *            Objekt Armee, welches entfernt werden soll
     */

    public void removeArmee(Armee armee) {
        int i = 0;
        for (Armee a : einheiten) {
            if (a == armee) {
                i = einheiten.indexOf(armee);
            }
        }
        einheiten.remove(i);
    }

    /**
     * Methode erzeugeLoot
     * 
     * Erstellt ein neues Item als Ressource und f�gt diese der Liste "loot"
     * hinzu
     */

    public void erzeugeLoot() {

        Ressource ressource = new Ressource("Geld", anzahlRessourcen());
        loot.add(ressource);
    }

    /**
     * Methode erzeugeStadt
     * 
     * Erstellt auf diesem Feld eine neue Stadt
     * 
     * @param name
     *            mitgegebene Stadtname
     * @param spieler
     *            Spieler, wem die Stadt geh�rt
     * @param startKapital
     *            wieviel Geld die Stadt zur Verf�gung bekommt
     * @param protMensch
     *            Objekt Mensch. Sp�ter k�nnte hier z.B. "K�mpfer" mitgegeben
     *            werden
     */

    public void erzeugeStadt(String name, Player spieler, int startKapital, Mensch protMensch) {
        stadt = new Stadt(name, this.xPos, this.yPos, spieler, startKapital, protMensch);
        setStadt(stadt);
    }

    /**
     * Methode lootAufnehmen
     * 
     * �berpr�ft ob Loot auf dem Feld vorhanden ist und l�sst dies vom
     * mitgegebenen Mensch aufnehmen.
     * 
     * @param mensch
     *            Mensch Objekt, welches Loot aufnimmt
     * @return
     */

    public boolean lootAufnehmen(Mensch mensch) {
        if (this.loot.size() == 0) {
            return false;
        }
        mensch.aufnehmen(this);
        return true;
    }

    /**
     * * anzahlRessource
     * 
     * Generiert eine Zahl zwischen 100 und 1000 und gibt sie als int-Wert
     * zur�ck
     * 
     * @return
     */

    private int anzahlRessourcen() {

        int min = 100;
        int max = 1000;
        Random randomZahl = new Random();
        int generierteRessource = randomZahl.nextInt(max - min) + min;
        return generierteRessource;
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

    public List<Armee> getEinheiten() {
        return einheiten;
    }

    public void setEinheiten(List<Armee> einheiten) {
        this.einheiten = einheiten;
    }

    public List<Item> getLoot() {
        return loot;
    }

    public void setLoot(List<Item> loot) {
        this.loot = loot;
    }

    public Stadt getStadt() {
        return stadt;
    }

    public void setStadt(Stadt stadt) {
        this.stadt = stadt;
    }

    public int getAnzahlRessource() {
        Item lootie = this.getLoot().get(0);
        if (lootie instanceof Ressource) {
            return ((Ressource) lootie).getAnzahl();
        }
        return 0;
    }

    public int countPlayerEinheiten(int playerID) {

        int anzahlMensch = 0;

        for (Armee a : einheiten) {
            if (playerID == a.getBesitzer().getID()) {
                anzahlMensch += a.getArmee().size();
                break;
            }
        }
        return anzahlMensch;

    }
}
