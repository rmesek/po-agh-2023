package agh.ics.oop.model;

import agh.ics.oop.util.BehaviorVariant;
import agh.ics.oop.util.MapVariant;
import agh.ics.oop.util.MutationVariant;
import agh.ics.oop.util.PlantGrowthVariant;

public record MapConfig(
        int mapWidth,
        int mapHeight,
        MapVariant mapVariant,
        int initialNumberOfPlants,
        int energyPerPlant,
        int newPlantsPerDay,
        PlantGrowthVariant plantGrowthVariant,
        int initialNumberOfAnimals,
        int initialEnergyOfAnimal,
        int wellFedEnergy,
        int reproductionEnergy,
        int minNumberOfMutations,
        int maxNumberOfMutations,
        MutationVariant mutationVariant,
        int lenOfGenome,
        BehaviorVariant behaviorVariant
) {
}