package agh.cs.po.lab2;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    Vector2d v = new Vector2d(1, 2);



    @Test
    void testToString() {
        assertEquals("(1,2)", v.toString());
    }

    @Test
    void precedes() {
        assertTrue(v.precedes(new Vector2d(1, 2)));
        assertFalse(v.precedes(new Vector2d(0, 2)));
        assertFalse(v.precedes(new Vector2d(1, 0)));
        assertFalse(v.precedes(new Vector2d(0, 0)));
        assertTrue(v.precedes(new Vector2d(1, 3)));
        assertTrue(v.precedes(new Vector2d(3, 2)));
        assertTrue(v.precedes(new Vector2d(3, 3)));

    }

    @Test
    void follows() {
        assertTrue(v.follows(new Vector2d(1, 2)));
        assertTrue(v.follows(new Vector2d(0, 2)));
        assertTrue(v.follows(new Vector2d(1, 0)));
        assertTrue(v.follows(new Vector2d(0, 0)));
        assertFalse(v.follows(new Vector2d(1, 3)));
        assertFalse(v.follows(new Vector2d(2, 2)));
        assertFalse(v.follows(new Vector2d(3, 3)));
    }

    @Test
    void upperRight() {
        // v = (1,2)

        Vector2d m = v.upperRight(new Vector2d(3, 4));
        assertEquals(3, m.x);
        assertEquals(4, m.y);

        m = v.upperRight(new Vector2d(0, 0));
        assertEquals(1, m.x);
        assertEquals(2, m.y);

        m = v.upperRight(new Vector2d(-1, 4));
        assertEquals(1, m.x);
        assertEquals(4, m.y);

        m = v.upperRight(new Vector2d(3, -1));
        assertEquals(3, m.x);
        assertEquals(2, m.y);
    }

    @Test
    void lowerLeft() {
        // v = (1,2)

        Vector2d m = v.lowerLeft(new Vector2d(3, 4));
        assertEquals(1, m.x);
        assertEquals(2, m.y);

        m = v.lowerLeft(new Vector2d(0, 0));
        assertEquals(0, m.x);
        assertEquals(0, m.y);

        m = v.lowerLeft(new Vector2d(-1, 4));
        assertEquals(-1, m.x);
        assertEquals(2, m.y);

        m = v.lowerLeft(new Vector2d(3, -1));
        assertEquals(1, m.x);
        assertEquals(-1, m.y);
    }

    @Test
    void add() {
        //v = (1,2)
        Vector2d m = v.add(new Vector2d(3, 4));
        assertEquals(4, m.x);
        assertEquals(6, m.y);

    }

    @Test
    void subtract() {
        //v = (1,2)
        Vector2d m = v.subtract(new Vector2d(3, 4));
        assertEquals(-2, m.x);
        assertEquals(-2, m.y);
    }

    @Test
    void equals() {
        //V = (1,2)
        assertTrue(v.equals(new Vector2d(1, 2)));
        assertTrue(v.equals(v));
        assertFalse(v.equals(new Vector2d(3, 4)));
        assertFalse(v.equals(" "));

    }

    @Test
    void opposite() {
        //v = (1,2)
        Vector2d m = v.opposite();
        assertEquals(-1, m.x);
        assertEquals(-2, m.y);
    }
}