package agh.ics.oop.model;

public class Field {
    private final Vector2d position;
    private FieldType type = FieldType.NORMAL;
    private boolean hasGrass = false;

    public Field(Vector2d position, FieldType type) {
        this.position = position;
        this.type = type;
    }

    public Vector2d getPosition() {
        return position;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public boolean hasGrass() {
        return hasGrass;
    }

    public void setGrass() {
        hasGrass = true;
    }

    @Override
    public String toString() {
        return getPosition().toString() + " " + type.toString();
    }
}