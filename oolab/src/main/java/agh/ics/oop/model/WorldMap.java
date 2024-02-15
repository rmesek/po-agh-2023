package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final int width;
    private final int height;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Field> fields = new HashMap<>();


    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void moveAnimal(Animal animal) {
        Vector2d oldPosition = animal.getPosition();
        MapDirection oldOrientation = animal.getOrientation();

        Vector2d newPosition = oldPosition.add(oldOrientation.toUnitVector());
        MapDirection newOrientation = oldOrientation;
        if (newPosition.getY() < 0 || newPosition.getY() >= height) {
            newPosition = new Vector2d(newPosition.getX(), oldPosition.getY());
            newOrientation = oldOrientation.opposite();
        }
        if (newPosition.getX() < 0) {
            newPosition = new Vector2d(width - 1, newPosition.getY());
        }
        if (newPosition.getX() >= width) {
            newPosition = new Vector2d(0, newPosition.getY());
        }

        animal.setPosition(newPosition, newOrientation);
    }

}
