package com.github.shibadog.azugon.game;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(final Stage stage) {
        try {
            // FXMLのレイアウトをロード
            URL fxml = getClass().getResource("/AzugonGame.fxml");
            final Parent root = FXMLLoader.load(fxml);

            // タイトルセット
            stage.setTitle("あずごんげーむ");

            // シーン生成
            final Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(final String... args) {
        Application.launch(args);
    }
}