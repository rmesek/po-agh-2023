package agh.ics.oop.model;

import agh.ics.oop.model.Vector2d;

public enum MapDirection {
    NORTH(new Vector2d(0, 1), "Północ", "N"),
    EAST(new Vector2d(1, 0), "Wschód", "E"),
    SOUTH(new Vector2d(0, -1), "Południe", "S"),
    WEST(new Vector2d(-1, 0), "Zachód", "W");

    private final Vector2d vector2d;
    private final String directionString;
    private final String shortString;

    private MapDirection(Vector2d vector2d, String directionString, String shortString) {
        this.vector2d = vector2d;
        this.directionString = directionString;
        this.shortString = shortString;
    }

    public String toString(){
        return this.directionString;
    }

    public MapDirection next(){
        return MapDirection.values()[(this.ordinal() + 1) % 4];
    }

    public MapDirection previous(){
        return MapDirection.values()[(this.ordinal() + 3) % 4];
    }

    public Vector2d toUnitVector(){
        return this.vector2d;
    }

    public String getShortString() {
        return shortString;
    }
}
