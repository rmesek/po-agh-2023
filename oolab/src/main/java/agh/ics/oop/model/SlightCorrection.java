package agh.ics.oop.model;

import java.util.List;
import java.util.Random;

public class SlightCorrection implements Mutation {
    @Override
    public void mutate(MapConfig mapConfig, List<Integer> genotype) {
        Random rand = new Random();
        for (int i = mapConfig.minNumberOfMutations(); i <= mapConfig.maxNumberOfMutations(); i++) {
            int shift = Math.random() > 0.5 ? 1 : 7;
            genotype.set(rand.nextInt(genotype.size()), (genotype.get(rand.nextInt(genotype.size())) + shift) % 8);
        }
    }
}
