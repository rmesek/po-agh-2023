package agh.ics.oop.model;

import agh.ics.oop.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    Animal animal;
    Vector2d vector2d = new Vector2d(2,2);

    @BeforeEach
    void setUp() {
        animal = new Animal(vector2d);
    }

    @Test
    public void testOrientation() {
        // given
        List<MoveDirection> moves = new LinkedList<>();
        moves.add(MoveDirection.LEFT);
        moves.add(MoveDirection.RIGHT);
        moves.add(MoveDirection.RIGHT);
        // when
        for (MoveDirection move: moves) {
            animal.move(move);
        }
        // then
        assertEquals(MapDirection.EAST, animal.getDirection());
    }

    @Test
    public void testPosition() {
        // given
        List<MoveDirection> moves = new LinkedList<>();
        moves.add(MoveDirection.FORWARD);
        moves.add(MoveDirection.LEFT);
        moves.add(MoveDirection.BACKWARD);
        moves.add(MoveDirection.RIGHT);
        moves.add(MoveDirection.RIGHT);
        moves.add(MoveDirection.FORWARD);
        // when
        for (MoveDirection move: moves) {
            animal.move(move);
        }
        // then
        Vector2d position = new Vector2d(4,3);
        assertTrue(animal.isAt(position));
    }

    @Test
    public void testBoundaries() {
        // given
        List<MoveDirection> moves = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            moves.add(MoveDirection.FORWARD);
        }
        for (int i = 0; i < 10; i++) {
            moves.add(MoveDirection.BACKWARD);
        }
        moves.add(MoveDirection.RIGHT);
        for (int i = 0; i < 10; i++) {
            moves.add(MoveDirection.FORWARD);
        }
        for (int i = 0; i < 10; i++) {
            moves.add(MoveDirection.BACKWARD);
        }
        // when
        for (MoveDirection move: moves) {
            animal.move(move);
        }
        // then
        Vector2d position = new Vector2d(0,0);
        assertTrue(animal.isAt(position));
    }
}
