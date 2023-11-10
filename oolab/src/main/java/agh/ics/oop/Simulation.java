package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> directions;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions) {
        animals = new ArrayList<>(positions.size());
        for (Vector2d position: positions) {
            animals.add(new Animal(position));
        }
        this.directions = directions;
    }

    public void run() {
        int i = 0;
        for (MoveDirection direction: this.directions) {
            Animal animal = animals.get(i);
            animal.move(direction);
            System.out.printf("Zwierzę %d : %s%n", i, animal.toString());
            i = (i + 1) % animals.size();
        }
    }
}
