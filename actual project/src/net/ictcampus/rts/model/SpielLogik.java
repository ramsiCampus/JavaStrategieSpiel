//packages
package net.ictcampus.rts.model;

//imports

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

import net.ictcampus.rts.view.Testframe;

/**
 * Klasse SpielLogik,
 * 
 * @author lauwrensw
 *
 */

public class SpielLogik {

    // ---------------------------variable_declaration---------------------------//

    private Spiel spiel;

    private SpielFeld spielfeld;
    private Mensch protMensch;
    private List<Player> spieler = new ArrayList<Player>();
    private int max = 5;
    private int min = 1;

    // -------------------------------Constructor--------------------------------//

    public SpielLogik() {

        spieler.add(new Player("Game",0));
        spieler.add(new Player("Winnie", 1));
        spieler.add(new Player("rämsi", 2));

        this.protMensch = new Mensch(spieler.get(0), 10);        
        
        this.spiel = new Spiel(20, 10, spieler.get(1), spieler.get(2), protMensch);
        
        this.spielfeld = spiel.getSpielFeld();

        

    }

    // -----------------------------------Main-----------------------------------//

    public static void main(String[] args) {
        SpielLogik sl = new SpielLogik();
        for(Player p : sl.spiel.getSpieler()){
            System.out.println(p.getName());
        }
        
       
        
        
        
        for (Stadt i : sl.spielfeld.getStaedte()) {
            System.out.println(i.getName());
            System.out.println(i.getVorratGUI("Geld"));
            System.out.println(i.getxPos());
            System.out.println(i.getyPos());
            
        }
        
      sl.spielfeld.getStadt(8, 3).menschenBewegen(10);
      System.out.println(sl.spielfeld.getStadt(8, 3).getArmee().getArmee().size());
      System.out.println(sl.spielfeld.getStadt(8, 3).getVolk().size());
       
       sl.ereignisGenerieren();
       System.out.println(sl.spielfeld.getStadt(8, 3).getVolk().size());
       
       Testframe tf = new Testframe(sl);
       
//        System.out.println(sl.spiel.getMenschPreis());
       
    }

    // ---------------------------------Methods---------------------------------//

    public boolean commandAusfuehren(int int1, int int2) {
        return false;

    }

    public void spielerHinzufuegen(Player playingSpieler) {

        spieler.add(playingSpieler);

    }

    public void armeeBewegen(int xPos, int yPos, Armee armee, Player playingSpieler) {

        if (armee.getBesitzer() == playingSpieler) {
            spielfeld.armeeBewegen(xPos, yPos, armee);
        }
    }

    public void menschenKaufen(Player playingSpieler, Stadt stadt, int mengeMenschen) {

        if (stadt.getBesitzer() == playingSpieler) {
            stadt.menschKaufen(protMensch,mengeMenschen);
        }

    }

    public void kaufeStadt(int xPos, int yPos, Player playingSpieler, int spielerGeld, String name,
            String ursprungsStadtName, int startKapital, Mensch protMensch) {

        spielfeld.stadtKaufen(xPos, yPos, playingSpieler, name, ursprungsStadtName, startKapital, protMensch);
    }

    public Ereignis ereignisGenerieren() {

        int zufallszahl;
        Random randomZahl = new Random();
        zufallszahl = randomZahl.nextInt(max - min) + min;
        Ereignis ereignis = new Ereignis("test", spielfeld.getStaedte().get(0), zufallszahl);
        return ereignis;

    }

    // ------------------------------Getter_Setter------------------------------//

    public Spiel getSpiel() {
        return spiel;
    }
}