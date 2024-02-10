package agh.ics.oop.presenter;

import agh.ics.oop.SimulationRuntime;
import agh.ics.oop.SimulationSetup;
import agh.ics.oop.model.MapConfig;
import agh.ics.oop.util.BehaviorVariant;
import agh.ics.oop.util.MapVariant;
import agh.ics.oop.util.MutationVariant;
import agh.ics.oop.util.PlantGrowthVariant;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.lang.Integer.parseInt;

public class SimulationSetupPresenter {
    private final ExecutorService executorService;
    private static final String DEFAULT_PROPERTIES_PATH = "default.properties";
    private final Hashtable<TextField, TextFieldData<Integer>> textFieldProperties = new Hashtable<>();
    @FXML
    private CheckBox logToFile;
    private int invalidFields = 0;
    @FXML
    private Label problemLabel;
    @FXML
    private Button exportButton;
    @FXML
    private Button startButton;
    @FXML
    private ScrollPane simulationSetupPane;

    @FXML
    private TextField mapWidthInput;

    @FXML
    private TextField mapHeightInput;

    @FXML
    private ComboBox<MapVariant> mapVariantInput;

    @FXML
    private TextField initialNumberOfPlantsInput;

    @FXML
    private TextField energyPerPlantInput;

    @FXML
    private TextField newPlantsPerDayInput;

    @FXML
    private ComboBox<PlantGrowthVariant> plantGrowthVariantInput;

    @FXML
    private TextField initialNumberOfAnimalsInput;

    @FXML
    private TextField initialEnergyOfAnimalInput;

    @FXML
    private TextField wellFedEnergyInput;

    @FXML
    private TextField reproductionEnergyInput;

    @FXML
    private TextField minNumberOfMutationsInput;

    @FXML
    private TextField maxNumberOfMutationsInput;

    @FXML
    private ComboBox<MutationVariant> mutationVariantInput;

    @FXML
    private TextField lenOfGenomeInput;

    @FXML
    private ComboBox<BehaviorVariant> behaviorVariantInput;

    public SimulationSetupPresenter() {
        this.executorService = SimulationSetup.getExecutorService();
    }

    @FXML
    private void initialize() {
        textFieldProperties.put(mapWidthInput, TextFieldsConfig.MAP_WIDTH);
        textFieldProperties.put(mapHeightInput, TextFieldsConfig.MAP_HEIGHT);
        textFieldProperties.put(initialNumberOfPlantsInput, TextFieldsConfig.INITIAL_NUMBER_OF_PLANTS);
        textFieldProperties.put(energyPerPlantInput, TextFieldsConfig.ENERGY_PER_PLANT);
        textFieldProperties.put(newPlantsPerDayInput, TextFieldsConfig.NEW_PLANTS_PER_DAY);
        textFieldProperties.put(initialNumberOfAnimalsInput, TextFieldsConfig.INITIAL_NUMBER_OF_ANIMALS);
        textFieldProperties.put(initialEnergyOfAnimalInput, TextFieldsConfig.INITIAL_ENERGY_OF_ANIMAL);
        textFieldProperties.put(wellFedEnergyInput, TextFieldsConfig.WELL_FED_ENERGY);
        textFieldProperties.put(reproductionEnergyInput, TextFieldsConfig.REPRODUCTION_ENERGY);
        textFieldProperties.put(minNumberOfMutationsInput, TextFieldsConfig.MIN_NUMBER_OF_MUTATIONS);
        textFieldProperties.put(maxNumberOfMutationsInput, TextFieldsConfig.MAX_NUMBER_OF_MUTATIONS);
        textFieldProperties.put(lenOfGenomeInput, TextFieldsConfig.LEN_OF_GENOME);

        mapVariantInput.getItems().addAll(MapVariant.values());
        plantGrowthVariantInput.getItems().addAll(PlantGrowthVariant.values());
        mutationVariantInput.getItems().addAll(MutationVariant.values());
        behaviorVariantInput.getItems().addAll(BehaviorVariant.values());

        onDefaults();
        setupValidators();
        // minNumberOfMutationsInput <= maxNumberOfMutationsInput
        minNumberOfMutationsInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if (isValidInt(minNumberOfMutationsInput.getText(), TextFieldsConfig.MIN_NUMBER_OF_MUTATIONS)
                        && isValidInt(maxNumberOfMutationsInput.getText(), TextFieldsConfig.MAX_NUMBER_OF_MUTATIONS)
                        && parseInt(minNumberOfMutationsInput.getText()) > parseInt(maxNumberOfMutationsInput.getText())) {
                    maxNumberOfMutationsInput.setText(minNumberOfMutationsInput.getText());
                }
            }
        });
        maxNumberOfMutationsInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if (isValidInt(maxNumberOfMutationsInput.getText(), TextFieldsConfig.MAX_NUMBER_OF_MUTATIONS)
                        && isValidInt(minNumberOfMutationsInput.getText(), TextFieldsConfig.MIN_NUMBER_OF_MUTATIONS)
                        && parseInt(maxNumberOfMutationsInput.getText()) < parseInt(minNumberOfMutationsInput.getText())) {
                    minNumberOfMutationsInput.setText(maxNumberOfMutationsInput.getText());
                }
            }
        });
    }

    private void setupValidators() {
        for (TextField textField : textFieldProperties.keySet()) {
            setupIntValidator(textField, textFieldProperties.get(textField));
        }
        exportButton.disableProperty().bind(startButton.disableProperty());
    }

    private void setupIntValidator(TextField textField, TextFieldData<Integer> valueConfig) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // set textField color to red if input is invalid
            if (!isValidInt(newValue, valueConfig)) {
                textField.setStyle("-fx-background-color: #ff6464");
                if (isValidInt(oldValue, valueConfig)) {
                    invalidFields++;
                    startButton.disableProperty().setValue(true);
                    problemLabel.setTextFill(Color.RED);
                }
            } else {
                // clear style
                textField.setStyle(null);
                if (!isValidInt(oldValue, valueConfig)) {
                    invalidFields--;
                    problemLabel.setTextFill(Color.BLACK);
                    if (invalidFields == 0) {
                        startButton.disableProperty().setValue(false);
                    }
                }
            }
        });

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (!isValidInt(textField.getText(), valueConfig)) {
                    problemLabel.setTextFill(Color.RED);
                }
                problemLabel.setText(textField.getId() + " [" + valueConfig.min + ", " + valueConfig.max + "]");
            } else {
                problemLabel.setTextFill(Color.BLACK);
                problemLabel.setText("");
            }
        });
    }

    private boolean isValidInt(String value, TextFieldData<Integer> limit) {
        int intValue;
        try {
            intValue = parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return intValue >= limit.min && intValue <= limit.max;
    }

    private File getDefaultProperties() throws IOException {
        URL defaultProperties = getClass().getClassLoader().getResource(DEFAULT_PROPERTIES_PATH);
        if (defaultProperties == null) {
            throw new IOException("Failed to load default properties file.");
        }
        return new File(defaultProperties.getFile());
    }

    private void loadConfig(File file) throws IOException {
        // https://www.baeldung.com/java-properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        try {
            parseProperties(properties);
            invalidFields = 0;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IOException("Failed to parse properties file.");
        }
    }

    private void parseProperties(Properties properties) throws IllegalArgumentException, NullPointerException {
        for (TextField textField : textFieldProperties.keySet()) {
            TextFieldData<Integer> valueConfig = textFieldProperties.get(textField);
            String property = properties.getProperty(valueConfig.propertyName);
            if (!isValidInt(property, valueConfig)) {
                throw new IllegalArgumentException("Invalid value for " + valueConfig.propertyName);
            }
            textField.setText(property);
        }
        if (parseInt(minNumberOfMutationsInput.getText()) > parseInt(maxNumberOfMutationsInput.getText())) {
            throw new IllegalArgumentException("minNumberOfMutationsInput > maxNumberOfMutationsInput");
        }
        mapVariantInput.setValue(MapVariant.valueOf(properties.getProperty("mapVariant")));
        plantGrowthVariantInput.setValue(PlantGrowthVariant.valueOf(properties.getProperty("plantGrowthVariant")));
        mutationVariantInput.setValue(MutationVariant.valueOf(properties.getProperty("mutationVariant")));
        behaviorVariantInput.setValue(BehaviorVariant.valueOf(properties.getProperty("behaviorVariant")));
    }

    private File chooseLoadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Configuration File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("properties", "*.properties"));
        return fileChooser.showOpenDialog(simulationSetupPane.getScene().getWindow());
    }

    private File chooseSaveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Configuration File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("properties", "*.properties"));
        return fileChooser.showSaveDialog(simulationSetupPane.getScene().getWindow());
    }

    private void saveConfig(File file) throws IOException {
        Properties properties = new Properties();
        for (TextField textField : textFieldProperties.keySet()) {
            TextFieldData<Integer> valueConfig = textFieldProperties.get(textField);
            properties.setProperty(valueConfig.propertyName, textField.getText());
        }
        properties.setProperty("mapVariant", mapVariantInput.getValue().name());
        properties.setProperty("plantGrowthVariant", plantGrowthVariantInput.getValue().name());
        properties.setProperty("mutationVariant", mutationVariantInput.getValue().name());
        properties.setProperty("behaviorVariant", behaviorVariantInput.getValue().name());
        properties.store(new FileOutputStream(file), null);
    }

    private MapConfig getMapConfig() {
        return new MapConfig(
                Integer.parseInt(mapWidthInput.getText()),
                Integer.parseInt(mapHeightInput.getText()),
                mapVariantInput.getValue(),
                Integer.parseInt(initialNumberOfPlantsInput.getText()),
                Integer.parseInt(energyPerPlantInput.getText()),
                Integer.parseInt(newPlantsPerDayInput.getText()),
                plantGrowthVariantInput.getValue(),
                Integer.parseInt(initialNumberOfAnimalsInput.getText()),
                Integer.parseInt(initialEnergyOfAnimalInput.getText()),
                Integer.parseInt(wellFedEnergyInput.getText()),
                Integer.parseInt(reproductionEnergyInput.getText()),
                Integer.parseInt(minNumberOfMutationsInput.getText()),
                Integer.parseInt(maxNumberOfMutationsInput.getText()),
                mutationVariantInput.getValue(),
                Integer.parseInt(lenOfGenomeInput.getText()),
                behaviorVariantInput.getValue()
        );
    }

    public void onDefaults() {
        // Load default config
        try {
            loadConfig(getDefaultProperties());
        } catch (IOException e) {
            System.out.println("Failed to load default config file. Exiting.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void onImportConfig() {
        File file = chooseLoadFile();
        if (file == null) return;
        try {
            problemLabel.setTextFill(Color.BLACK);
            problemLabel.setText("Loaded config file: " + file.getName());
            loadConfig(file);
        } catch (IOException e) {
            onDefaults();
            problemLabel.setTextFill(Color.RED);
            problemLabel.setText("Failed to load config file.");
            System.out.println("Failed to load config file.");
            e.printStackTrace();
        }
    }

    public void onExportConfig() {
        File file = chooseSaveFile();
        if (file == null) return;
        try {
            problemLabel.setTextFill(Color.BLACK);
            problemLabel.setText("Exported config file: " + file.getName());
            saveConfig(file);
        } catch (IOException e) {
            problemLabel.setTextFill(Color.RED);
            problemLabel.setText("Failed to export config file.");
            System.out.println("Failed to export config file.");
            e.printStackTrace();
        }
    }

    public void onStart() {
        if (logToFile.isSelected()) {
            setupFileLogging();
        }
        startNewSimulationWindow();
    }

    private void setupFileLogging() {
        System.out.println("Logging to file is not implemented yet.");
    }

    private void startNewSimulationWindow() {
        MapConfig mapConfig = getMapConfig();
        SimulationRuntime simulationRuntime = new SimulationRuntime(mapConfig);
        executorService.execute(simulationRuntime);
    }
}
