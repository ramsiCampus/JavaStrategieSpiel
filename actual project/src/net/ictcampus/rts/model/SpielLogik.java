//packages
package net.ictcampus.rts.model;

//imports

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasse SpielLogik,
 * 
 * @author lauwrensw
 *
 */

public class SpielLogik {

    // ---------------------------variable_declaration---------------------------//

    private Spiel spiel;
    private SpielFeld feld;
    private Mensch protMensch;
    private List<Player> spieler = new ArrayList<Player>();
    private int max = 5;
    private int min = 1;
    private Stadt stadt;

    // -------------------------------Constructor--------------------------------//

    public SpielLogik(Player playingSpieler) {
        
        spieler.add(new Player("testperson1", 1));
        spieler.add(new Player("testperson2", 2));
        
        this.spiel = new Spiel(20, 10, spieler.get(0), spieler.get(1));
        this.feld = spiel.getSpielFeld();
        
        this.protMensch = new Mensch(null, 10);
        
        

    }

    // -----------------------------------Main-----------------------------------//
    
  
    // ---------------------------------Methods---------------------------------//
    
    public boolean commandAusfuehren(int int1, int int2) {
        return false;

    }

    public void spielerHinzufuegen(Player spieler) {

    }
    
    public void armeeBewegen(int xPos, int yPos, Armee armee){
        feld.armeeBewegen(xPos, yPos, armee);
    }
    
    
    
    public void menschenKaufen(){
        feld.getStaedte().get(0).menschKaufen(protMensch);
    }
    
    public void stadtBauen(int xPos, int yPos, Player player, int spielerGeld, String name ){
        
        feld.stadtBauen(xPos, yPos, player, spielerGeld, name);
    }
    
    public void ressourcenSammeln(){
        
    }
    
    
    public  void ereignisGenerieren(){
        
        int zufallszahl;
        Random randomZahl = new Random();
        zufallszahl = randomZahl.nextInt(max - min) + min;   
               
        Ereignis ereignis = new Ereignis("test", feld.getStaedte().get(0),zufallszahl);
        
    }
    
    

    // ------------------------------Getter_Setter------------------------------//

}