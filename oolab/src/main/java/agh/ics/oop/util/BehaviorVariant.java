package agh.ics.oop.util;

public enum BehaviorVariant {
    COMPLETE_PREDESTINATION; // po co enum z jedną wartością?

    @Override
    public String toString() {
        return switch (this) {
            case COMPLETE_PREDESTINATION -> "Complete Predestination";
        };
    }
}
