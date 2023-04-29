package com.github.shibadog.azugon.game.pane;

import org.springframework.stereotype.Component;

import com.github.shibadog.azugon.game.model.CharacterState;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Operation extends BorderPane {
    private CharacterState state;

    public void setState(CharacterState state) {
        this.state = state;
    }

    public CharacterState getState() {
        return state;
    }

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
        state.up();
    }

    @FXML
    public void clickLeft() {
        log.debug("left!");
        state.left();
    }

    @FXML
    public void clickRight() {
        log.debug("right!");
        state.right();
    }

    @FXML
    public void clickDown() {
        log.debug("down!");
        state.down();
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
}
