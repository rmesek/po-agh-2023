package agh.ics.oop;


import agh.ics.oop.model.DayChangeListener;
import agh.ics.oop.model.EventListener;
import agh.ics.oop.model.MapConfig;
import agh.ics.oop.model.SimulationEngine;
import agh.ics.oop.presenter.FileLogger;
import agh.ics.oop.presenter.SimulationRuntimePresenter;
import agh.ics.oop.util.ConsoleEventListener;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SimulationRuntime implements Runnable {
    private static final String WINDOW_TITLE = "Evolution Simulation";
    private static final String SIMULATION_RUNTIME_FXML_PATH = "fxml/simulationRuntime.fxml";
    private static int simulationRuntimeCounter = 0;
    private final MapConfig mapConfig;
    private final File logFile;
    private Thread thread;
    private SimulationRuntimePresenter simulationRuntimePresenter;

    public SimulationRuntime(MapConfig mapConfig, File logFile) {
        this.mapConfig = mapConfig;
        this.logFile = logFile;
    }

    private void endThread() {
        thread.interrupt();
    }

    // Runs in JavaFX Application Thread!
    private void start() {
        try {
            var loader = new FXMLLoader(getClass().getClassLoader().getResource(SIMULATION_RUNTIME_FXML_PATH));
            var viewRoot = loader.load();
            simulationRuntimePresenter = loader.getController();
            simulationRuntimePresenter.setThread(thread);
            simulationRuntimePresenter.setLogFileInfo(logFile);
            simulationRuntimePresenter.setMapConfig(mapConfig);
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

    private void handleCloseRequest(WindowEvent event) {
        // End thread
        endThread();
//        System.out.println(thread.getName());
    }

    @Override
    public void run() {  // Start new simulation
        Platform.runLater(this::start);
        simulationRuntimeCounter++;
        thread = Thread.currentThread();

        waitForPresenter();

        List<EventListener> eventListeners = new ArrayList<>();
        if (logFile != null) eventListeners.add(new FileLogger(logFile));
        List<DayChangeListener> dayChangeListeners = List.of(simulationRuntimePresenter);
        SimulationEngine simulationEngine = new SimulationEngine(mapConfig, eventListeners, dayChangeListeners);
        try {
            simulationEngine.run();
        } catch (InterruptedException e) {
            endThread();
        }
    }

    private void waitForPresenter() {
        try {
            while (simulationRuntimePresenter == null) {
                sleep(100);
            }
        } catch (InterruptedException e) {
            endThread();
        }
    }
}
