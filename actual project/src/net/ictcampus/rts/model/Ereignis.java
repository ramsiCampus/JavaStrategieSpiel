//packages
package net.ictcampus.rts.model;
//imports

/**
 * Ereignis,
 * 
 * @author lauwrensw
 * @version 1.0
 */
public class Ereignis {

    // ---------------------------variable_declaration---------------------------//

    private String name;
    private int staerke;
    private Player betroffeneSpieler;

    // -------------------------------Constructor--------------------------------//
    
    public Ereignis(String name, int staerke, Player spieler){
        
        this.name = name;
        this.staerke = staerke;
        this.betroffeneSpieler = spieler;
      
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    // ------------------------------Getter_Setter------------------------------//

}
