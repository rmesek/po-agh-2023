package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {
    @Test
    public void testNext(){
        // given
        MapDirection[] directions = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        MapDirection[] directionsResults = {MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST, MapDirection.NORTH};

        for (int i = 0; i < directionsResults.length; ++i){
            // when
            MapDirection direction = directions[i].next();

            // then
            assertEquals(direction, directionsResults[i]);
        }
    }

    @Test
    public void testPrevious(){
        // given
        MapDirection[] directions = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        MapDirection[] directionsResults = {MapDirection.WEST, MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH};

        for (int i = 0; i < directionsResults.length; ++i){
            // when
            MapDirection direction = directions[i].previous();

            // then
            assertEquals(direction, directionsResults[i]);
        }
    }
}
