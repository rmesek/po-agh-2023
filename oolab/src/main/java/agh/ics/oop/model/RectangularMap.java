package agh.ics.oop.model;

import agh.ics.oop.util.MapVisualizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    public RectangularMap(int width, int height) {
        mapBottomLeft = new Vector2d(0,0);
        mapTopRight = new Vector2d(width - 1, height - 1);
    }
}
