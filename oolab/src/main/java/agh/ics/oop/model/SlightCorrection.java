package agh.ics.oop.model;

import java.util.List;
import java.util.Random;

public class SlightCorrection implements Mutation {
    @Override
    public void mutate(MapConfig mapConfig, List<Integer> genotype) {
        Random rand = new Random();
        int numberOfMutations = rand.nextInt(mapConfig.maxNumberOfMutations() - mapConfig.minNumberOfMutations() + 1) + mapConfig.minNumberOfMutations();
        for (int i = 0; i < numberOfMutations; i++) {
            int shift = Math.random() > 0.5 ? 1 : 7;
            int index = rand.nextInt(genotype.size());
            genotype.set(index, (genotype.get(index) + shift) % 8);
        }
    }
}
