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
    private int stadtPreis;

    // -------------------------------Constructor--------------------------------//

    public SpielFeld(int xLength, int yLength) {

        this.stadtPreis = 200;
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
                    s.vorratVerringern("Geld", stadtPreis);
                    thisField.erzeugeStadt(name, spieler, startKapital, protMensch);
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
        Armee armeeOldi;

        if (armee.armeeBewegen(xPos, yPos)) {
            System.out.println("sventest7");
            List<Armee> neuArmeen = this.felder[xPos][yPos].getEinheiten();
            neuArmeen.add(armee);

            Feld f = this.felder[xOld][yOld];
            if (f.getStadt() == null) {
                List<Armee> oldArmeen = this.felder[xOld][yOld].getEinheiten();
                oldArmeen.remove(oldArmeen.indexOf(armee));
            }
            else {
                this.felder[xOld][yOld].getStadt().setArmee(null);

            }

            armee.setxPos(xPos);
            armee.setyPos(yPos);

            if (this.felder[xPos][yPos].getStadt() != null) {
                this.felder[xPos][yPos].getStadt().wirdBetreten(armee);
            }

            if (this.felder[xPos][yPos].getLoot().size() != 0) { //FEHLER HIER; MANCHMANL IST DIESES IF TRUE, DIES SOLLTE ES GLAUBE ICH NICHT SEIN. LG SVEN
                System.out.println("sventest8");
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

    public void setStadtPreis(int stadtPreis) {
        this.stadtPreis = stadtPreis;
    }
}
