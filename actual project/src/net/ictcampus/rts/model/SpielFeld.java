//packages
package net.ictcampus.rts.model;

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

    private List<Mensch> einheiten;
    private List<Stadt> staedte;
    private Feld[][] felder;

    // -------------------------------Constructor--------------------------------//

    public SpielFeld(int xLength, int yLength) {

        felder = new Feld[xLength][yLength];

        for (int i = 0; i <= xLength; i++) {
            for (int j = 0; j <= yLength; j++) {
                Feld feld = new Feld(i, j);
                felder[i][j] = feld;
            }

        }

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    public void stadtBauen(int xPos, int yPos, Player spieler, int spielerGeld, String name) {

        int bezahlt;
        int kosten = felder[xPos][yPos].getStadt().getPreis();
        Feld thisField = felder[xPos][yPos];
        Stadt thisCity = thisField.getStadt();

        if (thisCity != null && kosten >= spielerGeld) {
            thisField.erzeugeStadt(name, spieler);
            bezahlt = (spieler.getTestgeld() - kosten);
            spieler.setTestgeld(bezahlt);
        }
    }

    public void menschBewegen(int xPos, int yPos, Mensch mensch) {

    }

    // ------------------------------Getter_Setter------------------------------//
}
