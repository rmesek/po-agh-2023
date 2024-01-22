package agh.ics.oop;


import agh.ics.oop.model.MapConfig;
import agh.ics.oop.presenter.SimulationRuntimePresenter;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static java.lang.Thread.sleep;

public class SimulationRuntime implements Runnable {
    private static final String WINDOW_TITLE = "Evolution Simulation";
    private static final String SIMULATION_RUNTIME_FXML_PATH = "fxml/simulationRuntime.fxml";
    private static int simulationRuntimeCounter = 0;
    private final MapConfig mapConfig;
    private Thread thread;

    public SimulationRuntime(MapConfig mapConfig) {
        this.mapConfig = mapConfig;
    }

    // Runs in JavaFX Application Thread!
    public void start() {
        try {
            var loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource(SIMULATION_RUNTIME_FXML_PATH));
            ScrollPane viewRoot = loader.load();
            SimulationRuntimePresenter presenter = loader.getController();
            presenter.setMapConfig(mapConfig);
            presenter.setThread(thread);
            var scene = new Scene(viewRoot);
            var primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle(WINDOW_TITLE + " " + simulationRuntimeCounter);
            primaryStage.show();
            primaryStage.setOnCloseRequest(this::handleCloseRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleCloseRequest(WindowEvent event) {
        // End thread
        thread.interrupt();
        System.out.println(thread.getName());
    }

    @Override
    public void run() {
        simulationRuntimeCounter++;
        thread = Thread.currentThread();

        Platform.runLater(this::start);

        // Do some work
        try {
            sleep(10000000);
        } catch (InterruptedException e) {
            return;
        }
    }
}
