package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> simulationTasks = new LinkedList<>();

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            simulationTasks.add(new Thread(simulation));
        }

        for (Thread simulationTask : simulationTasks) {
            simulationTask.start();
        }
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread thread : simulationTasks) {
            thread.join();
        }
    }
}
