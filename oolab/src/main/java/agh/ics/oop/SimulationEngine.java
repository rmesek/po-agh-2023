package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> simulationTasks = new LinkedList<>();
    private ExecutorService executorService;

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

        if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("Time limit exceeded");
            executorService.shutdownNow();
        }
    }

    public void runAsyncInThreadPool() {
        executorService = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulations) {
            executorService.submit(simulation);
        }
        executorService.shutdown();
    }
}
