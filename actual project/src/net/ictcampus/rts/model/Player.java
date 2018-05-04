//packages
package net.ictcampus.rts.model;
//imports

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import java.util.List;
//
//import com.sun.javafx.collections.MappingChange.Map;

/**
 * Player,
 * 
 * @author lauwrensw
 * @version 1.0
 */
public class Player implements Serializable{

    // ---------------------------variable_declaration---------------------------//

    private String name;
    private static List<Integer> alleID = new ArrayList<Integer>();
    private int ID;
    

  //-------------------------------Constructor--------------------------------//

   

    public Player(String name, int ID){
        this.setName(name);
        this.ID = ID;
        alleID.add(ID);
    }
  //-----------------------------------Main-----------------------------------//

  //---------------------------------Methods---------------------------------//
    
    
  //------------------------------Getter_Setter------------------------------//
    
   

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}
