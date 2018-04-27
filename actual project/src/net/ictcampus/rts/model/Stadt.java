package net.ictcampus.rts.model;

import java.util.List;

public class Stadt {

    private int xPos;
    private int yPos;
    private String name;
    private List<Mensch> volk;
    private List<Item> vorrat;
    private Player besitzer;
    private int preis;
   
    public Stadt(int xPos, int yPos, Player spieler) {
        
    }
    
    public void menschKaufen(Mensch mensch) {
        
    }
    
    public void vorratErzeugen(Ressource ressource, int menge) {
        
    }
    
    public void vorratAddieren(Ressource ressource, int menge) {
        
    }
    
    public void wirdBetreten(Mensch mensch) {
        
    }
    
    public int checkVorrat(Ressource ressource) {
        return 0;
        
    }
}
