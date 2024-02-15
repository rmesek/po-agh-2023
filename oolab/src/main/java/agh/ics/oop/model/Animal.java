package agh.ics.oop.model;

public class Animal {
    private final WorldMap worldMap;
    private MapDirection orientation;
    private final int animalId;
    private int energy;
    private final MapConfig mapConfig;
    private boolean isAlive = true;

    public Animal(MapConfig mapConfig, MapDirection initialOrientation, int energy, WorldMap worldMap) {
        this.mapConfig = mapConfig;
        this.orientation = initialOrientation;
        this.animalId = worldMap.getAnimals().size();
        this.energy = energy;
        this.worldMap = worldMap;
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

    @Override
    public String toString() {
        return getPosition().toString() + " " + getOrientation().toString() + (isAlive ? " Alive" : " Dead") + " AnimalId: " + animalId;
    }
}
