package agh.ics.oop.model;

import agh.ics.oop.util.MapVisualizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    final Vector2d mapBottomLeft = new Vector2d(0, 0);
    final Vector2d mapTopRight;
    public RectangularMap(int width, int height) {
        mapTopRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    public String toString() {
        return visualizer.draw(mapBottomLeft, mapTopRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(mapBottomLeft) && position.precedes(mapTopRight);
    }
}
