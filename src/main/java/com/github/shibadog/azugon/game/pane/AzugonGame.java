package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.github.shibadog.azugon.game.model.CharactorState;
import com.github.shibadog.azugon.game.model.MapModel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AzugonGame extends BorderPane implements Initializable {
    public CharactorState blackCat;
    public MapModel map;

    @FXML
    private Pane mainCanvas;
    @FXML
    private MainCanvas mainCanvasController;

    @FXML
    private BorderPane operation;
    @FXML
    private Operation operationController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        map = new MapModel(200, 200);
        int key = map.createCharactor();
        URL blackCatPath = getClass().getResource("/blackcat.png");
        blackCat = new CharactorState(blackCatPath, 32, 32, map.getPosition(key));
        operationController.setState(blackCat);
        mainCanvasController.addCharactor(blackCat);

        int whiteCatKey = map.createCharactor(2, 3);
        URL whiteCatPath = getClass().getResource("/whitecat.png");
        final CharactorState whiteCat = new CharactorState(whiteCatPath, 32, 32, map.getPosition(whiteCatKey));
        Timeline whiteCatOperation = new Timeline(new KeyFrame(Duration.seconds(2), (e) -> {
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
        mainCanvasController.addCharactor(whiteCat);
    }
}