package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private static final double CELL_WIDTH = 50;
    private static final double CELL_HEIGHT = 50;
    @FXML
    private GridPane mapGrid;
    @FXML
    private Label moveDescription;
    @FXML
    private TextField textField;
    @FXML
    private Button startButton;
    private WorldMap worldMap;
    private SimulationEngine simulationEngine;

    @FXML
    private void initialize() {

    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.worldMap.subscribe(this);
    }

    public void drawMap() {
        clearGrid();
        Boundary boundary = worldMap.getCurrentBounds();
        int xMin = boundary.BottomLeftVec().getX();
        int xMax = boundary.TopRightVec().getX();
        int yMin = boundary.BottomLeftVec().getY();
        int yMax = boundary.TopRightVec().getY();

        addEdge(mapGrid);
        addLegendX(mapGrid, xMin, xMax);
        addLegendY(mapGrid, yMin, yMax);

        for (int x = xMin; x <= xMax; x++) {
             for (int y = yMin; y <= yMax; y++) {
                addCell(mapGrid, x - xMin, y - yMin, x, yMax + yMin - y);
            }
        }
    }

    private void addCell(GridPane mapGrid, int x, int y, int mapX, int mapY) {
        Label label = new Label();
        WorldElement worldElement = worldMap.objectAt(new Vector2d(mapX, mapY));
        if (worldElement != null) {
            label.setText(worldElement.toString());
        } else {
            label.setText("");
        }
        GridPane.setHalignment(label, HPos.CENTER);
        mapGrid.add(label, x + 1, y + 1);
    }

    private void addEdge(GridPane mapGrid) {
        Label label = new Label();
        label.setText("y\\x");
        GridPane.setHalignment(label, HPos.CENTER);
        mapGrid.add(label, 0 ,0);
        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
    }

    private void addLegendX(GridPane mapGrid, int fromValue, int toValue) {
        for (int x = 0; x <= toValue - fromValue; ++x) {
            Label label = new Label();
            label.setText(String.valueOf(fromValue + x));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label, x + 1,0);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
    }

    private void addLegendY(GridPane mapGrid, int fromValue, int toValue) {
        for (int y = 0; y <= toValue - fromValue; ++y) {
            Label label = new Label();
            label.setText(String.valueOf(fromValue + y));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label, 0,toValue - fromValue - y + 1);
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            this.moveDescription.setText(message);
            drawMap();
        });
    }

    public void onSimulationStartClicked() throws InterruptedException {
        moveDescription.setTextFill(Color.BLACK);
        moveDescription.setText("");
        List<MoveDirection> directions;
        try {
            directions = OptionsParser.parse(textField.getText().split(" "));
        } catch (IllegalArgumentException ex) {
            moveDescription.setTextFill(Color.RED);
            moveDescription.setText(ex.getMessage());
            return;
        }

        prepareSimulationEngine(directions);
        this.simulationEngine.runAsyncInThreadPool();
    }

    private void prepareSimulationEngine(List<MoveDirection> directions) {
        List<Simulation> simulations = new LinkedList<>();
        setWorldMap(new GrassField(5));
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        Simulation simulation = new Simulation(positions, directions, worldMap);
        simulations.add(simulation);
        this.simulationEngine = new SimulationEngine(simulations);
    }
}
