package net.ictcampus.rts.controller;

import java.io.Serializable;

/**
 * SmallSerial, Testmethode mit einem "kleinen" Objekt, zum überprüfen, ob wir
 * das mit dem Server an den Client senden können
 * 
 * @author kerstingk
 * @version 1.0
 */
public class SmallSerial implements Serializable {
    // ---------------------------variable_declaration---------------------------//
    public String name;
    public int zahl;

    // -------------------------------Constructor--------------------------------//
    public SmallSerial() {
        name = "Halllo";
        zahl = 283;
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    // ------------------------------Getter_Setter------------------------------//

}
