package agh.ics.oop.model;

import agh.ics.oop.World;
import agh.ics.oop.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private Vector2d mapBottomLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private Vector2d mapTopRight = new Vector2d(0,0);
    private Vector2d limitBottomLeft = new Vector2d(0,0);
    private Vector2d limitTopRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private final MapVisualizer visualizer = new MapVisualizer(this);

    public GrassField(int grassCount) {
        int maxRange = (int) sqrt((long) grassCount * 10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxRange, maxRange, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
//            System.out.println(grassPosition);
            grasses.put(grassPosition, new Grass(grassPosition));
        }
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
        return this.animals.containsKey(position) || this.grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement worldElement = animals.get(position);
        if (worldElement != null) {
            return worldElement;
        }
        return grasses.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position) && position.follows(limitBottomLeft) && position.precedes(limitTopRight);
    }

    @Override
    public String toString() {
        updateBoundaries();
        return this.visualizer.draw(mapBottomLeft, mapTopRight);
    }

    private void updateBoundaries() {
        for (Vector2d animalPosition: animals.keySet()) {
            mapTopRight = mapTopRight.upperRight(animalPosition);
            mapBottomLeft = mapBottomLeft.lowerLeft(animalPosition);
        }
        for (Vector2d grassPosition: grasses.keySet()) {
            mapTopRight = mapTopRight.upperRight(grassPosition);
            mapBottomLeft = mapBottomLeft.lowerLeft(grassPosition);
        }
    }
}
