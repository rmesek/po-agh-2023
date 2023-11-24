package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomPositionGeneratorTest {
    List<Vector2d> positions;

    @BeforeEach
    void setUp() {
        this.positions = new ArrayList<>();
    }
    @Test
    public void testEmpty() {
        // given
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(0,0, 0);
        List<Vector2d> solutionPositions = new ArrayList<>();

        // when
        for(Vector2d position : randomPositionGenerator) {
            positions.add(position);
        }

        // then
        assertEquals(solutionPositions, positions);
    }

    @Test
    public void testFull() {
        // given
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(1,1, 1);
        List<Vector2d> solutionPositions = new ArrayList<>();
        solutionPositions.add(new Vector2d(0,0));

        // when
        for(Vector2d position : randomPositionGenerator) {
            positions.add(position);
        }

        // then
        assertEquals(solutionPositions, positions);


    }

    @Test
    public void testSize() {
        // given
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(4,4, 16);
        int solutionNumber = 16;

        // when
        for(Vector2d position : randomPositionGenerator) {
            positions.add(position);
        }

        // then
        assertEquals(solutionNumber, positions.size());
    }

    @Test
    public void testUnique() {
        // given
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(3,3, 9);
        int solutionNumber = 9;

        // when
        for(Vector2d position : randomPositionGenerator) {
            positions.add(position);
        }

        // then
        assertEquals(positions.size(), new HashSet<>(positions).size());
        assertEquals(solutionNumber, positions.size());
    }

    @Test
    public void testRandom() {
        // given
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(3,3, 8);
        int solutionNumber = 8;

        // when
        for(Vector2d position : randomPositionGenerator) {
            positions.add(position);
        }

        // then
        assertEquals(positions.size(), new HashSet<>(positions).size());
        assertEquals(solutionNumber, positions.size());
    }

    @Test
    public void testTooMuch() {
        // TODO
    }
}
