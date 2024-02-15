package agh.ics.oop.model;

public class Animal {
    private Vector2d position;
    private MapDirection orientation;
    private int energy;
    public Animal(Vector2d initialPosition, MapDirection initialOrientation, int energy, MapConfig mapConfig) {
        setPosition(initialPosition, initialOrientation);
        this.energy = energy;
    }

    public void setPosition(Vector2d position, MapDirection orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }
}
