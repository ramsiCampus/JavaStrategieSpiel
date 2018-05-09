package net.ictcampus.rts.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenschTest {

    private static Player p;
    private static Mensch m;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        p = new Player("Tester", 0);
        m = new Mensch(p, 10);
        m.setxPos(5);
        m.setyPos(5);
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        p = null;
        m = null;
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testMensch() {
        assertEquals(m.getBesitzer(), p);
        assertEquals(m.getPreis().getAnzahl(), 10);

    }
    
    @Test
    void testBewegen() {
        assertEquals(m.bewegen(6, 5),true);
        assertEquals(m.bewegen(5, 5),true);
        assertEquals(m.bewegen(5, 6),true);
        assertEquals(m.bewegen(5, 5),true);
        assertEquals(m.bewegen(6, 6),false);
        assertEquals(m.bewegen(7, 5),false);
        assertEquals(m.bewegen(3, 5),false);
    }
    
    /*
    @Test
    void testAufnehmen() {
        fail("Not yet implemented");
    }
    */

}
