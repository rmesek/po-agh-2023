package agh.ics.oop.model;

import java.util.List;
import java.util.Random;

public class CompleteRandomization implements Mutation {
    @Override
    public void mutate(MapConfig mapConfig, List<Integer> genotype) {
        Random rand = new Random();
        for (int i = mapConfig.minNumberOfMutations(); i <= mapConfig.maxNumberOfMutations(); i++) {
            genotype.set(rand.nextInt(genotype.size()), rand.nextInt(8));
        }
    }
}
