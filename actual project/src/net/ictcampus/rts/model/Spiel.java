//packages
package net.ictcampus.rts.model;

import java.io.Serializable;
import java.util.ArrayList;

//imports

import java.util.List;

/**
 * Klasse Spiel,
 * 
 * @author lauwrensw
 *
 */

public class Spiel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4113321948974371347L;
    // ---------------------------variable_declaration---------------------------//
    private SpielFeld spielFeld;
    private List<Player> spieler = new ArrayList<Player>();
    private Mensch protMensch;
    private int stadtPreis;
    private int stadtStartkapital;

    // -------------------------------Constructor--------------------------------//

    public Spiel(int xLength, int yLength, Player spieler1, Player spieler2, Mensch protMensch) {

        this.spielFeld = new SpielFeld(xLength, yLength);
        this.spieler.add(spieler1);
        this.spieler.add(spieler2);
        this.stadtPreis = 200;
        this.stadtStartkapital = 1000;
        this.protMensch = protMensch;
        
        
        
        String s = "playerOneStadt";
        
        int x = 8;
        int y = 3;

        for (Player i : spieler) {
            
            initStadt(i, x, y, stadtStartkapital, s, protMensch);
            x = x + 3;
            y = y + 4;
            s = "playerTwoStadt";
        }
        initStadt(spieler1,3,3,stadtStartkapital,s,protMensch);

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    private void initStadt(Player spieler, int xPosStadt, int yPosStadt, int startKapital,
            String stadtName, Mensch protMensch) {

        Stadt stadt = new Stadt(stadtName, xPosStadt, yPosStadt, spieler, startKapital, protMensch);
        spielFeld.getStaedte().add(stadt);
        spielFeld.getFelder()[xPosStadt][yPosStadt].setStadt(stadt);

    }

    

    // ------------------------------Getter_Setter------------------------------//

    public SpielFeld getSpielFeld() {
        return spielFeld;
    }

    public List<Player> getSpieler() {
        return spieler;
    }

    public void setProtMensch(Mensch protMensch) {
        this.protMensch = protMensch;
    }
    
    public Mensch getProtMensch() {
        return protMensch;
    }

    public int getMenschPreis() {
        return this.protMensch.getPreis().getAnzahl();
    }

    public Player getPlayerByID(int id) {

        Player gesuchteSpieler = null;

        for (Player p : spieler) {
            if (id == p.getID()) {
                gesuchteSpieler = p;
                break;
            }
        }

        return gesuchteSpieler;
    }
    
    public int getStadtPreis() {

        return stadtPreis;
    }


    public int getStadtStartkapital() {
        return stadtStartkapital;
    }

    public void setStadtStartkapital(int stadtStartkapital) {
        this.stadtStartkapital = stadtStartkapital;
    }
}
