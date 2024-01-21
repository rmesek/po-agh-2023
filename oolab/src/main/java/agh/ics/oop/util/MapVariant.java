package agh.ics.oop.util;

public enum MapVariant {
    GLOBE;

    @Override
    public String toString() {
        return switch (this) {
            case GLOBE -> "Globe";
        };
    }
}
