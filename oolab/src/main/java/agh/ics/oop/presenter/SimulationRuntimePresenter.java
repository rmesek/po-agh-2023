package agh.ics.oop.presenter;

import agh.ics.oop.model.MapConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class SimulationRuntimePresenter {
    @FXML
    private MapConfig mapConfig;
    @FXML
    private Label mapConfigLabel;
    @FXML
    private Label threadLabel;
    @FXML
    private Label mainLabel;

    @FXML
    private void initialize() {

    }

    public void setMapConfig(MapConfig mapConfig) {
//        this.mapConfig = mapConfig;
//        mapConfigLabel.setText(mapConfig.toString());
    }

    public void setRealTime(String realTime) {
        mainLabel.setText(realTime);
    }


    public void setThread(Thread thread) {
        // set threadLabel text to thread name
        threadLabel.setText(thread.getName());
    }
}
