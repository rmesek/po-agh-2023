package agh.ics.oop.util;

import agh.ics.oop.model.MapConfig;
import agh.ics.oop.model.SimulationEngine;

import java.util.List;

public class ConsoleDemo {
    public static void main(String[] args) {
        MapConfig mapConfig = new MapConfig(
                10,
                10,
                MapVariant.GLOBE,
                10,
                100,
                10,
                PlantGrowthVariant.FORESTED_EQUATOR,
                20,
                10,
                40,
                10,
                1,
                10,
                MutationVariant.COMPLETE_RANDOMIZATION,
                10,
                BehaviorVariant.COMPLETE_PREDESTINATION);

        SimulationEngine engine = new SimulationEngine(mapConfig, List.of(new ConsoleEventListener()), null);
        try {
            engine.setRunning(true);
            engine.setDelay(100);
            engine.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
