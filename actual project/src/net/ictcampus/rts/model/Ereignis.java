//packages

package net.ictcampus.rts.model;
//imports

import java.util.Random;

/**
 * Klasse Ereignis
 * 
 * @author lauwrensw
 * @version 2.0
 */

public class Ereignis {

    // ---------------------------variable_declaration---------------------------//

    private String name;
    private int todesOpfer;
    private Stadt betroffeneStadt;

    // -------------------------------Constructor--------------------------------//

    /**
     * Konstruktor Ereignis
     * 
     * Anhand param ereignisZahl wird entshieden welche Parameter dem Methode
     * ausbruch() mitgegeben werden
     * 
     * @param name
     *            Name des Ereignisses
     * @param stadt
     *            Objekt Stadt, die vom Ereignis betroffen wird
     * @param ereignisZahl
     *            Zufallszahl zwischen 1 und 5
     */

    public Ereignis(String name, Stadt stadt, int ereignisZahl) {

        this.name = name;
        this.betroffeneStadt = stadt;

        switch (ereignisZahl) {
        case 1:
            ausbruch("Feuer", 1, 22);
            break;
        case 2:
            ausbruch("Pest", 100, 490);
            break;
        case 3:
            ausbruch("Tornado", 1, 15);
            break;
        case 4:
            ausbruch("Revolte", 5, 49);
            break;
        case 5:
            ausbruch("Erdbeben", 6, 30);
        }
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * methode ausbruch
     * 
     * ruft Methode menschenDezimieren aus Klasse Stadt auf welche die Anzahl an
     * Menschen entfernen
     * 
     * @param name,
     *            name des Ausbruches
     * @param min,
     *            mindest Anzahl an Todesopfern
     * @param max,
     *            höchste Anzahl an Todesopfern
     */

    private void ausbruch(String name, int min, int max) {

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
