package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void testPlaceEmpty() {
        // given
        RectangularMap rectangularMap = new RectangularMap(0,0);
        Vector2d vector2d = new Vector2d(0,0);
        Animal animal = new Animal(vector2d);

        // when
        assertFalse(rectangularMap.place(animal));
        // then
        assertNull(rectangularMap.objectAt(vector2d));
    }

    @Test
    public void testPlaceSamePosition() {
        // given
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Vector2d vector2d = new Vector2d(2,3);
        Animal animal1 = new Animal(vector2d);
        Animal animal2 = new Animal(vector2d);

        // when
        assertTrue(rectangularMap.place(animal1));
        assertFalse(rectangularMap.place(animal1));
        assertFalse(rectangularMap.place(animal2));

        // then
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i != 2 && j != 3) {
                    assertNull(rectangularMap.objectAt(new Vector2d(i, j)));
                } else if (i == 2 && j == 3) {
                    assertTrue(animal1 == rectangularMap.objectAt(new Vector2d(i, j)));
                    assertFalse(animal2 == rectangularMap.objectAt(new Vector2d(i, j)));
                }
            }
        }
    }

    @Test
    public void testMoves() {
        // given
        RectangularMap rectangularMap = new RectangularMap(2,2);
        Vector2d vector2d1 = new Vector2d(0,0);
        Vector2d vector2d2 = new Vector2d(0,1);
        Animal animal1 = new Animal(vector2d1);
        Animal animal2 = new Animal(vector2d2);

        // when
        assertTrue(rectangularMap.place(animal1));
        assertTrue(rectangularMap.place(animal2));

        // then
        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertTrue(animal1 == rectangularMap.objectAt(vector2d1));

        rectangularMap.move(animal2, MoveDirection.FORWARD);
        assertTrue(animal2 == rectangularMap.objectAt(vector2d2));

        rectangularMap.move(animal2, MoveDirection.RIGHT);
        rectangularMap.move(animal2, MoveDirection.FORWARD);
        assertTrue(animal2 == rectangularMap.objectAt(new Vector2d(1, 1)));

        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertTrue(animal1 == rectangularMap.objectAt(new Vector2d(0, 1)));

        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertTrue(animal1 == rectangularMap.objectAt(new Vector2d(0, 1)));
    }
}
