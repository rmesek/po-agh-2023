package agh.ics.oop.util;

public enum PlantGrowthVariant {
    FORESTED_EQUATOR,
    CREEPING_JUNGLE;

    @Override
    public String toString() {
        return switch (this) {
            case FORESTED_EQUATOR -> "Forested Equator";
            case CREEPING_JUNGLE -> "Creeping Jungle";
        };
    }
}
