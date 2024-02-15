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

}
