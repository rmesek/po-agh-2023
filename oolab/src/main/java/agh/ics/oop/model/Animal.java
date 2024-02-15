package agh.ics.oop.model;

import java.util.*;
import java.util.stream.Stream;

public class Animal {
    private static final AnimalComparator ANIMAL_COMPARATOR = new AnimalComparator();
    private final WorldMap worldMap;
    private MapDirection orientation;
    private final int animalId;
    private int energy;
    private final MapConfig mapConfig;
    private final List<Integer> genotype;
    private int activeGenIndex = 0;
    private int daysAlive = 0;
    private List<Animal> children = new ArrayList<>();
    private final int randomTrair = new Random().nextInt();

    public Animal(MapConfig mapConfig, MapDirection initialOrientation, int energy, WorldMap worldMap) {
        this.mapConfig = mapConfig;
        this.orientation = initialOrientation;
        this.animalId = worldMap.getAnimals().size();
        this.energy = energy;
        this.worldMap = worldMap;
        this.genotype = new ArrayList<>(mapConfig.lenOfGenome());
    }

    public int getRandomTrait() {
        return randomTrair;
    }

    public void eatPlant() {
        energy += worldMap.eatPlant(getPosition());
        worldMap.notifyEventListeners("Grass eaten at " + getPosition() + " by animal " + animalId);
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

    public void prepareGenotype() {
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
        if (energy < mapConfig.wellFedEnergy()) return;
        Animal partner = worldMap.getAnimalsAt(getPosition()).stream().filter(Animal::isAlive).filter(animal -> animal != this).max(ANIMAL_COMPARATOR).orElse(null);
        if (partner == null) return;
        int partnerEnergy = partner.getEnergy();
        if (partnerEnergy < mapConfig.wellFedEnergy()) return;

        createChild(partner);
        energy -= mapConfig.reproductionEnergy();
        partner.energy -= mapConfig.reproductionEnergy();
        worldMap.notifyEventListeners("Animal " + animalId + " reproduced");
    }

    private void createChild(Animal partner) {
        Random rand = new Random();
        Animal stronger = energy > partner.energy ? this : partner;
        Animal weaker = energy > partner.energy ? partner : this;

        double ratio = (double) weaker.energy / (stronger.energy + weaker.energy);
        int cutIndex = mapConfig.lenOfGenome() - (int) (mapConfig.lenOfGenome() * ratio);
        List<Integer> strongerGenotype = new ArrayList<>(stronger.getGenotype());
        List<Integer> weakerGenotype = new ArrayList<>(weaker.getGenotype());
        if (Math.random() > 0.5) {
            Collections.reverse(strongerGenotype);
        } else {
            Collections.reverse(weakerGenotype);
        }
        List<Integer> childGenotype = Stream.concat(strongerGenotype.stream().limit(cutIndex), weakerGenotype.stream().skip(cutIndex)).toList();
        Animal child = new Animal(mapConfig, MapDirection.values()[rand.nextInt(MapDirection.values().length)], mapConfig.reproductionEnergy() * 2, worldMap);
        child.genotype.addAll(childGenotype);
        worldMap.spawnChild(child, getPosition());
        if (child.genotype.size() != mapConfig.lenOfGenome()) {
            throw new RuntimeException("Child genotype size is not equal to mapConfig.lenOfGenome()");
        }
        children.add(child);
    }
}
