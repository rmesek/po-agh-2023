package agh.ics.oop.util;

import agh.ics.oop.model.EventListener;

public class ConsoleEventListener implements EventListener {
    @Override
    public void update(String event) {
        System.out.println(event);
    }
}
