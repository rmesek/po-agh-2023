package agh.ics.oop.model;

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

        // then
        assertThrows(PositionAlreadyOccupiedException.class, () -> rectangularMap.place(animal));
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
        try {
            rectangularMap.place(animal1);
        } catch (PositionAlreadyOccupiedException e) {
            fail(e);
        }

        // then
        Exception exception1 = assertThrows(PositionAlreadyOccupiedException.class, () -> rectangularMap.place(animal1));
        String expectedMessage = "Position (2,3) is already occupied";
        String actualMessage1 = exception1.getMessage();

        assertTrue(actualMessage1.contains(expectedMessage));

        Exception exception2 = assertThrows(PositionAlreadyOccupiedException.class, () -> rectangularMap.place(animal2));
        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage2.contains(expectedMessage));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i != 2 && j != 3) {
                    assertNull(rectangularMap.objectAt(new Vector2d(i, j)));
                } else if (i == 2 && j == 3) {
                    assertSame(animal1, rectangularMap.objectAt(new Vector2d(i, j)));
                    assertNotSame(animal2, rectangularMap.objectAt(new Vector2d(i, j)));
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
        try {
            rectangularMap.place(animal1);
            rectangularMap.place(animal2);
        } catch (PositionAlreadyOccupiedException e) {
            fail(e);
        }

        // then
        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertSame(animal1, rectangularMap.objectAt(vector2d1));

        rectangularMap.move(animal2, MoveDirection.FORWARD);
        assertSame(animal2, rectangularMap.objectAt(vector2d2));

        rectangularMap.move(animal2, MoveDirection.RIGHT);
        rectangularMap.move(animal2, MoveDirection.FORWARD);
        assertSame(animal2, rectangularMap.objectAt(new Vector2d(1, 1)));

        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertSame(animal1, rectangularMap.objectAt(new Vector2d(0, 1)));

        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertSame(animal1, rectangularMap.objectAt(new Vector2d(0, 1)));
    }
}
