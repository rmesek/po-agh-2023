package agh.ics.oop.presenter;

import agh.ics.oop.model.MapConfig;
import agh.ics.oop.util.BehaviorVariant;
import agh.ics.oop.util.MapVariant;
import agh.ics.oop.util.MutationVariant;
import agh.ics.oop.util.PlantGrowthVariant;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SimulationSetupPresenter {
    private static final String DEFAULT_PROPERTIES_PATH = "default.properties";
    private final List<TextField> textFields = new ArrayList<>();
    private final List<ComboBox<?>> comboBoxes = new ArrayList<>();
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

    @FXML
    private void initialize() {
        textFields.add(mapWidthInput);
        textFields.add(mapHeightInput);
        textFields.add(initialNumberOfPlantsInput);
        textFields.add(energyPerPlantInput);
        textFields.add(newPlantsPerDayInput);
        textFields.add(initialNumberOfAnimalsInput);
        textFields.add(initialEnergyOfAnimalInput);
        textFields.add(wellFedEnergyInput);
        textFields.add(reproductionEnergyInput);
        textFields.add(minNumberOfMutationsInput);
        textFields.add(maxNumberOfMutationsInput);
        textFields.add(lenOfGenomeInput);

        mapVariantInput.getItems().addAll(MapVariant.values());
        plantGrowthVariantInput.getItems().addAll(PlantGrowthVariant.values());
        mutationVariantInput.getItems().addAll(MutationVariant.values());
        behaviorVariantInput.getItems().addAll(BehaviorVariant.values());

        comboBoxes.add(mapVariantInput);
        comboBoxes.add(plantGrowthVariantInput);
        comboBoxes.add(mutationVariantInput);
        comboBoxes.add(behaviorVariantInput);

        onDefaults();
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
        // https://docs.oracle.com/javase/8/docs/api/java/io/File.html
        System.out.println(file);  // TODO: remove

        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        try {
            parseProperties(properties);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IOException("Failed to parse properties file.");
        }
    }

    private void parseProperties(Properties properties) {
        mapWidthInput.setText(properties.getProperty("mapWidth"));
        mapHeightInput.setText(properties.getProperty("mapHeight"));
        mapVariantInput.setValue(MapVariant.valueOf(properties.getProperty("mapVariant")));
        initialNumberOfPlantsInput.setText(properties.getProperty("initialNumberOfPlants"));
        energyPerPlantInput.setText(properties.getProperty("energyPerPlant"));
        newPlantsPerDayInput.setText(properties.getProperty("newPlantsPerDay"));
        plantGrowthVariantInput.setValue(PlantGrowthVariant.valueOf(properties.getProperty("plantGrowthVariant")));
        initialNumberOfAnimalsInput.setText(properties.getProperty("initialNumberOfAnimals"));
        initialEnergyOfAnimalInput.setText(properties.getProperty("initialEnergyOfAnimal"));
        wellFedEnergyInput.setText(properties.getProperty("wellFedEnergy"));
        reproductionEnergyInput.setText(properties.getProperty("reproductionEnergy"));
        minNumberOfMutationsInput.setText(properties.getProperty("minNumberOfMutations"));
        maxNumberOfMutationsInput.setText(properties.getProperty("maxNumberOfMutations"));
        mutationVariantInput.setValue(MutationVariant.valueOf(properties.getProperty("mutationVariant")));
        lenOfGenomeInput.setText(properties.getProperty("lenOfGenome"));
        behaviorVariantInput.setValue(BehaviorVariant.valueOf(properties.getProperty("behaviorVariant")));
    }

    private MapConfig getMapConfig() {  // TODO: validate input
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

    private File chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Configuration File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("properties", "*.properties"));
        return fileChooser.showOpenDialog(simulationSetupPane.getScene().getWindow());
    }

    private File saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Configuration File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("properties", "*.properties"));
        return fileChooser.showSaveDialog(simulationSetupPane.getScene().getWindow());
    }

    public void onStart() {
        MapConfig mapConfig = getMapConfig();
        System.out.println(mapConfig);
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
        // https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
        File file = chooseFile();
        if (file == null) return;
        try {
            loadConfig(file);
        } catch (IOException e) {
            System.out.println("Failed to load config file.");
            e.printStackTrace();
        }
    }

    public void onExportConfig() {
        File file = saveFile();
        if (file == null) return;
        try {
            saveConfig(file);
        } catch (IOException e) {
            System.out.println("Failed to save config file.");
            e.printStackTrace();
        }
    }

    private void saveConfig(File file) throws IOException {  // TODO: validate input
        Properties properties = new Properties();
        properties.setProperty("mapWidth", mapWidthInput.getText());
        properties.setProperty("mapHeight", mapHeightInput.getText());
        properties.setProperty("mapVariant", mapVariantInput.getValue().name());
        properties.setProperty("initialNumberOfPlants", initialNumberOfPlantsInput.getText());
        properties.setProperty("energyPerPlant", energyPerPlantInput.getText());
        properties.setProperty("newPlantsPerDay", newPlantsPerDayInput.getText());
        properties.setProperty("plantGrowthVariant", plantGrowthVariantInput.getValue().name());
        properties.setProperty("initialNumberOfAnimals", initialNumberOfAnimalsInput.getText());
        properties.setProperty("initialEnergyOfAnimal", initialEnergyOfAnimalInput.getText());
        properties.setProperty("wellFedEnergy", wellFedEnergyInput.getText());
        properties.setProperty("reproductionEnergy", reproductionEnergyInput.getText());
        properties.setProperty("minNumberOfMutations", minNumberOfMutationsInput.getText());
        properties.setProperty("maxNumberOfMutations", maxNumberOfMutationsInput.getText());
        properties.setProperty("mutationVariant", mutationVariantInput.getValue().name());
        properties.setProperty("lenOfGenome", lenOfGenomeInput.getText());
        properties.setProperty("behaviorVariant", behaviorVariantInput.getValue().name());
        properties.store(new FileOutputStream(file), null);
    }

}
