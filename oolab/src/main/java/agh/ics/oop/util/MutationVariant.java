package agh.ics.oop.util;

public enum MutationVariant {
    COMPLETE_RANDOMIZATION,
    SLIGHT_CORRECTION;

    @Override
    public String toString() {
        return switch (this) {
            case COMPLETE_RANDOMIZATION -> "Complete Randomization";
            case SLIGHT_CORRECTION -> "Slight Correction";
        };
    }
}
