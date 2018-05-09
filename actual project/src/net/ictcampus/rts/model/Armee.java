//packages
package net.ictcampus.rts.model;

//imports

import java.util.List;
import java.util.ArrayList;

/**
 * Klasse Armee
 * 
 * @author lauwrensw
 * @Version 2.0
 *
 */

public class Armee extends GameObject {

    // ---------------------------variable_declaration---------------------------//

    private int xPos;
    private int yPos;
    private String name;
    private List<Mensch> armee = new ArrayList<Mensch>();
    private int ausdauer = 99;
    private Player besitzer;

    // -------------------------------Constructor--------------------------------//

    /**
     * Konstruktor Armee
     * 
     * Bestimmt der Besitzer und wieviel Ausdauer die Mensch Objekten haben
     * 
     * @param besitzer
     *            Objekt Player, welches die Armee besitzt
     * @param name
     *            Name der Armee
     * @param anzahlMenschen
     *            Anzahl Mensch Objekte in der Armee
     */

    public Armee(Player besitzer, String name, int anzahlMenschen) {

        this.setBesitzer(besitzer);
        this.setName(name);
        for (int i = 0; i < anzahlMenschen; i++) {
            Mensch tempMensch = new Mensch(besitzer, i);
            if (tempMensch.getAusdauer() < this.ausdauer) {
                this.setAusdauer(tempMensch.getAusdauer());
            }
            armee.add(tempMensch);
        }
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * Methode menschenEinfuegen
     * 
     * Fügt Mensch Objekte in die Liste armee hinein.
     * 
     * @param neuArmee
     *            Liste von Mensch Objekte, welche in die neue Armee
     *            hineingefügt werden
     */

    public void menschenHinzufuegen(List<Mensch> neuArmee) {

        while (neuArmee.size() > 0) {
            this.armee.add(neuArmee.get(0));
            if (neuArmee.get(0).getAusdauer() < this.ausdauer) {
                this.setAusdauer(neuArmee.get(0).getAusdauer());
            }
            neuArmee.remove(0);
        }
    }

    /**
     * Methode armeeBewegen
     * 
     * Überprüft ob start Werten kleiner, gleich oder grösser als mitgebene
     * Parameter sind, und setzt die Positionen gemäss Vergleich neu
     * 
     * @param xGoal
     *            xPosition auf dem Spielfeld
     * @param yGoal,
     *            yPosition auf dem Spielfeld
     */

    public boolean armeeBewegen(int xGoal, int yGoal) {

        int distanz = Math.abs(xPos - xGoal) + Math.abs(yPos - yGoal);
        if (distanz <= this.ausdauer) {
            this.xPos = xGoal;
            this.yPos = yGoal;
            return true;
        }
        return false;
    }

    // ------------------------------Getter_Setter------------------------------//

    public List<Mensch> getArmee() {
        return armee;
    }

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

    public Player getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Player besitzer) {
        this.besitzer = besitzer;
    }

    public int getAusdauer() {
        return ausdauer;
    }

    public void setAusdauer(int ausdauer) {
        this.ausdauer = ausdauer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
