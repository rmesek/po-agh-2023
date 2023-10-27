package agh.ics.oop.model;

import agh.ics.oop.model.Vector2d;

public enum MapDirection {
    NORTH(new Vector2d(0, 1), "Północ"),
    EAST(new Vector2d(1, 0), "Wschód"),
    SOUTH(new Vector2d(0, -1), "Południe"),
    WEST(new Vector2d(-1, 0), "Zachód");

    private final Vector2d vector2d;
    private final String directionString;

    private MapDirection(Vector2d vector2d, String directionString) {
        this.vector2d = vector2d;
        this.directionString = directionString;
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
}
