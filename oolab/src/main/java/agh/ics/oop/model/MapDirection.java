package agh.ics.oop.model;

public enum MapDirection {
    NORTH(new Vector2d(0, 1), "North", "N"),
    NORTHEAST(new Vector2d(1, 1), "Northeast", "NE"),
    EAST(new Vector2d(1, 0), "East", "E"),
    SOUTHEAST(new Vector2d(1, -1), "Southeast", "SE"),
    SOUTH(new Vector2d(0, -1), "South", "S"),
    SOUTHWEST(new Vector2d(-1, -1), "Southwest", "SW"),
    WEST(new Vector2d(-1, 0), "West", "W"),
    NORTHWEST(new Vector2d(-1, 1), "Northwest", "NW");

    private final Vector2d vector2d;
    private final String directionString;
    private final String shortString;

    MapDirection(Vector2d vector2d, String directionString, String shortString) {
        this.vector2d = vector2d;
        this.directionString = directionString;
        this.shortString = shortString;
    }

    public String toString() {
        return shortString;
    }

    public MapDirection next() {
        return MapDirection.values()[(this.ordinal() + 1) % 8];
    }

    public MapDirection previous() {
        return MapDirection.values()[(this.ordinal() + 7) % 8];
    }

    public Vector2d toUnitVector() {
        return this.vector2d;
    }

    public String toDirectionString() {
        return directionString;
    }
}