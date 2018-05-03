//packages
package net.ictcampus.rts.model;
//imports

/**
 * Class Item,
 * 
 * @author lauwrensw
 *
 */
public class Item extends GameObject{

    // ---------------------------variable_declaration---------------------------//

    private String name;

    // -------------------------------Constructor--------------------------------//

    public Item(String name) {
        super();
        this.name = name;
    }


    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    // ------------------------------Getter_Setter------------------------------//

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}
