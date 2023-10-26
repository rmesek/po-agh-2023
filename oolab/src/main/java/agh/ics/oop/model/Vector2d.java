package agh.ics.oop.model;

public record Vector2d(int x, int y) {

    public String toString() {
        return "(%d,%d)".formatted(this.x, this.y);
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.x() && this.y <= other.y();
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x() && this.y >= other.y();
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x(), this.y + other.y());
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x(), this.y - other.y());
    }

    public Vector2d upperRight(Vector2d other) {
        int biggerX = this.x >= other.x() ? this.x : other.x();
        int biggerY = this.y >= other.y() ? this.y : other.y();
        return new Vector2d(biggerX, biggerY);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int smallerX = this.x <= other.x() ? this.x : other.x();
        int smallerY = this.y <= other.y() ? this.y : other.y();
        return new Vector2d(smallerX, smallerY);
    }

    public Vector2d opposite() {
        return new Vector2d(-1 * this.x, -1 * this.y);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;

        return this.x == that.x() && this.y == that.y();
    }
}
