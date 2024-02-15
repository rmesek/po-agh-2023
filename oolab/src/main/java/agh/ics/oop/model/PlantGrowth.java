package agh.ics.oop.model;

import java.util.Map;

public interface PlantGrowth {
    Map<Vector2d, Field> prepareFields(MapConfig mapConfig);

    void updateFields(Map<Vector2d, Field> fields);
}
