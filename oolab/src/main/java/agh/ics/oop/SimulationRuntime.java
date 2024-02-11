package agh.ics.oop;


import agh.ics.oop.model.MapConfig;
import agh.ics.oop.presenter.SimulationRuntimePresenter;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static java.lang.Thread.sleep;

public class SimulationRuntime implements Runnable {
    private static final String WINDOW_TITLE = "Evolution Simulation";
    private static final String SIMULATION_RUNTIME_FXML_PATH = "fxml/simulationRuntime.fxml";
    private static int simulationRuntimeCounter = 0;
    private int simulationRuntimeId;
    private final MapConfig mapConfig;
    private Thread thread;
    private SimulationRuntimePresenter presenter;
    private String realTime;

    public SimulationRuntime(MapConfig mapConfig) {
        this.mapConfig = mapConfig;
    }

    // Runs in JavaFX Application Thread!
    private void start() {
        try {
            var loader = new FXMLLoader(getClass().getClassLoader().getResource(SIMULATION_RUNTIME_FXML_PATH));
            var viewRoot = loader.load();
            presenter = loader.getController();
            presenter.setThread(thread);
            presenter.setMapConfig(mapConfig);
            var scene = new Scene((Region) viewRoot);
            var primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle(WINDOW_TITLE + " " + simulationRuntimeCounter);
            primaryStage.setOnCloseRequest(this::handleCloseRequest);
            primaryStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePresenter() {
        presenter.setRealTime(this.realTime);
    }

    private void handleCloseRequest(WindowEvent event) {
        // End thread
        thread.interrupt();
        System.out.println(thread.getName());
    }

    public String getRealTime() { // TODO: delete later
        return "Thread" + Thread.currentThread().getName() + " Real time: " + System.currentTimeMillis() / 1000;
    }

    public void runTimer() throws InterruptedException {
        while (true) {
            this.realTime = getRealTime();
//            System.out.println(time);
            Platform.runLater(this::updatePresenter);
            sleep(1000L * mapConfig.mapWidth());
        }
    }

    @Override
    public void run() {  // Start new simulation
        simulationRuntimeCounter++;
        this.simulationRuntimeId = simulationRuntimeCounter;
        thread = Thread.currentThread();

        Platform.runLater(this::start);

        // Do some work
        try {
            runTimer();
        } catch (InterruptedException e) {
            return;
        }
    }
}
