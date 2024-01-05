package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap worldMap;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap worldMap) {
        animals = new ArrayList<>(positions.size());
        for (Vector2d position: positions) {
            try {
                Animal animal = new Animal(position);
                worldMap.place(animal);
                animals.add(animal);
            } catch (PositionAlreadyOccupiedException ex) {
                // Ignore if the place wasn't successful. System.out.println(ex.getMessage());
            }
        }
        this.directions = directions;
        this.worldMap = worldMap;
    }

    @Override
    public void run() {
        int i = 0;
        for (MoveDirection direction: this.directions) {
            Animal animal = animals.get(i);
            worldMap.move(animal,direction);
            i = (i + 1) % animals.size();
        }
    }
}
