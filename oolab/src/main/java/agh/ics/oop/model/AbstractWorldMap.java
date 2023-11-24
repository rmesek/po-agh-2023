package agh.ics.oop.model;

import agh.ics.oop.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    protected final Map<Vector2d, WorldElement> animals = new HashMap<>();
    protected Vector2d mapBottomLeft;
    protected Vector2d mapTopRight;

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
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
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return visualizer.draw(mapBottomLeft, mapTopRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position) && position.follows(mapBottomLeft) && position.precedes(mapTopRight);
    }

    @Override
    public Collection<WorldElement> getElements() {
        return Collections.unmodifiableCollection(animals.values());
    }
}
