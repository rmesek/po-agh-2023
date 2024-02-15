package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class ForestedEquator implements PlantGrowth {
    private static final int JUNGLE_PERCENTAGE = 20;
    @Override
    public Map<Vector2d, Field> prepareFields(MapConfig mapConfig, WorldMap worldMap) {
        Map<Vector2d, Field> fields = new HashMap<>();
        int middleY = mapConfig.mapHeight() / 2;
        int bottomY = middleY - (mapConfig.mapHeight() * JUNGLE_PERCENTAGE / 200);
        int topY = middleY + (mapConfig.mapHeight() * JUNGLE_PERCENTAGE / 200);
        for (int y = 0; y < mapConfig.mapHeight(); y++) {
            for (int x = 0; x < mapConfig.mapWidth(); x++) {
                Vector2d position = new Vector2d(x, y);
                if (y >= bottomY && y <= topY) {
                    fields.put(position, new Field(worldMap, FieldType.JUNGLE));
                    worldMap.notifyEventListeners("New jungle field at " + position.toString());
                } else {
                    fields.put(position, new Field(worldMap, FieldType.NORMAL));
                    worldMap.notifyEventListeners("New normal field at " + position.toString());
                }
            }
        }
        return fields;
    }

    @Override
    public void updateFields(WorldMap worldMap) {
        // do nothing
    }
}
