package agh.ics.oop.model;

import agh.ics.oop.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    protected final Map<Vector2d, WorldElement> animals = new HashMap<>();
    private final List<MapChangeListener> mapChangeListeners = new ArrayList<>();
    private final UUID id;

    protected AbstractWorldMap() {
        this.id = UUID.randomUUID();
    }

    @Override
    public void subscribe(MapChangeListener listener) {
        mapChangeListeners.add(listener);
    }

    @Override
    public void unsubscribe(MapChangeListener listener) {
        mapChangeListeners.remove(listener);
    }

    private void mapChanged(String s) {
        for (MapChangeListener listener : mapChangeListeners) {
            listener.mapChanged(this, s);
        }
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if (!canMoveTo(animal.getPosition())) {
            throw new PositionAlreadyOccupiedException(animal.getPosition());
        }
        animals.put(animal.getPosition(), animal);
        mapChanged("New animal at " + animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        MapDirection oldDirection = animal.getDirection();
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        if (oldPosition != animal.getPosition()) {
            animals.remove(oldPosition);
            animals.put(animal.getPosition(), animal);
            mapChanged("Animal moved from " + oldPosition + " to " + animal.getPosition());
        }
        if (oldDirection != animal.getDirection()) {
            mapChanged("Animal changed direction from " + oldDirection + " to " + animal.getDirection());
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

    @Override
    public String toString() {
        Boundary boundary = getCurrentBounds();
        return visualizer.draw(boundary.BottomLeftVec(), boundary.TopRightVec());
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
