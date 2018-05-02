//packages
package net.ictcampus.rts.model;

//imports
import java.util.List;

/**
 * Feld
 * @author lauwrensw
 *
 */

public class Feld {

  //---------------------------variable_declaration---------------------------//
    private int xPos;
    private int yPos;
    private List<Mensch> einheiten;
    private List<Item> loot;
    private Stadt stadt;
    
  //-------------------------------Constructor--------------------------------//

    public Feld(int xPos, int yPos) {
        xPos = this.xPos;
        yPos = this.yPos;
    }
    
  //-----------------------------------Main-----------------------------------//
    

  //---------------------------------Methods---------------------------------//
    
    /**
     * wirdBetreten, 
     * @param einheiten
     */

    public void wirdBetreten(Mensch einheiten) {

    }
    
    /**
     * lootAufnehmen,
     * @param einheiten
     * @return
     */

    public Item lootAufnehmen(Mensch einheiten) {
        return null;

    }
    
    /**
     * erzeugeLoot,
     */

    public void erzeugeLoot() {

    }

}
