package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private static int numberOfUpdates = 0;
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Map id: " + worldMap.getId());
        System.out.println(message);
        System.out.println("Update no. " + ++numberOfUpdates);
        System.out.println(worldMap);
    }
}
