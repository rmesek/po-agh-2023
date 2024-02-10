package agh.ics.oop.presenter;

import agh.ics.oop.model.MapConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationRuntimePresenter {
    @FXML
    private Label graphLabel;
    @FXML
    private Label threadLabel;
    @FXML
    private Label timeLabel;

    @FXML
    private void initialize() {

    }

    public void setMapConfig(MapConfig mapConfig) {
        graphLabel.setText(mapConfig.toString());
    }

    public void setRealTime(String realTime) {
        timeLabel.setText(realTime);
    }


    public void setThread(Thread thread) {
        threadLabel.setText(thread.getName());
    }
}
