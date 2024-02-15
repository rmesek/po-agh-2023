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
    private boolean isAlive = true;
    private final List<Integer> genotype;

    public Animal(MapConfig mapConfig, MapDirection initialOrientation, int energy, WorldMap worldMap) {
        this.mapConfig = mapConfig;
        this.orientation = initialOrientation;
        this.animalId = worldMap.getAnimals().size();
        this.energy = energy;
        this.worldMap = worldMap;
        this.genotype = new ArrayList<>(mapConfig.lenOfGenome());
        prepareGenotype();
    }

    private void prepareGenotype() {
        Random rand = new Random();
        for (int i = 0; i < mapConfig.lenOfGenome(); i++) {
            genotype.add(i, rand.nextInt(8));
        }
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
    }

    public void killAnimal() {
        isAlive = false;
    }

    public Vector2d getPosition() {
        return worldMap.getAnimalPositions().get(this);
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public int getEnergy() {
        return energy;
    }

    public List<Integer> getGenotype() {
        return Collections.unmodifiableList(genotype);
    }

    @Override
    public String toString() {
        return getPosition().toString() + " " + getOrientation().toString() + (isAlive ? " Alive" : " Dead") + " AnimalId: " + animalId;
    }
}
