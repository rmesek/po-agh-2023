package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals(){
        // given
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(0,1);

        // when
        Vector2d v1Equal = new Vector2d(0,0);
        Vector2d v2Equal = new Vector2d(0,1);

        // then
        assertEquals(v1, v1Equal);
        assertNotEquals(v1, v2);
        assertEquals(v2, v2Equal);
    }

    @Test
    public void testToString(){
        // given
        Vector2d v = new Vector2d(0, 1);

        // when
        String s = "(0,1)";

        // then
        assertEquals(v.toString(), s);
    }

    @Test
    public void testPrecedes(){
        // given
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 2);
        Vector2d v3 = new Vector2d(2, 3);

        // when

        // then
        assertTrue(v1.precedes(v1));
        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v3));

        assertTrue(v2.precedes(v3));

        assertFalse(v3.precedes(v2));
        assertFalse(v3.precedes(v1));

        assertFalse(v2.precedes(v1));
    }

    @Test
    public void testFollows(){
        // given
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 2);
        Vector2d v3 = new Vector2d(2, 3);

        // when

        // then
        assertTrue(v3.follows(v3));
        assertTrue(v3.follows(v2));
        assertTrue(v3.follows(v1));

        assertTrue(v2.follows(v1));

        assertFalse(v1.follows(v2));
        assertFalse(v1.follows(v3));

        assertFalse(v2.follows(v3));
    }

    @Test
    public void testUpperRight(){
        // given
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(1, 2);

        // when
        Vector2d upperRight = new Vector2d(2, 2);

        // then
        assertEquals(v1.upperRight(v2), upperRight);
        assertEquals(v2.upperRight(v1), upperRight);
    }

    @Test
    public void testLowerLeft(){
        // given
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(1, 2);

        // when
        Vector2d lowerLeft = new Vector2d(1, 1);

        // then
        assertEquals(v1.lowerLeft(v2), lowerLeft);
        assertEquals(v2.lowerLeft(v1), lowerLeft);
    }

    @Test
    public void testAdd(){
        // given
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(-1, -2);

        // when

        // then
        assertEquals(v1.add(v2), new Vector2d(1,-1));
        assertEquals(v2.add(v1), new Vector2d(1,-1));

        assertEquals(v1.add(v1), new Vector2d(4,2));
        assertEquals(v2.add(v2), new Vector2d(-2,-4));
    }

    @Test
    public void testSubtract(){
        // given
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(-1, -2);

        // when

        // then
        assertEquals(v1.subtract(v2), new Vector2d(3,3));
        assertEquals(v2.subtract(v1), new Vector2d(-3,-3));

        assertEquals(v1.subtract(v1), new Vector2d(0,0));
        assertEquals(v2.subtract(v2), new Vector2d(0,0));
    }

    @Test
    public void testOpposite(){
        // given
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(-1, -2);

        // when

        // then
        assertEquals(v1.opposite(), new Vector2d(-2, -1));
        assertEquals(v2.opposite(), new Vector2d(1, 2));
    }
}
