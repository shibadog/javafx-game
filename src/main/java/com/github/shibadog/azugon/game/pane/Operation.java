package com.github.shibadog.azugon.game.pane;

import com.github.shibadog.azugon.game.model.CharactorState;
import com.github.shibadog.azugon.game.model.CharactorState.Direction;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class Operation extends BorderPane {
    private CharactorState state;
    
    public void setState(CharactorState state) {
        this.state = state;
    }
    public CharactorState getState() {
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
        System.out.println("up!");
        state.setDirection(Direction.BACK);
        state.getPosition().up();
    }
    @FXML
    public void clickLeft() {
        System.out.println("left!");
        state.setDirection(Direction.LEFT);
        state.getPosition().left();
    }
    @FXML
    public void clickRight() {
        System.out.println("right!");
        state.setDirection(Direction.RIGHT);
        state.getPosition().right();
    }
    @FXML
    public void clickDown() {
        System.out.println("down!");
        state.setDirection(Direction.FRONT);
        state.getPosition().down();
    }

    @FXML
    public void keyPressed(KeyEvent e) {
        switch(e.getCode()) {
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