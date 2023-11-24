package agh.ics.oop.model;

import agh.ics.oop.World;
import agh.ics.oop.util.MapVisualizer;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, WorldElement> grasses = new HashMap<>();
    private Vector2d boundaryBottomLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private Vector2d boundaryTopRight = new Vector2d(0,0);


    public GrassField(int grassCount) {
        mapBottomLeft = new Vector2d(0,0);
        mapTopRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
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

    @Override
    public String toString() {
        updateBoundaries();
        return visualizer.draw(boundaryBottomLeft, boundaryTopRight);
    }

    private void updateBoundaries() {
        for (Vector2d animalPosition: animals.keySet()) {
            boundaryTopRight = boundaryTopRight.upperRight(animalPosition);
            boundaryBottomLeft = boundaryBottomLeft.lowerLeft(animalPosition);
        }
        for (Vector2d grassPosition: grasses.keySet()) {
            boundaryTopRight = boundaryTopRight.upperRight(grassPosition);
            boundaryBottomLeft = boundaryBottomLeft.lowerLeft(grassPosition);
        }
    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> combinedCollection = new LinkedList<>(grasses.values());
        combinedCollection.addAll(super.getElements());
        return Collections.unmodifiableCollection(combinedCollection);
    }
}
