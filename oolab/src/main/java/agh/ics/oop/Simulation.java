package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> directions;
    private WorldMap worldMap;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap worldMap) {
        animals = new ArrayList<>(positions.size());
        for (Vector2d position: positions) {
            Animal animal = new Animal(position);
            if (worldMap.place(animal)) {
                animals.add(animal);
            }
        }
        this.directions = directions;
        this.worldMap = worldMap;
    }

    public void run() {
        int i = 0;
        for (MoveDirection direction: this.directions) {
            Animal animal = animals.get(i);
            worldMap.move(animal,direction);
            System.out.println(worldMap);
            i = (i + 1) % animals.size();
        }
    }
}
