package net.ictcampus.rts.model;

import java.util.List;

public class Feld {
    
    private int xPos;
    private int yPos;
    private List<Mensch> einheiten;
    private List<Item> loot;
    private Stadt stadt;
    
    public Feld(int xPos, int yPos){
        xPos = this.xPos;
        yPos = this.yPos;
    }
    
    public void wirdBetreten(Mensch einheiten){
        
    }
    
    public Item lootAufgenommen(Mensch einheiten){
        return null;
        
    }
    
    public void erzeugeLoot(){
        
    }

}
