package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            GrassField grassField = new GrassField(10);
            grassField.subscribe(new ConsoleMapDisplay());
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            List<MoveDirection> directions = OptionsParser.parse(args);
            Simulation simulation = new Simulation(positions, directions, grassField);
            simulation.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}