package agh.ics.oop.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class SimulationEngine {
    private static final AnimalComparator ANIMAL_COMPERATOR = new AnimalComparator();
    private static final int ENERGY_LOSS_PER_DAY = 1;
    private final List<DayChangeListener> dayChangeListeners = new LinkedList<>();
    private WorldMap worldMap;
    private MapConfig mapConfig;

    private boolean isRunning = false;
    private int delay = 0;

    public SimulationEngine(MapConfig mapConfig, List<EventListener> eventListeners, List<DayChangeListener> dayChangeListeners) {
        this.mapConfig = mapConfig;
        this.worldMap = new WorldMap(mapConfig, eventListeners);

        this.dayChangeListeners.addAll(dayChangeListeners);

        notifyDayChangeListeners(worldMap);
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
        for (Animal animal : worldMap.getAnimals()) {
            animal.consumeEnergy(ENERGY_LOSS_PER_DAY);
            if (!animal.isAlive()) {
                worldMap.notifyEventListeners("Animal " + animal.getId() + " died");
            }
            animal.addDayAlive();
        }
    }

    private void moveAnimals() {
        for (Animal animal : worldMap.getAnimals()) {
            if (animal.isAlive()){
                animal.activateGen();
                worldMap.moveAnimal(animal);
            }
        }
    }

    private void eatPlants() {
        var animals = worldMap.getAnimals().stream().filter(Animal::isAlive).sorted(ANIMAL_COMPERATOR).toList();
        for (Animal animal : animals) {
            animal.eatPlant();
        }
    }

    private void reproduceAnimals() {
        var animals = worldMap.getAnimals().stream().filter(Animal::isAlive).sorted(ANIMAL_COMPERATOR).toList();
        for (Animal animal : animals) {
            animal.reproduce();
        }
    }

    private void growPlants() {
        worldMap.updateFields();
        for (int i = 0; i < mapConfig.newPlantsPerDay(); i++) {
            worldMap.growPlant();
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
