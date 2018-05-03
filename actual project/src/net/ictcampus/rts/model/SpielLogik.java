//packages
package net.ictcampus.rts.model;

//imports

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        spieler.add(new Player("testperson1", 1));
        spieler.add(new Player("testperson2", 2));

        this.spiel = new Spiel(20, 10, spieler.get(0), spieler.get(1));
        this.spielfeld = spiel.getSpielFeld();

        this.protMensch = new Mensch(null, 10);
        spiel.setProtMensch(protMensch);

    }

    // -----------------------------------Main-----------------------------------//

    public static void main(String[] args) {
        SpielLogik sl = new SpielLogik();
        Testframe tf = new Testframe();
        for(Player p : sl.spiel.getSpieler()){
            System.out.println(p.getName());
        }
        for (Stadt i : sl.spielfeld.getStaedte()) {
            System.out.println(i.getName());
            System.out.println(i.getBesitzer());
            System.out.println(i.getVorrat());
            
        }
        
       
//       sl.ereignisGenerieren();
        System.out.println(sl.spiel.getMenschPreis());
       
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

    public void menschenKaufen(Player playingSpieler, Stadt stadt) {

        if (stadt.getBesitzer() == playingSpieler) {
            stadt.menschKaufen(protMensch);
        }

    }

    public void kaufeStadt(int xPos, int yPos, Player playingSpieler, int spielerGeld, String name,
            String ursprungsStadtName) {

        spielfeld.stadtKaufen(xPos, yPos, playingSpieler, name, ursprungsStadtName);
    }

    public void ressourcenSammeln() {

    }

    public Ereignis ereignisGenerieren() {

        int zufallszahl;
        Random randomZahl = new Random();
        zufallszahl = randomZahl.nextInt(max - min) + min;
        Ereignis ereignis = new Ereignis("test", spielfeld.getStaedte().get(0), zufallszahl);
        return ereignis;

    }

    // ------------------------------Getter_Setter------------------------------//

}