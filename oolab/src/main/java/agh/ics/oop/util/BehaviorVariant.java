package agh.ics.oop.util;

public enum BehaviorVariant { // czemu to nie jest część modelu?
    COMPLETE_PREDESTINATION; // enum z jedną wartością?

    @Override
    public String toString() {
        return switch (this) {
            case COMPLETE_PREDESTINATION -> "Complete Predestination";
        };
    }
}
