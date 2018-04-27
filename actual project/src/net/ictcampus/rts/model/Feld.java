package net.ictcampus.rts.model;

public class Feld {
    
    private int xPos;
    private int yPos;
    private List<Mensch> einheiten = new ArrayList<Mensch>();
    private List<Item> loot = new ArrayList<Item>();
    private Stadt stadt = new Stadt();
    
    public Feld(int xPos, int yPos){
        xPos = this.xPos;
        yPos = this.yPos;
    }
    
    public void wirdBetreten(Mensch einheiten){
        
    }
    
    public Item lootAufgenommen(Mensch einheiten){
        
    }
    
    public void erzeugeLoot(){
        
    }

}
