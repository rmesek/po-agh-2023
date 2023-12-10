package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.LinkedList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        List<Simulation> simulations = new LinkedList<>();
        try {
            simulations.add(prepareGrassField(args));
            simulations.add(prepareRectangularMap(args));
            SimulationEngine simulationEngine = new SimulationEngine(simulations);

            simulationEngine.runSync();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Simulation prepareGrassField(String[] args) throws IllegalArgumentException {
        GrassField grassField = new GrassField(10);
        grassField.subscribe(new ConsoleMapDisplay());
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<MoveDirection> directions = OptionsParser.parse(args);
        return new Simulation(positions, directions, grassField);
    }
    private static Simulation prepareRectangularMap(String[] args) throws IllegalArgumentException {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        rectangularMap.subscribe(new ConsoleMapDisplay());
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<MoveDirection> directions = OptionsParser.parse(args);
        return new Simulation(positions, directions, rectangularMap);
    }
}