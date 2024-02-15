package agh.ics.oop.model;

import java.util.Map;

public interface PlantGrowth {
    Map<Vector2d, Field> prepareFields(MapConfig mapConfig);

    Map<Vector2d, Field> updateFields(MapConfig mapConfig, Map<Vector2d, Field> fields);
}
