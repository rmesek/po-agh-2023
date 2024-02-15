package agh.ics.oop.model;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal a1, Animal a2) {
        int energyCompare = Integer.compare(a2.getEnergy(), a1.getEnergy());
        if (energyCompare != 0) return energyCompare;
        int ageCompare = Integer.compare(a2.getDaysAlive(), a1.getDaysAlive());
        if (ageCompare != 0) return ageCompare;
        int childrenCompare = Integer.compare(a2.getChildrenCount(), a1.getChildrenCount());
        if (childrenCompare != 0) return childrenCompare;
        return Math.random() > 0.5 ? 1 : -1;
    }
}
