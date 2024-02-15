package agh.ics.oop.model;

import java.util.List;

public interface Mutation {
    void mutate(MapConfig mapConfig, List<Integer> genotype);
}
