package agh.ics.oop.presenter;

public class TextFieldsConfig {
    public static final TextFieldData<Integer> MAP_WIDTH = new TextFieldData<>(1, 10, "mapWidth");
    public static final TextFieldData<Integer> MAP_HEIGHT = new TextFieldData<>(1, 10, "mapHeight");
    public static final TextFieldData<Integer> INITIAL_NUMBER_OF_PLANTS = new TextFieldData<>(1, 10, "initialNumberOfPlants");
    public static final TextFieldData<Integer> ENERGY_PER_PLANT = new TextFieldData<>(1, 10, "energyPerPlant");
    public static final TextFieldData<Integer> NEW_PLANTS_PER_DAY = new TextFieldData<>(1, 10, "newPlantsPerDay");
    public static final TextFieldData<Integer> INITIAL_NUMBER_OF_ANIMALS = new TextFieldData<>(1, 10, "initialNumberOfAnimals");
    public static final TextFieldData<Integer> INITIAL_ENERGY_OF_ANIMAL = new TextFieldData<>(1, 10, "initialEnergyOfAnimal");
    public static final TextFieldData<Integer> WELL_FED_ENERGY = new TextFieldData<>(1, 10, "wellFedEnergy");
    public static final TextFieldData<Integer> REPRODUCTION_ENERGY = new TextFieldData<>(1, 10, "reproductionEnergy");
    public static final TextFieldData<Integer> MIN_NUMBER_OF_MUTATIONS = new TextFieldData<>(1, 10, "minNumberOfMutations");
    public static final TextFieldData<Integer> MAX_NUMBER_OF_MUTATIONS = new TextFieldData<>(1, 10, "maxNumberOfMutations");
    public static final TextFieldData<Integer> LEN_OF_GENOME = new TextFieldData<>(1, 10, "lenOfGenome");
}
