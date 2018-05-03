//packages
package net.ictcampus.rts.model;
//imports

import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * Player,
 * 
 * @author lauwrensw
 * @version 1.0
 */
public class Player {

    // ---------------------------variable_declaration---------------------------//

    private String name;
    private int[] alleID;
    private int ID;
    private List<Ressource> ressourcen;       // Anschauen!!!
    private int testgeld;

  //-------------------------------Constructor--------------------------------//

   

    public Player(String name, int ID){
        this.name = name;
        this.ID = ID;
        
    }
  //-----------------------------------Main-----------------------------------//

  //---------------------------------Methods---------------------------------//
    
    
  //------------------------------Getter_Setter------------------------------//
    
    public int getTestgeld() {
        return testgeld;
    }

    public int getID() {
        return ID;
    }

    public void setTestgeld(int testgeld) {
        this.testgeld = testgeld;
    }
    
    

}
