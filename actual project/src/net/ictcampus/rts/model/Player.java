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
    

  //-------------------------------Constructor--------------------------------//

   

    public Player(String name, int ID){
        this.name = name;
        this.ID = ID;
        
    }
  //-----------------------------------Main-----------------------------------//

  //---------------------------------Methods---------------------------------//
    
    
  //------------------------------Getter_Setter------------------------------//
    
   

    public int getID() {
        return ID;
    }

    

}
