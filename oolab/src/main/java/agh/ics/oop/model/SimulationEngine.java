package agh.ics.oop.model;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SimulationEngine {
    private final List<EventListener> eventListeners = new LinkedList<>();
    private final List<DayChangeListener> dayChangeListeners = new LinkedList<>();
    private WorldMap worldMap;
    private int trackedAnimalId = -1;  // TODO: Trackowanie tylko w UI?

    private boolean isRunning = false;
    private int delay = 0;

    public SimulationEngine(MapConfig mapConfig) {
        prepareSimulation();

        notifyDayChangeListeners(worldMap);
    }

    private void prepareSimulation() {
    }

    public void setTrackedAnimalId(int id) {
        trackedAnimalId = id;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void run() throws InterruptedException {
        while (true) {
            if (!isRunning) {
                sleep(100);
                continue;
            }

            removeDeadAnimals();
            moveAnimals();
            eatPlants();
            reproduceAnimals();
            growPlants();

            notifyDayChangeListeners(worldMap);
            sleep(delay);
        }
    }

    private void removeDeadAnimals() {
    }

    private void moveAnimals() {
    }

    private void eatPlants() {
    }

    private void reproduceAnimals() {
    }

    private void growPlants() {
    }

    public void addEventListener(EventListener listener) {
        eventListeners.add(listener);
    }

    public void notifyEventListeners(String event) {
        for (EventListener listener : eventListeners) {
            listener.update(event);
        }
    }

    public void addDayChangeListener(DayChangeListener listener) {
        dayChangeListeners.add(listener);
    }

    public void notifyDayChangeListeners(WorldMap worldMap) {
        for (DayChangeListener listener : dayChangeListeners) {
            listener.dayPassed(worldMap);
        }
    }
}
