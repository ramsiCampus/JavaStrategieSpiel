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
 *
 */

public class Feld implements Serializable {

    // ---------------------------variable_declaration---------------------------//
    private int xPos;
    private int yPos;
    private List<Armee> einheiten = new ArrayList<Armee>();

    private List<Item> loot = new ArrayList<Item>();
    private Stadt stadt;

    // -------------------------------Constructor--------------------------------//

    public Feld(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * wirdBetreten, fügt Objekt Mensch der Liste einheiten hinzu und überprüft
     * ob die Liste Objekte des Typs Mensch erhalten.
     * 
     * @param mensch,
     *            Objeckt Mensch als Parameter wird mitgegeben
     * @return true falls Objekt Mensch in Liste vorhanden ist, sonnst false.
     */

    public boolean wirdBetreten(Armee armee) {
        if (einheiten.size() > 0) {
            for (Armee i : einheiten) {
                if (i.getArmee().size() > 0) {
                    if (i.getBesitzer().equals(armee.getBesitzer())) {
                        i.menschenHinzufuegen(armee.getArmee());
                        return true;
                    }
                }
            }
            // possible pvp
        }
        einheiten.add(armee);
        return true;
    }

    /**
     * erzeugeLoot, erstellt ein neues Item als Ressource und fügt diese der
     * Liste "loot" hinzu
     */

    public void erzeugeLoot() {

        Ressource ressource = new Ressource("Geld", anzahlRessourcen());
        loot.add(ressource);

    }

    public void erzeugeStadt(String name, Player spieler, int startKapital, Mensch protMensch) {
        stadt = new Stadt(name, this.xPos, this.yPos, spieler, startKapital, protMensch);
        setStadt(stadt);
    }

    /**
     * lootAufnehmen,
     * 
     * @param einheiten
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
     * anzahlRessource, generiert eine Zahl zwischen 100 und 1000 und gibt sie
     * als int-Wert zurück @return, generierte Zahl als int
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
            
            for( Armee a : einheiten){
                if(playerID == a.getBesitzer().getID()){
                    anzahlMensch += a.getArmee().size();
                    break;
                }               
            }          
            return anzahlMensch;

    }
}
