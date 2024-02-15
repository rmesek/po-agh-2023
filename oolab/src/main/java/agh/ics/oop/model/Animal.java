package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Animal {
    private final WorldMap worldMap;
    private MapDirection orientation;
    private final int animalId;
    private int energy;
    private final MapConfig mapConfig;
    private final List<Integer> genotype;
    private int activeGenIndex = 0;
    private int daysAlive = 0;
    private List<Animal> children = new ArrayList<>();

    public Animal(MapConfig mapConfig, MapDirection initialOrientation, int energy, WorldMap worldMap) {
        this.mapConfig = mapConfig;
        this.orientation = initialOrientation;
        this.animalId = worldMap.getAnimals().size();
        this.energy = energy;
        this.worldMap = worldMap;
        this.genotype = new ArrayList<>(mapConfig.lenOfGenome());
        prepareGenotype();
    }

    public void eatPlant() {
        energy += worldMap.eatPlant(getPosition());
    }

    public void addDayAlive() {
        daysAlive++;
    }

    public int getChildrenCount() {
        return children.size();
    }

    public int getDaysAlive() {
        return daysAlive;
    }

    public int getId() {
        return animalId;
    }

    private void nextGenIndex() {
        activeGenIndex = (activeGenIndex + 1) % mapConfig.lenOfGenome();
    }

    public void activateGen() {
        int gen = genotype.get(activeGenIndex);
        orientation = orientation.next(gen);

        nextGenIndex();
        worldMap.notifyEventListeners("Animal " + animalId + " activated gene " + gen);
    }

    private void prepareGenotype() {
        Random rand = new Random();
        for (int i = 0; i < mapConfig.lenOfGenome(); i++) {
            genotype.add(i, rand.nextInt(8));
        }
        worldMap.notifyEventListeners("Animal " + animalId + " got genotype " + getGenotype());
    }

    public void mutate() {
        switch (mapConfig.mutationVariant()) {
            case COMPLETE_RANDOMIZATION -> {
                Mutation mutation = new CompleteRandomization();
                mutation.mutate(mapConfig, genotype);
            }
            case SLIGHT_CORRECTION -> {
                Mutation mutation = new SlightCorrection();
                mutation.mutate(mapConfig, genotype);
            }
        }
        worldMap.notifyEventListeners("Animal " + animalId + " mutated to " + getGenotype());
    }


    public Vector2d getPosition() {
        return worldMap.getAnimalPositions().get(this);
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
        worldMap.notifyEventListeners("Animal " + animalId + " changed orientation to " + orientation);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public int getEnergy() {
        return energy;
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;
    }

    public List<Integer> getGenotype() {
        return Collections.unmodifiableList(genotype);
    }

    public boolean isAlive() {
        return energy > 0;
    }

    @Override
    public String toString() {
        return getPosition().toString() + " " + getOrientation().toString() + (isAlive() ? " Alive" : " Dead") + " AnimalId: " + animalId;
    }

    public void reproduce() {
        // TODO: Implement this
    }
}
