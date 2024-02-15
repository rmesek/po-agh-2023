package agh.ics.oop.model;

import java.util.*;

public class CreepingJungle implements PlantGrowth {
    private static final int JUNGLE_PERCENTAGE = 20;

    @Override
    public Map<Vector2d, Field> prepareFields(MapConfig mapConfig, WorldMap worldMap) {
        Map<Vector2d, Field> fields = new HashMap<>();
        List<Vector2d> positions = new ArrayList<>(mapConfig.mapHeight() * mapConfig.mapWidth());
        int totalFields = mapConfig.mapHeight() * mapConfig.mapWidth();
        int jungleFields = Math.floorDiv(totalFields, 100 / JUNGLE_PERCENTAGE);
        for (int x = 0; x < mapConfig.mapWidth(); x++) {
            for (int y = 0; y < mapConfig.mapHeight(); y++) {
                Vector2d position = new Vector2d(x, y);
                positions.add(position);
            }
        }

        Collections.shuffle(positions);
        for (Vector2d position : positions) {
            if (jungleFields > 0) {
                fields.put(position, new Field(worldMap, FieldType.JUNGLE));
                jungleFields--;
            } else {
                fields.put(position, new Field(worldMap, FieldType.NORMAL));
            }
        }
        return fields;
    }

    @Override
    public void updateFields(WorldMap worldMap) {
        var fields = worldMap.getFields();
        for (Field field : fields.values()) {
            field.setType(FieldType.NORMAL);
        }
        for (Field field : fields.values()) {
            for (MapDirection direction : MapDirection.values()) {
                Vector2d neighbourPosition = field.getPosition().add(direction.toUnitVector());
                if (fields.containsKey(neighbourPosition) && field.hasGrass()) {
                    fields.get(neighbourPosition).setType(FieldType.JUNGLE);
                }
            }
        }
    }
}
