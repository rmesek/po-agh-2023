package agh.ics.oop.model;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(%d,%d)".formatted(this.x, this.y);
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.getX() && this.y <= other.getY();
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.getX() && this.y >= other.getY();
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.getX(), this.y + other.getY());
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.getX(), this.y - other.getY());
    }

    public Vector2d upperRight(Vector2d other) {
        int biggerX = this.x >= other.getX() ? this.x : other.getX();
        int biggerY = this.y >= other.getY() ? this.y : other.getY();
        return new Vector2d(biggerX, biggerY);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int smallerX = this.x <= other.getX() ? this.x : other.getX();
        int smallerY = this.y <= other.getY() ? this.y : other.getY();
        return new Vector2d(smallerX, smallerY);
    }

    public Vector2d opposite() {
        return new Vector2d(-1*this.x, -1*this.y);
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
