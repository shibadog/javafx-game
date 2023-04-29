package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.shibadog.azugon.game.model.CharacterState;
import com.github.shibadog.azugon.game.model.MapModel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

@Component
public class AzugonGame extends BorderPane implements Initializable {
    @FXML
    private Pane mainCanvas;
    @FXML
    private MainCanvas mainCanvasController;

    @FXML
    private BorderPane operation;
    @FXML
    private Operation operationController;

    @Autowired
    @Qualifier("mainMap")
    private MapModel map;

    @Autowired
    @Qualifier("blackCat")
    private CharacterState blackCat;

    @Autowired
    @Qualifier("whiteCat")
    private CharacterState whiteCat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline whiteCatOperation = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            switch (ThreadLocalRandom.current().nextInt(5)) {
                case 1:
                    whiteCat.up();
                    break;
                case 2:
                    whiteCat.left();
                    break;
                case 3:
                    whiteCat.right();
                    break;
                case 4:
                    whiteCat.down();
                    break;
                default:
            }
        }));
        whiteCatOperation.setCycleCount(Timeline.INDEFINITE);
        whiteCatOperation.play();
    }

    void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
