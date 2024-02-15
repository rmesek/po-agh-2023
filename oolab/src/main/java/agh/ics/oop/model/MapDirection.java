package agh.ics.oop.model;

public enum MapDirection {
    NORTH(new Vector2d(0, 1), 0, "N"),
    NORTHEAST(new Vector2d(1, 1), 45, "NE"),
    EAST(new Vector2d(1, 0), 90, "E"),
    SOUTHEAST(new Vector2d(1, -1), 135, "SE"),
    SOUTH(new Vector2d(0, -1), 180, "S"),
    SOUTHWEST(new Vector2d(-1, -1), 225, "SW"),
    WEST(new Vector2d(-1, 0), 270, "W"),
    NORTHWEST(new Vector2d(-1, 1), 315, "NW");

    private final Vector2d vector2d;
    private final double directionDegrees;
    private final String shortString;

    MapDirection(Vector2d vector2d, double directionDegrees, String shortString) {
        this.vector2d = vector2d;
        this.directionDegrees = directionDegrees;
        this.shortString = shortString;
    }

    public String toString() {
        return shortString;
    }

    public MapDirection next(int n) {
        return MapDirection.values()[(this.ordinal() + n) % 8];
    }

    public MapDirection opposite() {
        return MapDirection.values()[(this.ordinal() + 4) % 8];
    }

    public Vector2d toUnitVector() {
        return vector2d;
    }

    public double toDegrees() {
        return directionDegrees;
    }
}