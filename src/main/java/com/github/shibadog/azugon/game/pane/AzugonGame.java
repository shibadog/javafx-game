package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.github.shibadog.azugon.game.gamepad.ControllerInput;
import com.github.shibadog.azugon.game.gamepad.KeyboardControllerInput;
import com.github.shibadog.azugon.game.gamepad.StickControllerInput;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapModel map = new MapModel(200, 200);
        int key = map.createCharacter();
        URL blackCatPath = getClass().getResource("/image/blackcat.png");
        final CharacterState blackCat = new CharacterState(blackCatPath, 32, 32, map.getPosition(key));
        operationController.setState(blackCat);
        mainCanvasController.addCharacter(blackCat);

        int whiteCatKey = map.createCharacter(2, 3);
        URL whiteCatPath = getClass().getResource("/image/whitecat.png");
        final CharacterState whiteCat = new CharacterState(whiteCatPath, 32, 32, map.getPosition(whiteCatKey));
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
        mainCanvasController.addCharacter(whiteCat);

        ControllerInput stickInput = new StickControllerInput();
        ControllerInput keyboardInput = new KeyboardControllerInput();

        Consumer<ControllerInput.State> changeState = x -> {
            switch(x.x()) {
                case 1:
                    blackCat.right();
                    break;
                case -1:
                    blackCat.left();
                    break;
                default:
            }
            switch(x.y()) {
                case 1:
                    blackCat.down();
                    break;
                case -1:
                    blackCat.up();
                    break;
                default:
            }
        };
        // ゲームパッド入力
        if (stickInput.available()) {
            ForkJoinPool.commonPool().execute(() -> {
                while (true) {
                    stickInput.getState().ifPresent(changeState);
                    sleep(500L);
                }
            });
        }
        // キーボード入力
        if (keyboardInput.available()) {
            ForkJoinPool.commonPool().execute(() -> {
                while (true) {
                    keyboardInput.getState().ifPresent(changeState);
                    sleep(500L);
                }
            });
        }
    }

    void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
