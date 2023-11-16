package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        WorldMap worldMap = new RectangularMap(5, 5);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        List<MoveDirection> directions = OptionsParser.parse(args);
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5,5));
        simulation.run();
    }
}