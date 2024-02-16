package agh.ics.oop.model;

import java.util.List;

public class Field {
    private final WorldMap worldMap;
    private FieldType type;
    private boolean hasGrass = false;

    public Field(WorldMap worldMap, FieldType type) {
        this.worldMap = worldMap;
        this.type = type;
    }

    public Vector2d getPosition() {
        var worldMapFields = worldMap.getFields();
        for (Vector2d position : worldMapFields.keySet()) {
            if (worldMapFields.get(position) == this) {
                return position;
            }
        }
        throw new RuntimeException("Field not found in worldMap fields.");  // TODO: should never happen
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) { // czy na pewno?
        this.type = type;
    }

    public boolean hasGrass() {
        return hasGrass;
    }

    public void setGrass(boolean hasGrass) {
        this.hasGrass = hasGrass;
    }

    @Override
    public String toString() {
        return getPosition().toString() + " " + type.toString() + " " + hasGrass;
    }
}