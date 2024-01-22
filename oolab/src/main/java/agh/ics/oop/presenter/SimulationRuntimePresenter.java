package agh.ics.oop.presenter;

import agh.ics.oop.model.MapConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationRuntimePresenter {
    // private MapConfig mapConfig;
    @FXML
    private Label threadLabel;
    @FXML
    private Label mainLabel;

    @FXML
    private void initialize() {
    }

    public void setMapConfig(MapConfig mapConfig) {
        // this.mapConfig = mapConfig;
        mainLabel.setText(mapConfig.toString());
    }


    public void setThread(Thread thread) {
        // set threadLabel text to thread name
        threadLabel.setText(thread.getName());
    }
}
