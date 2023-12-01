package agh.ics.oop.model;

import agh.ics.oop.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    protected final Map<Vector2d, WorldElement> animals = new HashMap<>();

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if (!canMoveTo(animal.getPosition())) {
            throw new PositionAlreadyOccupiedException(animal.getPosition());
        }
        animals.put(animal.getPosition(), animal);
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
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return new LinkedList<>(animals.values());
    }
}
