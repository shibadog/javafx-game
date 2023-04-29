package com.github.shibadog.azugon.game;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class BootifulApp {
    public static void main(final String... args) {
        Application.launch(App.class, args);
    }
}
