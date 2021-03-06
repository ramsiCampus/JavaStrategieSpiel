//packages
package net.ictcampus.rts.model;

import java.io.Serializable;
import java.util.ArrayList;

//imports

import java.util.List;

/**
 * Klasse SpielFeld,
 * 
 * @author lauwrensw
 *
 */

public class SpielFeld implements Serializable {

    // ---------------------------variable_declaration---------------------------//

    // private List<Mensch> einheiten;
    private List<Armee> armies = new ArrayList<Armee>();
    private List<Stadt> staedte = new ArrayList<Stadt>();
    private Feld[][] felder;

    // -------------------------------Constructor--------------------------------//

    public SpielFeld(int xLength, int yLength) {

        felder = new Feld[xLength][yLength];

        for (int j = 0; j < yLength; j++) {
            for (int i = 0; i < xLength; i++) {
                Feld feld = new Feld(i, j);
                felder[i][j] = feld;
                if (Math.random() < 0.3) {
                    feld.erzeugeLoot();
                }
            }
        }
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    public boolean stadtBauen(int xPos, int yPos, Player spieler, String name, int startKapital,
            Mensch protMensch) {

        Feld thisField = felder[xPos][yPos];
        Stadt thisCity = thisField.getStadt();

        if (thisCity != null) {
            thisField.erzeugeStadt(name, spieler, startKapital, protMensch);

            staedte.add(thisField.getStadt());
            return true;
        }
        return false;
    }

    public void stadtKaufen(int xPos, int yPos, Player spieler, String name, String ursprungsStadt,
            int startKapital, Mensch protMensch) {

        Feld thisField = felder[xPos][yPos];

        for (Stadt s : staedte) {

            if (s.getBesitzer() == spieler && s.getName() == ursprungsStadt) {

                if (s.kaufeStadt() == true) {
                    thisField.erzeugeStadt(name, spieler, startKapital, protMensch);
                    s.vorratVerringern("Geld", 200);
                }

            }
        }
        staedte.add(thisField.getStadt());

    }

    public Stadt getStadt(int xPos, int yPos) {

        Stadt gefundeneStadt = null;

        for (Stadt s : staedte) {
            if (s.getxPos() == xPos && s.getyPos() == yPos) {

                gefundeneStadt = s;
                break;
            }
        }
        return gefundeneStadt;
    }

    public void armeeBewegen(int xPos, int yPos, Armee armee) {
        int xOld = armee.getxPos();
        int yOld = armee.getyPos();

        if (armee.armeeBewegen(xPos, yPos)) {

            Feld f = this.felder[xOld][yOld];
            if (f.getStadt() == null) {
                this.felder[xOld][yOld].removeArmee(armee);
                
            }
            else {
                this.felder[xOld][yOld].getStadt().setArmee(null);

            }

            armee.setxPos(xPos);
            armee.setyPos(yPos);

            if (this.felder[xPos][yPos].getStadt() != null) {
                this.felder[xPos][yPos].getStadt().wirdBetreten(armee);
            }

            else {
                this.felder[xPos][yPos].wirdBetreten(armee);
            }
        }

    }

    // ------------------------------Getter_Setter------------------------------//

    public List<Armee> getArmies() {
        return armies;
    }

    public List<Stadt> getStaedte() {
        return staedte;
    }

    public Feld[][] getFelder() {
        return felder;
    }

}
