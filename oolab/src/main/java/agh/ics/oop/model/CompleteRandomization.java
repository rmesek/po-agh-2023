package agh.ics.oop.model;

import java.util.List;
import java.util.Random;

public class CompleteRandomization implements Mutation {
    @Override
    public void mutate(MapConfig mapConfig, List<Integer> genotype) {
        Random rand = new Random();
        int numberOfMutations = rand.nextInt(mapConfig.maxNumberOfMutations() - mapConfig.minNumberOfMutations() + 1) + mapConfig.minNumberOfMutations();
        for (int i = 0; i < numberOfMutations; i++) {
            genotype.set(rand.nextInt(genotype.size()), rand.nextInt(8));
        }
    }
}
