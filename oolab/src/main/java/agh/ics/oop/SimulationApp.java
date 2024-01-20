package agh.ics.oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulationApp extends Application {
    private static final String WINDOW_TITLE = "Evolution Simulation";
    private static final String SIMULATION_SETUP_FXML_PATH = "fxml/simulationSetup.fxml";

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(SIMULATION_SETUP_FXML_PATH));
        ScrollPane viewRoot = loader.load();

        configureStage(primaryStage, viewRoot);

        primaryStage.show();

        primaryStage.setMaxWidth(primaryStage.getWidth());
        primaryStage.setMaxHeight(primaryStage.getHeight());
    }

    private void configureStage(Stage primaryStage, ScrollPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static void init(String[] args) {
        Application.launch(SimulationApp.class, args);
    }
}
