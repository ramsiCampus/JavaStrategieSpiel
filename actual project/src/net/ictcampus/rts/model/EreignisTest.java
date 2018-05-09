package net.ictcampus.rts.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EreignisTest {
    
    private static Stadt s;
    private static Mensch pm;
    private static Player p;
    private static List<Mensch> lm;
    private static Ereignis e;
    
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        p = new Player("Tester", 0);
        pm = new Mensch(p,10);
        s = new Stadt("Teststadt", 5, 5, p, 20, pm);
        lm = new ArrayList<Mensch>();
        for(int i = 0; i<500; i++) {
            lm.add(pm);
        }
    
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testEreignis() {
        
        resetStadt();
        e = new Ereignis("Pest", s, 1);
        assertEquals(s.getVolk().size()<=499,true);
        assertEquals(s.getVolk().size()>=478,true);
        
        
        resetStadt();
        e = new Ereignis("Pest", s, 2);
        assertEquals(s.getVolk().size()<500,true);
        assertEquals(s.getVolk().size()>9,true);
        
        resetStadt();
        e = new Ereignis("Pest", s, 3);
        assertEquals(s.getVolk().size()<=499,true);
        assertEquals(s.getVolk().size()>=485,true);
        
        resetStadt();
        e = new Ereignis("Pest", s, 4);
        assertEquals(s.getVolk().size()<496,true);
        assertEquals(s.getVolk().size()>450,true);
        
        resetStadt();
        e = new Ereignis("Pest", s, 5);
        assertEquals(s.getVolk().size()<495,true);
        assertEquals(s.getVolk().size()>469,true);
        
    }
    
    private static void resetStadt() {
        List<Mensch>lmk = new ArrayList<Mensch>();
        for(Mensch m : lm) {
            lmk.add(m);
        }
        
        s.setVolk(lmk);
    }

}
