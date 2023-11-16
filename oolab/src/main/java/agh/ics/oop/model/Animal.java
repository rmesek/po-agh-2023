package agh.ics.oop.model;

public class Animal {
    private Vector2d position;
    private MapDirection direction;

    static final Vector2d MAP_BOTTOM_LEFT = new Vector2d(0, 0);
    static final Vector2d MAP_TOP_RIGHT = new Vector2d(4,4);

    public Animal(Vector2d position, MapDirection direction) {
        this.position = position;
        this.direction = direction;
    }
    public Animal() {
        this(new Vector2d(2,2), MapDirection.NORTH);
    }

    public Animal(Vector2d position) {
        this(position, MapDirection.NORTH);
    }

    public Animal(MapDirection direction) {
        this(new Vector2d(2,2), direction);
    }

    public String toString() {
        return "Pozycja: %s; Orientacja: %s".formatted(this.position.toString(), this.direction.toString());
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d moveVector;
        Vector2d newPosition;
        switch (direction){
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case FORWARD:
                moveVector = this.direction.toUnitVector();
                newPosition = this.position.add(moveVector);
                if (newPosition.follows(MAP_BOTTOM_LEFT) && newPosition.precedes(MAP_TOP_RIGHT)) {
                    this.position = newPosition;
                }
                break;
            case BACKWARD:
                moveVector = this.direction.toUnitVector();
                newPosition = this.position.subtract(moveVector);
                if (newPosition.follows(MAP_BOTTOM_LEFT) && newPosition.precedes(MAP_TOP_RIGHT)) {
                    this.position = newPosition;
                }
                break;
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getDirection() {
        return direction;
    }
}
