package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    public void testPlaceEmpty() {
        // given
        GrassField grassField = new GrassField(0);
        Vector2d vector2d1 = new Vector2d(0,0);
        Vector2d vector2d2 = new Vector2d(-1, 0);
        Animal animal1 = new Animal(vector2d1);
        Animal animal2 = new Animal(vector2d2);

        // when
        assertTrue(grassField.place(animal1));
        assertTrue(grassField.place(animal2));
        // then
        assertTrue(animal1 == grassField.objectAt(vector2d1));
        assertTrue(animal2 == grassField.objectAt(vector2d2));
    }

    @Test
    public void testGrassNumber() {
        // given
        GrassField grassField = new GrassField(10);
        int grassFound = 0;

        // when
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grassField.isOccupied(new Vector2d(i, j))) grassFound++;
            }
        }

        // then
        assertEquals(10, grassFound);
    }

    @Test
    public void testGrassNumber10Times() {
        for (int i = 0; i < 10; i++) testGrassNumber();
    }

    @Test
    public void testMoves() {
        // given
        GrassField grassField = new GrassField(10);
        Vector2d vector2d1 = new Vector2d(0,0);
        Vector2d vector2d2 = new Vector2d(0,1);
        Animal animal1 = new Animal(vector2d1);
        Animal animal2 = new Animal(vector2d2);

        // when
        assertTrue(grassField.place(animal1));
        assertTrue(grassField.place(animal2));

        // then
        grassField.move(animal1, MoveDirection.FORWARD);
        assertTrue(animal1 == grassField.objectAt(vector2d1));

        grassField.move(animal2, MoveDirection.FORWARD);
        assertTrue(animal2 == grassField.objectAt(new Vector2d(0,2)));

        grassField.move(animal2, MoveDirection.RIGHT);
        grassField.move(animal2, MoveDirection.FORWARD);
        assertTrue(animal2 == grassField.objectAt(new Vector2d(1, 2)));

        grassField.move(animal1, MoveDirection.FORWARD);
        assertTrue(animal1 == grassField.objectAt(new Vector2d(0, 1)));

        grassField.move(animal1, MoveDirection.FORWARD);
        assertTrue(animal1 == grassField.objectAt(new Vector2d(0, 2)));
    }
}
