package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

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
    private CharacterState mainChara;
    private List<ControllerInput> inputs;

    @FXML
    private Button up;
    @FXML
    private Button left;
    @FXML
    private Button right;
    @FXML
    private Button down;

    public Operation(List<ControllerInput> inputs,
            @Qualifier("blackCat") CharacterState mainChara) {
        this.inputs = inputs;
        this.mainChara = mainChara;
    }

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
        this.operation(OperationEvent.of(e));
    }

    void changeState(ControllerInput.State state) {
        this.operation(OperationEvent.of(state));
    }

    void operation(OperationEvent e) {
        switch (e.code()) {
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
        inputs.stream()
            .filter(ControllerInput::available)
            .forEach(input -> ForkJoinPool.commonPool().execute(() -> {
                while (true) {
                    input.getState().ifPresent(this::changeState);
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
    }

    record OperationEvent(
        OperationCode code
    ) {
        enum OperationCode {
            UP, DOWN, LEFT, RIGHT, NONE
        }

        public static OperationEvent of(KeyEvent e) {
            switch (e.getCode()) {
                case UP:
                    return new OperationEvent(OperationCode.UP);
                case LEFT:
                    return new OperationEvent(OperationCode.LEFT);
                case RIGHT:
                    return new OperationEvent(OperationCode.RIGHT);
                case DOWN:
                    return new OperationEvent(OperationCode.DOWN);
                default:
                    return new OperationEvent(OperationCode.NONE);
            }
        }

        public static OperationEvent of(ControllerInput.State state) {
            switch(state.x()) {
                case 1:
                    return new OperationEvent(OperationCode.RIGHT);
                case -1:
                    return new OperationEvent(OperationCode.LEFT);
                default:
                    switch(state.y()) {
                        case 1:
                            return new OperationEvent(OperationCode.DOWN);
                        case -1:
                            return new OperationEvent(OperationCode.UP);
                        default:
                            return new OperationEvent(OperationCode.NONE);
                    }
            }
        }
    }
}
