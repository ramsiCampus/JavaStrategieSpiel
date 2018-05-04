//packages
package net.ictcampus.rts.model;

import java.util.ArrayList;

//imports

import java.util.List;

/**
 * Klasse Stadt,
 * 
 * @author lauwrensw
 *
 */

public class Stadt extends GameObject {

    // ---------------------------variable_declaration---------------------------//

    private int xPos;
    private int yPos;
    private String name;
    private List<Mensch> volk = new ArrayList<Mensch>();
    private List<Item> vorrat = new ArrayList<Item>();
    private Player besitzer;
    private int preis;
    @SuppressWarnings("unused")
    private Armee armee;
    
    

    // -------------------------------Constructor--------------------------------//

    public Stadt(String name, int xPos, int yPos, Player spieler, int startKapital, Mensch protMensch) {
        super();
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.besitzer = spieler;
        vorratErzeugen("Geld", startKapital);
        menschKaufen(protMensch, 50);
        

    }

    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    /**
     * 
     * @param mensch
     *            Welcher Menschen typ gekauft werden soll
     * @return boolean ob Transaktion geglückt
     */
    public boolean menschKaufen(Mensch mensch, int mengeMenschen) {
        int preisM = mensch.getPreis().getAnzahl();
        if (preisM > this.getVorratGUI("Geld")) {
            return false;
        }
        for (int i = 0; i < mengeMenschen; i++) {
            for (Item item : vorrat) {
                if (item instanceof Ressource) {
                    if (item.getName().equals("Geld")) {
                        int saldo = this.getVorratGUI("Geld") - preisM;
                        ((Ressource) item).setAnzahl(saldo);
                    }
                }
            }
            Mensch newBuerger = new Mensch(this.besitzer, preisM);
            volk.add(newBuerger);
        }
        return true;
    }

    /**
     * methode menschenDezimieren, wird von Klasse Ereignis aufgerufen, um die
     * Anzahl der Objekt Mensch in der Liste volk um den parameter todesOpfer zu
     * reduzieren. Falls todesopfer grösser als volk ist, wird ganzes volk erlöscht
     * 
     * @param todesOpfer,
     *            anzahl als int von Menschen, die entfernt werden
     */

    public void menschenDezimieren(int todesOpfer) {

        if (todesOpfer >= volk.size()) {
            volk.clear();
        }
        else {
            for (int i = 0; i <= todesOpfer; i++) {
                volk.remove(0);
            }
        }
    }

    public void vorratErzeugen(String ressourceName, int menge) {
        Item r = new Ressource(ressourceName, menge);
        vorrat.add(r);
    }

    public void vorratAddieren(String ressourceName, int menge) {
        if (getVorratGUI(ressourceName) == 0) {
            vorratErzeugen(ressourceName, menge);
        }
        else {
            for (Item i : vorrat) {
                if (i instanceof Ressource) {
                    if (i.getName().equals(ressourceName)) {
                        int mengeNeu = ((Ressource) i).getAnzahl() + menge;
                        ((Ressource) i).setAnzahl(mengeNeu);
                    }
                }
            }
        }
    }

    public void vorratVerringern(String ressourceName, int menge) {
        for (Item i : vorrat) {
            if (i instanceof Ressource) {
                if (i.getName().equals(ressourceName)) {
                    int mengeNeu = ((Ressource) i).getAnzahl() - menge;
                    ((Ressource) i).setAnzahl(mengeNeu);
                }
            }
        }
    }

    public void wirdBetreten(Mensch mensch) {

        if (mensch.getBesitzer().equals(this.besitzer)) {
            for (Item i : mensch.getTasche()) {
                if (i instanceof Ressource) {
                    vorratAddieren("Geld", ((Ressource) i).getAnzahl());
                }
                else {
                    vorrat.add(i);
                }
            }
            mensch.getTasche().clear();

            this.volk.add(mensch);
        }
    }

    public void wirdBetreten(Armee armee) {

        if (armee.getBesitzer().equals(this.besitzer)) {
            for (Mensch mensch : armee.getArmee()) {
                wirdBetreten(mensch);
                armee.getArmee().remove(mensch);
            }
        }
    }

  

    public int getVorratGUI(String itemName) {
        int anzahl = 0;
        for (Item i : vorrat) {
            if (i instanceof Ressource) {
                if (i.getName().equals(itemName))
                    anzahl += ((Ressource) i).getAnzahl();
            }
        }

        return anzahl;
    }

    /**
     * Methode aremeeErzeugen, erzeugt eine Armee mit Mensch Objekten aus der Liste
     * volk. die Anzahl wird über einen Input bestimmt.
     */

    public boolean menschenBewegen(int anzahlMenschen, String name) {
        if (!armeeErzeugen(name, anzahlMenschen)) {
            return false;
        }        
        return true;
    }

    private boolean armeeErzeugen(String name, int anzahlMenschen) {

        if (anzahlMenschen > this.volk.size()) {
            return false;
        }
        armee = new Armee(this.besitzer, name, anzahlMenschen);
        armee.setxPos(this.getxPos());
        armee.setyPos(this.getyPos());
        for (int i = 0; i < anzahlMenschen; i++) {
            this.volk.remove(0);
        }
        return true;
    }
    
    public boolean kaufeStadt(){
        
        int guthaben = getVorratGUI("Geld");
        int kaufpreis = 200;
        
        
        if(guthaben < kaufpreis){
            return false;
        }       
              
                return true;
        
    }

    // ------------------------------Getter_Setter------------------------------//

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Mensch> getVolk() {
        return volk;
    }

    public void setVolk(List<Mensch> volk) {
        this.volk = volk;
    }

    public List<Item> getVorrat() {
        return vorrat;
    }

    public void setVorrat(List<Item> vorrat) {
        this.vorrat = vorrat;
    }

    public Player getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Player besitzer) {
        this.besitzer = besitzer;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;

    }

    public Armee getArmee() {
        return armee;
    }
    

}
