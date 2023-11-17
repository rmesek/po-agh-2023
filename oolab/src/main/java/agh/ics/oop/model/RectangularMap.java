package agh.ics.oop.model;

import agh.ics.oop.util.MapVisualizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Vector2d mapBottomLeft;
    private final Vector2d mapTopRight;
    private final MapVisualizer visualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height) {
        this.mapBottomLeft = new Vector2d(0,0);
        this.mapTopRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }
    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        if (oldPosition != animal.getPosition()) {
            animals.remove(oldPosition);
            animals.put(animal.getPosition(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && position.follows(mapBottomLeft) && position.precedes(mapTopRight);
    }

    @Override
    public String toString() {
        return this.visualizer.draw(mapBottomLeft, mapTopRight);
    }
}
