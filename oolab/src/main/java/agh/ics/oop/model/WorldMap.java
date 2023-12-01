package agh.ics.oop.model;

import java.util.Collection;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap extends MoveValidator {

    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map.
     */
    void place(Animal animal) throws PositionAlreadyOccupiedException;

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(Animal animal, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return a world element at a given position.
     *
     * @param position The position of the world element.
     * @return world element or null if the position is not occupied.
     */
    WorldElement objectAt(Vector2d position);

    /**
     * Return collection of all world elements on the map.
     *
     * @return collection of all world elements on the map.
     */
    Collection<WorldElement> getElements();
}
