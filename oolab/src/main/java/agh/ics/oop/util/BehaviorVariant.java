package agh.ics.oop.util;

public enum BehaviorVariant {
    COMPLETE_PREDESTINATION;

    @Override
    public String toString() {
        return switch (this) {
            case COMPLETE_PREDESTINATION -> "Complete Predestination";
        };
    }
}
