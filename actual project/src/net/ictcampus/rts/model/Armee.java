//packages
package net.ictcampus.rts.model;

//imports

import java.util.List;
import java.util.ArrayList;

/**
 * Klasse Armee
 * 
 * @author lauwrensw
 *
 */

public class Armee extends GameObject {

    // ---------------------------variable_declaration---------------------------//

    private int xPos;
    private int yPos;
    private String name;
    private List<Mensch> menschen;
    private int anzahlMenschen;
    private List<Mensch> armee = new ArrayList<Mensch>();

    // -------------------------------Constructor--------------------------------//

    public Armee(String name, int anzahlMenschen, List<Mensch> menschen) {

        name = this.name;
        anzahlMenschen = this.anzahlMenschen;
        menschen = this.menschen;
       

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * Methode, menschenEinfuegen, fügt anhand mitgegebene Paramter im Konstrukt Mensch Objekte in die Liste armee hinein.
     */
    public void menschenEinfuegen() {

        for (int i = 0; i <= anzahlMenschen; i++) {
            armee.add(menschen.get(i));
        }

    }
    
    /**
     * Methode armeeBewegen, MUSS NOCH ÜBERARBEITET UND DURCHDACHT WERDEN
     * @param xPos
     * @param yPos
     */
    
    public void armeeBewegen(int xPos, int yPos){
        
       int startX = getxPos();
       int startY = getyPos();  
       
       xPos = startX + xPos;
       yPos = startY + yPos;
       
       setxPos(xPos);
       setyPos(yPos);
       
      
    }
  
    // ------------------------------Getter_Setter------------------------------//
    
    public List<Mensch> getArmee() {
        return armee;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
    
    


}
