package agh.ics.oop.presenter;

import agh.ics.oop.model.MapConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.lang.reflect.Field;

public class SimulationRuntimePresenter {

    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Slider delaySlider;
    @FXML
    private VBox controlsVBox;
    @FXML
    private VBox configVBox;
    @FXML
    private Label graphLabel;
    @FXML
    private Label timeLabel;
    // General info
    @FXML
    private Label numberOfAnimals;
    @FXML
    private Label numberOfPlants;
    @FXML
    private Label numberOfFreeSpaces;
    @FXML
    private Label mostPopularGenotypes;
    @FXML
    private Label averageEnergy;
    @FXML
    private Label averageLifespan;
    @FXML
    private Label averageDescendandsForAlive;
    // Tracked info
    @FXML
    private TextField trackedAnimalID;
    @FXML
    private Label trackedGenotype;
    @FXML
    private Label trackedActiveGenotype;
    @FXML
    private Label trackedEnergy;
    @FXML
    private Label trackedPlantsEaten;
    @FXML
    private Label trackedChildren;
    @FXML
    private Label trackedDescendands;
    @FXML
    private Label trackedDaysAlive;
    @FXML
    private Label trackedDayOfDeath;



    @FXML
    private void initialize() {
        initializeToggleButton();
    }

    private void initializeToggleButton() {
        toggleButton.setText("Start");
        toggleButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                toggleButton.setText("Stop");
            } else {
                toggleButton.setText("Start");
            }
        });
    }

    private void appendConfigInfo(MapConfig mapConfig) {
        // itareate over the fields of the MapConfig record and add them to the VBox
        for (Field field : MapConfig.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                configVBox.getChildren().add(createHBox(field.getName(), String.valueOf(field.get(mapConfig))));
            } catch (IllegalAccessException e) {
                configVBox.getChildren().add(createHBox(field.getName(), ""));
            }
            configVBox.getChildren().add(new Separator());
        }
    }

    private HBox createHBox(String label, String value) {
        HBox hBox = new HBox();
        Label labelControl = new Label(label);
        Label valueControl = new Label(value);

        // configure the label and value controls look
        hBox.setPadding(new Insets(5));
        labelControl.setStyle("-fx-border-width: 0; -fx-padding: 2px");
        valueControl.setStyle("-fx-border-color: lightgrey; -fx-border-width: 1; -fx-padding: 2px");

        hBox.getChildren().addAll(labelControl, valueControl);
        return hBox;
    }

    public void setMapConfig(MapConfig mapConfig) {
        appendConfigInfo(mapConfig);
    }

    public void setRealTime(String realTime) {
//        timeLabel.setText(realTime);
    }

    public void setThread(Thread thread) {
        configVBox.getChildren().add(createHBox("thread", thread.getName()));
        configVBox.getChildren().add(new Separator());
    }

    public void setLogFileInfo(File logFileInfo) {
        configVBox.getChildren().add(createHBox("logFile", String.valueOf(logFileInfo)));
        configVBox.getChildren().add(new Separator());
    }

    public void toggleSimulation() {
        System.out.println(toggleButton.isSelected());
    }
}
