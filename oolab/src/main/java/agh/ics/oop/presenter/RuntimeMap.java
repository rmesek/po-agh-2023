package agh.ics.oop.presenter;

import agh.ics.oop.model.*;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RuntimeMap {
    private final GridPane mapGrid;
    private final int mapWidth;
    private final int mapHeight;
    private static final int CELL_WIDTH = 32;
    private static final int CELL_HEIGHT = 32;
    private static final AnimalComparator ANIMAL_COMPARATOR = new AnimalComparator();
    private static final double ANIMAL_GOOD_HEALTH = 10.0;
    private static final Image ANIMAL_IMAGE = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/animal.png")).toExternalForm());
    private static final Image GRASS_IMAGE = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/grass.png")).toExternalForm());
    private static final Image TRACKED_FIELD = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/trackedField.png")).toExternalForm());
    private static final Image JUNGLE_FIELD = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/jungleField.png")).toExternalForm());
    private static final Image BEST_GENOTYPE = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/bestGenotype.png")).toExternalForm());
    private static final Image NORMAL_FIELD = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/normalField.png")).toExternalForm());


    public RuntimeMap(int mapWidth, int mapHeight, SimulationEngine simulationEngine) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.mapGrid = new GridPane();
        prepareGrid();
    }

    private Effect getHealthEffect(double health) {
        double criticalHealth = 0.5;
        if (health > criticalHealth) {
            return new ColorAdjust(0, 0, 0, 0);
        }
        return new ColorAdjust(-0.1, (criticalHealth - health) * 1 / criticalHealth, 0, 0);
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void prepareGrid() {
        mapGrid.setGridLinesVisible(true);
    }

    private void addGrass(int x, int y) {
        var grassImageView = new ImageView(GRASS_IMAGE);
        mapGrid.add(grassImageView, x, y);
    }

    private void addAnimal(int x, int y, double direction, double health) {
        var animalImageView = new ImageView(ANIMAL_IMAGE);
        animalImageView.setRotate(direction);
        animalImageView.setEffect(getHealthEffect(health));
        animalImageView.setOnMouseClicked(e -> System.out.println("Clicked at " + x + ", " + y));
        mapGrid.add(animalImageView, x, y);
    }

    private void addNormal(int x, int y) {
        var normalImageView = new ImageView(NORMAL_FIELD);
        mapGrid.add(normalImageView, x, y);
    }

    private void addJungle(int x, int y) {
        var jungleImageView = new ImageView(JUNGLE_FIELD);
        mapGrid.add(jungleImageView, x, y);
    }

    private void addBestGenotype(int x, int y) {
        var bestGenotypeImageView = new ImageView(BEST_GENOTYPE);
        mapGrid.add(bestGenotypeImageView, x, y);
    }

    private void addTracked(int x, int y) {
        var trackedImageView = new ImageView(TRACKED_FIELD);
        mapGrid.add(trackedImageView, x, y);
    }

    public void updateMap(WorldMap worldMap) {
        clearGrid();
        Map<Vector2d, Field> fields = worldMap.getFields();

        for (int x = 0; x < mapWidth; x++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            for (int y = 0; y < mapHeight; y++) {
                drawField(fields, x, y);
                drawAnimal(worldMap, x, y);
            }
        }
    }

    private void drawAnimal(WorldMap worldMap, int x, int y) {
        List<Animal> animals = worldMap.getAnimalsAt(new Vector2d(x, mapHeight - y - 1));
        if (animals.isEmpty()) return;
        Animal animal = animals.stream().filter(Animal::isAlive).max(ANIMAL_COMPARATOR).orElse(animals.get(0));
        addAnimal(x, y, animal.getOrientation().toDegrees(), animal.getEnergy() / ANIMAL_GOOD_HEALTH);
    }

    private void drawField(Map<Vector2d, Field> fields, int x, int y) {
        Field field = fields.get(new Vector2d(x, mapHeight - y - 1));
        switch (field.getType()) {
            case NORMAL -> addNormal(x, y);
            case JUNGLE -> addJungle(x, y);
        }
        if (field.hasGrass()) addGrass(x, y);
    }

    public Node getContent() {
        clearGrid();
        for (int x = 0; x < mapWidth; x++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
        return mapGrid;
    }
}
