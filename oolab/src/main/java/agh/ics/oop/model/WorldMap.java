package agh.ics.oop.model;

import java.util.*;

public class WorldMap {
    private final MapConfig mapConfig;
    private final Map<Animal, Vector2d> animalPositions = new HashMap<>();
    private final Map<Vector2d, Field> fields = new HashMap<>();


    public WorldMap(MapConfig mapConfig) {
        this.mapConfig = mapConfig;

        prepareFields();
        preparePlants();
        prepareAnimals();
    }

    private void prepareFields() {
        switch (mapConfig.plantGrowthVariant()) {
            case FORESTED_EQUATOR -> {
                PlantGrowth plantGrowth = new ForestedEquator();
                fields.putAll(plantGrowth.prepareFields(mapConfig, this));
            }
            case CREEPING_JUNGLE -> {
                PlantGrowth plantGrowth = new CreepingJungle();
                fields.putAll(plantGrowth.prepareFields(mapConfig, this));
            }
        }
    }

    private void preparePlants() {
        for (int i = 0; i < mapConfig.initialNumberOfPlants(); i++) {
            growPlant();
        }
    }

    public void growPlant() {
        Random rand = new Random();
        boolean onJungle = Math.random() > 0.2;
        List<Vector2d> normalFreePositions = new ArrayList<>();
        List<Vector2d> jungleFreePositions = new ArrayList<>();
        for (Field field : fields.values()) {
            if (!field.hasGrass()) {
                switch (field.getType()) {
                    case JUNGLE -> jungleFreePositions.add(field.getPosition());
                    case NORMAL -> normalFreePositions.add(field.getPosition());
                }
            }
        }
        if (onJungle && !jungleFreePositions.isEmpty()) {
            int randomIndex = rand.nextInt(jungleFreePositions.size());
            fields.get(jungleFreePositions.get(randomIndex)).setGrass(true);
        } else if (!normalFreePositions.isEmpty()) {
            int randomIndex = rand.nextInt(normalFreePositions.size());
            fields.get(normalFreePositions.get(randomIndex)).setGrass(true);
        }
    }

    public void eatPlant(Vector2d position) {
        Field field = fields.get(position);
        if (field.hasGrass()) {
            field.setGrass(false);
        }
    }

    private void prepareAnimals() {
        for (int i = 0; i < mapConfig.initialNumberOfAnimals(); i++) {
            spawnAnimal();
        }
    }

    private void spawnAnimal() {
        Random rand = new Random();
        Vector2d position = new Vector2d(rand.nextInt(mapConfig.mapWidth()), rand.nextInt(mapConfig.mapHeight()));
        MapDirection orientation = MapDirection.values()[rand.nextInt(MapDirection.values().length)];
        Animal animal = new Animal(mapConfig, orientation, mapConfig.initialEnergyOfAnimal(), this);
        animalPositions.put(animal, position);
    }


    private List<Animal> getAnimalsAt(Vector2d position) {
        List<Animal> animals = new ArrayList<>();
        for (Animal animal : animalPositions.keySet()) {
            if (position.equals(animalPositions.get(animal))) {
                animals.add(animal);
            }
        }
        return animals;
    }

    private Field getFieldAt(Vector2d position) {
        return fields.get(position);
    }


    private void updateFields() {
        switch (mapConfig.plantGrowthVariant()) {
            case FORESTED_EQUATOR -> {
                PlantGrowth plantGrowth = new ForestedEquator();
                plantGrowth.updateFields(this);
            }
            case CREEPING_JUNGLE -> {
                PlantGrowth plantGrowth = new CreepingJungle();
                plantGrowth.updateFields(this);
            }
        }
    }

    public void moveAnimal(Animal animal) {
        Vector2d oldPosition = animalPositions.get(animal);
        MapDirection oldOrientation = animal.getOrientation();

        Vector2d newPosition = oldPosition.add(oldOrientation.toUnitVector());
        MapDirection newOrientation = oldOrientation;
        if (newPosition.getY() < 0 || newPosition.getY() >= mapConfig.mapHeight()) {
            newPosition = new Vector2d(newPosition.getX(), oldPosition.getY());
            newOrientation = oldOrientation.opposite();
        }
        if (newPosition.getX() < 0) {
            newPosition = new Vector2d(mapConfig.mapWidth() - 1, newPosition.getY());
        }
        if (newPosition.getX() >= mapConfig.mapWidth()) {
            newPosition = new Vector2d(0, newPosition.getY());
        }

        if (!newPosition.equals(oldPosition)) {
            animalPositions.put(animal, newPosition);
        }
        animal.setOrientation(newOrientation);
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animalPositions.keySet());
    }

    public Map<Animal, Vector2d> getAnimalPositions() {
        return Collections.unmodifiableMap(animalPositions);
    }

    public Map<Vector2d, Field> getFields() {
        return Collections.unmodifiableMap(fields);
    }
}
