package agh.ics.oop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationSetup extends Application {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static final String WINDOW_TITLE = "Evolution Simulation";
    private static final String SIMULATION_SETUP_FXML_PATH = "fxml/simulationSetup.fxml";

    public static void init(String[] args) {
        Application.launch(SimulationSetup.class, args);
    }

    public void start(Stage primaryStage) throws IOException {
        var loader = new FXMLLoader(getClass().getClassLoader().getResource(SIMULATION_SETUP_FXML_PATH));
        ScrollPane viewRoot = loader.load();
        var scene = new Scene(viewRoot);

        primaryStage.setScene(scene);
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
        primaryStage.setOnCloseRequest(this::handleCloseRequest);

        primaryStage.show();
        primaryStage.setMaxWidth(primaryStage.getWidth());
        primaryStage.setMaxHeight(primaryStage.getHeight());
    }

    private void handleCloseRequest(WindowEvent event) {
        // Check if there are open windows
        if (Window.getWindows().size() > 1) {
            // Display a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText("There are open simulation windows!");
            alert.setContentText("Are you sure you want to exit?");

            // Customize the buttons in the dialog
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");

            alert.getButtonTypes().setAll(yesButton, noButton);

            // Show the confirmation dialog
            alert.showAndWait().ifPresent(response -> {
                if (response == yesButton) {
                    Platform.exit();
                } else {
                    event.consume();
                }
            });
        } else {
            executorService.shutdownNow();
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }
}
