
//packages
package net.ictcampus.rts.model;
//imports

import java.util.ArrayList;
import java.util.List;

/**
* Mensch,
* 
* @author lauwrensw
* @version 1.0
*/
public class Mensch extends GameObject{

  // ---------------------------variable_declaration---------------------------//

  private int xPos;
  private int yPos;
  private int lebenspunkt;
  private List<Item> tasche = new ArrayList<Item>();
  private Player besitzer = new Player();
  private int preis;

//-------------------------------Constructor--------------------------------//
  
  public Mensch(Player besitzer, int preis){
      super();
      besitzer = this.besitzer;
      preis = this.preis;
  }

//-----------------------------------Main-----------------------------------//

//---------------------------------Methods---------------------------------//
  
  public boolean bewegen(int xPos, int yPos){
      return true;
  }
  
  public void aufnehmen(Item tasche){
      
  }
  
  
//------------------------------Getter_Setter------------------------------//

}
