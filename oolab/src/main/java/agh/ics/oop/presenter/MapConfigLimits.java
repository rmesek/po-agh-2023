package agh.ics.oop.presenter;

public class MapConfigLimits { // czy ta klasa jest używana?
    public ConfigLimit<Integer> mapWidth = new ConfigLimit<>(1, 10); // 10?
    public ConfigLimit<Integer> mapHeight = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> initialNumberOfPlants = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> energyPerPlant = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> newPlantsPerDay = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> initialNumberOfAnimals = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> initialEnergyOfAnimal = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> wellFedEnergy = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> reproductionEnergy = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> minNumberOfMutations = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> maxNumberOfMutations = new ConfigLimit<>(1, 10);
    public ConfigLimit<Integer> lenOfGenome = new ConfigLimit<>(1, 10);
}
