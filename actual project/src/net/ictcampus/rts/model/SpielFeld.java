//packages
package net.ictcampus.rts.model;

import java.util.ArrayList;

//imports

import java.util.List;

/**
 * Klasse SpielFeld,
 * 
 * @author lauwrensw
 *
 */

public class SpielFeld {

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
            }
        }
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    public boolean stadtBauen(int xPos, int yPos, Player spieler, String name) {

        Feld thisField = felder[xPos][yPos];
        Stadt thisCity = thisField.getStadt();

        if (thisCity != null) {
            thisField.erzeugeStadt(name, spieler);
            staedte.add(thisField.getStadt());
            return true;
        }
        return false;
    }

    public void stadtKaufen(int xPos, int yPos, Player spieler, String name,
            String ursprungsStadt) {

        Feld thisField = felder[xPos][yPos];
        for (Stadt s : staedte) {

            if (s.getBesitzer() == spieler && s.getName() == ursprungsStadt) {

                if (s.kaufeStadt() == true) {
                    thisField.erzeugeStadt(name, spieler);
                    staedte.add(thisField.getStadt());

                }

            }
        }

    }
    
    public Stadt getStadt(int xPos, int yPos){
        
        Stadt gefundeneStadt = null;
        
        for(Stadt s : staedte){
            if(s.getxPos() == xPos && s.getyPos() == yPos){
                
                gefundeneStadt = s;
                break;
            }
        }
        return gefundeneStadt;
    }

    public void armeeBewegen(int xPos, int yPos, Armee armee) {

        armee.armeeBewegen(xPos, yPos);

    }

    // ------------------------------Getter_Setter------------------------------//

    public List<Armee> getArmies() {
        return armies;
    }

    public List<Stadt> getStaedte() {
        return staedte;
    }

}
