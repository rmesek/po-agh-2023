package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final int width;
    private final int height;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Field> fields = new HashMap<>();


    public WorldMap(MapConfig mapConfig) {
        this.width = mapConfig.mapWidth();
        this.height = mapConfig.mapHeight();

        prepareFields();
    }

    private void prepareFields() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < height; x++) {
                Vector2d position = new Vector2d(y, x);
                fields.put(position, new Field(position, FieldType.NORMAL));
            }
        }
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
