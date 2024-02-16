package agh.ics.oop.presenter;

import agh.ics.oop.model.EventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileLogger implements EventListener {
    File file; // świadomie wybrany modyfikator dostępu?

    public FileLogger(File file) {
        this.file = file;
    }

    @Override
    public void update(String event) {
        System.out.println(event);
        try {
            Files.write(file.toPath(), (event + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + file.getName());
            e.printStackTrace();
        }
    }
}
