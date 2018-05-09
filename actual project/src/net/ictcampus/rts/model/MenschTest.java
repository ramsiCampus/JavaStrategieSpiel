//package net.ictcampus.rts.model;
//
//import static org.junit.Assert.assertEquals;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class MenschTest {
//
//    private static Player p;
//    private static Mensch m;
//    private static Feld f;
//    private static Ressource r;
//    private static List<Item> l;
//
//    @BeforeAll
//    static void setUpBeforeClass() throws Exception {
//        p = new Player("Tester", 0);
//        m = new Mensch(p, 10);
//        m.setxPos(5);
//        m.setyPos(5);
//        f = new Feld(5, 5);
//        r = new Ressource("Geld", 100);
//        l = new ArrayList<Item>();
//        l.add(r);
//        f.setLoot(l);
//
//    }
//
//    @AfterAll
//    static void tearDownAfterClass() throws Exception {
//        p = null;
//        m = null;
//        f = null;
//        l = null;
//    }
//
//    @BeforeEach
//    void setUp() throws Exception {
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//    }
//
//    @Test
//    void testMensch() {
//        /*
//         * Standard Konstruktor test
//         */
//        assertEquals(m.getBesitzer(), p);
//        assertEquals(m.getPreis().getAnzahl(), 10);
//
//    }
//
//    @Test
//    void testBewegen() {
//        /*
//         * verschiedene Bewegungen testen
//         */
//        m.setAusdauer(1);
//        assertEquals(m.bewegen(6, 5), true);
//        assertEquals(m.bewegen(5, 5), true);
//        assertEquals(m.bewegen(5, 6), true);
//        assertEquals(m.bewegen(5, 5), true);
//        assertEquals(m.bewegen(6, 6), false);
//        assertEquals(m.bewegen(7, 5), false);
//        assertEquals(m.bewegen(3, 5), false);
//    }
//
//    @Test
//    void testAufnehmen() {
//        /*
//         * Feld mit 100 Geld plündern
//         */
//        m.aufnehmen(f);
//        assertEquals(f.getAnzahlRessource(),50);
//        int anzahl = 0;
//        for (Item i : m.getTasche()) {
//            if (i instanceof Ressource) {
//                if (i.getName().equals("Geld"))
//                    anzahl += ((Ressource) i).getAnzahl();
//            }
//        }
//        assertEquals(anzahl,50);
//        
//        /*
//         * Feld mit 50 geld leeren
//         */
//        m.aufnehmen(f);
//        assertEquals(f.getLoot().size(),0);
//        anzahl = 0;
//        for (Item i : m.getTasche()) {
//            if (i instanceof Ressource) {
//                if (i.getName().equals("Geld"))
//                    anzahl += ((Ressource) i).getAnzahl();
//            }
//        }
//        assertEquals(anzahl,100);
//        
//        /*
//         * leeres feld looten
//         */
//        m.aufnehmen(f);
//        assertEquals(f.getLoot().size(),0);
//        anzahl = 0;
//        for (Item i : m.getTasche()) {
//            if (i instanceof Ressource) {
//                if (i.getName().equals("Geld"))
//                    anzahl += ((Ressource) i).getAnzahl();
//            }
//        }
//        assertEquals(anzahl,100);
//    }
//
//}
