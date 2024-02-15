package agh.ics.oop.presenter;

import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;

public class RuntimeMap {
    private final GridPane mapGrid;
    private final int mapWidth;
    private final int mapHeight;
    private static final int CELL_WIDTH = 32;
    private static final int CELL_HEIGHT = 32;
    private static final Image ANIMAL_IMAGE = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/animal.png")).toExternalForm());
    private static final Image GRASS_IMAGE = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/grass.png")).toExternalForm());
    private static final Image TRACKED_FIELD = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/trackedField.png")).toExternalForm());
    private static final Image JUNGLE_FIELD = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/jungleField.png")).toExternalForm());
    private static final Image BEST_GENOTYPE = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/bestGenotype.png")).toExternalForm());
    private static final Image NORMAL_FIELD = new Image(Objects.requireNonNull(RuntimeMap.class.getClassLoader().getResource("img/normalField.png")).toExternalForm());

    public RuntimeMap(int mapWidth, int mapHeight) {
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

    private void updateMap() {
        clearGrid();
        for (int x = 0; x < mapWidth; x++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            for (int y = 0; y < mapHeight; y++) {
                // TODO: objectAt(x,y) ...
                addNormal(x, y);
            }
        }
        addAnimal(0, 1, 0, 0.2);
        addTracked(0, 1);
        addBestGenotype(0, 1);
    }

    public Node getContent() {
        updateMap();
        return mapGrid;
    }
}
