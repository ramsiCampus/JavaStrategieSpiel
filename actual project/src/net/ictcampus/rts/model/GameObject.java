//packages
package net.ictcampus.rts.model;
//imports
import java.util.ArrayList;
import java.util.List;
/**
 * GameObject,
 * 
 * @author lauwrensw
 * @version 1.0
 */
public class GameObject {

    // ---------------------------variable_declaration---------------------------//

    private static int counter;
    private List<GameObject> alleObj = new ArrayList<GameObject>();
    private int staerke;
    private int ID;

    // -------------------------------Constructor--------------------------------//

    public GameObject() {
        counter++;
        this.ID = counter;
        alleObj.add(this);
    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    // ------------------------------Getter_Setter------------------------------//

    public int getID() {
        return ID;
    }
}
