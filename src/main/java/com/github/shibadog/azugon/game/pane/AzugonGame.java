package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
