package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.LinkedList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        List<Simulation> simulations = new LinkedList<>();
        MapChangeListener listener = new ConsoleMapDisplay();
        try {
            for (int i = 0; i < 500; ++i) {
                simulations.add(prepareSimulation(args, new GrassField(10), listener));
                simulations.add(prepareSimulation(args, new RectangularMap(5,5), listener));
            }
            SimulationEngine simulationEngine = new SimulationEngine(simulations);

            simulationEngine.runAsyncInThreadPool();
            simulationEngine.awaitSimulationsEnd();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted: " + e.getMessage());
        }
        System.out.println("System zakończył działanie");
    }

    private static Simulation prepareSimulation(String[] args, WorldMap map, MapChangeListener listener) {
        map.subscribe(listener);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<MoveDirection> directions = OptionsParser.parse(args);
        return new Simulation(positions, directions, map);
    }
}