package net.ictcampus.rts.view;


import java.util.ArrayList;
import java.util.List;

import net.ictcampus.rts.model.*;

public class Testdata {

    //PLAYER
    Player p1 = new Player("Flo",1);
    Player p2 = new Player("Sven",2);
    //SPIEL
    Spiel spiel = new Spiel(20,10,p1,p2);

    public Spiel getSpiel(){
        return spiel;
    }
}
