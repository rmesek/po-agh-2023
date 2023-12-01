package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, WorldElement> grasses = new HashMap<>();

    private Boundary boundary = new Boundary(new Vector2d(0,0), new Vector2d(0,0));

    public GrassField(int grassCount) {
        int maxRange = (int) sqrt((long) grassCount * 10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxRange, maxRange, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement worldElement = super.objectAt(position);
        if (worldElement != null) {
            return worldElement;
        }
        return grasses.get(position);
    }

    private void updateBoundaries() {
        for (Vector2d animalPosition: animals.keySet()) {
            this.boundary = new Boundary(boundary.BottomLeftVec().lowerLeft(animalPosition), boundary.TopRightVec().upperRight(animalPosition));
        }
        for (Vector2d grassPosition: grasses.keySet()) {
            this.boundary = new Boundary(boundary.BottomLeftVec().lowerLeft(grassPosition), boundary.TopRightVec().upperRight(grassPosition));
        }
    }

    @Override
    public List<WorldElement> getElements() {
        LinkedList<WorldElement> combinedCollection = new LinkedList<>(grasses.values());
        combinedCollection.addAll(super.getElements());
        return combinedCollection;
    }

    @Override
    public Boundary getCurrentBounds() {
        updateBoundaries();
        return this.boundary;
    }
}
