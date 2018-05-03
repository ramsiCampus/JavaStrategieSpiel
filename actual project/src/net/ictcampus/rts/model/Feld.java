//packages
package net.ictcampus.rts.model;

//imports
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Feld
 * 
 * @author lauwrensw
 *
 */

public class Feld {

    // ---------------------------variable_declaration---------------------------//
    private int xPos;
    private int yPos;
    private List<Mensch> einheiten = new ArrayList<Mensch>();
    private List<Item> loot = new ArrayList<Item>();
    private Stadt stadt;

    // -------------------------------Constructor--------------------------------//

   
   
    public Feld(int xPos, int yPos) {
        xPos = this.xPos;
        yPos = this.yPos;
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * wirdBetreten, f�gt Objekt Mensch der Liste einheiten hinzu und �berpr�ft ob
     * die Liste Objekte des Typs Mensch erhalten.
     * 
     * @param mensch,
     *            Objeckt Mensch als Parameter wird mitgegeben
     * @return true falls Objekt Mensch in Liste vorhanden ist, sonnst false.
     */

    @SuppressWarnings("unlikely-arg-type")
    public boolean wirdBetreten(Mensch mensch) {

        einheiten.add(mensch);

        if (einheiten.contains(einheiten)) {
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * erzeugeLoot, erstellt ein neues Item als Ressource und f�gt diese der Liste
     * "loot" hinzu
     */

    public void erzeugeLoot() {

        Ressource ressource = new Ressource("Geld", anzahlRessourcen());
        loot.add(ressource);

    }
    
    public void erzeugeStadt(String name, Player spieler){
        stadt = new Stadt(name, this.xPos,this.yPos, spieler);  
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
     * anzahlRessource, generiert eine Zahl zwischen 100 und 1000 und gibt sie als
     * int-Wert zur�ck @return, generierte Zahl als int
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

    public List<Mensch> getEinheiten() {
        return einheiten;
    }

    public void setEinheiten(List<Mensch> einheiten) {
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


}