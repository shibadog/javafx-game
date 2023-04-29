package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.shibadog.azugon.game.gamepad.ControllerInput;
import com.github.shibadog.azugon.game.model.CharacterState;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Operation extends BorderPane implements Initializable {
    @Autowired
    @Qualifier("blackCat")
    private CharacterState mainChara;

    @Autowired
    private List<ControllerInput> inputs;

    @FXML
    private Button up;
    @FXML
    private Button left;
    @FXML
    private Button right;
    @FXML
    private Button down;

    @FXML
    public void clickUp() {
        log.debug("up!");
        mainChara.up();
    }

    @FXML
    public void clickLeft() {
        log.debug("left!");
        mainChara.left();
    }

    @FXML
    public void clickRight() {
        log.debug("right!");
        mainChara.right();
    }

    @FXML
    public void clickDown() {
        log.debug("down!");
        mainChara.down();
    }

    @FXML
    public void keyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                clickUp();
                break;
            case LEFT:
                clickLeft();
                break;
            case RIGHT:
                clickRight();
                break;
            case DOWN:
                clickDown();
                break;
            default:
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Consumer<ControllerInput.State> changeState = x -> {
            switch(x.x()) {
                case 1:
                    mainChara.right();
                    break;
                case -1:
                    mainChara.left();
                    break;
                default:
            }
            switch(x.y()) {
                case 1:
                    mainChara.down();
                    break;
                case -1:
                    mainChara.up();
                    break;
                default:
            }
        };

        inputs.stream()
            .filter(ControllerInput::available)
            .forEach(input -> ForkJoinPool.commonPool().execute(() -> {
                while (true) {
                    input.getState().ifPresent(changeState);
                    sleep(500L);
                }
            }));
    }

    void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
