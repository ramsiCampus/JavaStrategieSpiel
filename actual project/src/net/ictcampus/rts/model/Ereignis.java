//packages
package net.ictcampus.rts.model;
//imports
import java.util.Random;

/**
 * Ereignis,
 * 
 * @author lauwrensw
 * @version 1.0
 */
public class Ereignis {

    // ---------------------------variable_declaration---------------------------//

    private String name;
    private int todesOpfer;
    private Stadt betroffeneStadt;

    // -------------------------------Constructor--------------------------------//

    public Ereignis(String name, Stadt stadt, int ereignisZahl) {

        this.name = name;
        this.betroffeneStadt = stadt;

        switch (ereignisZahl) {
        case 1:
            ausbruch("Feuer",50, 100);
            break;
        case 2:
            ausbruch("Pest",200, 1000);
            break;
        case 3:
            ausbruch("Tornado",10, 30);
            break;
        case 4:
            ausbruch("Revolte",600, 1000);
            break;
        case 5:
            ausbruch("Erdbeben",80, 300);
        }
        
    }

    // -----------------------------------Main-----------------------------------//
    
    
    // ---------------------------------Methods---------------------------------//

    /**
     * methode feuerAusbruch, ruft Methode menschenDezimieren aus Klass Stadt
     * auf und generiert eine Zufallszahl zwischen 10 und 30, welche die Anzahl
     * an Menschen entfernen
     */
    private void ausbruch(String name,int min, int max) {

        Random randomZahl = new Random();
        todesOpfer = randomZahl.nextInt(max - min) + min;
        betroffeneStadt.menschenDezimieren(todesOpfer);

    }


    // ------------------------------Getter_Setter------------------------------//

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
}
