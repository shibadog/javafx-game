package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.github.shibadog.azugon.game.model.CharactorState;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MainCanvas extends Pane implements Initializable {
    private Set<CharactorState> charactors = Collections.synchronizedSet(new HashSet<CharactorState>());

    public void addCharactor(CharactorState state) {
        charactors.add(state);
    }

    @FXML
    private Canvas mainCanvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline reDrow = new Timeline(new KeyFrame(Duration.millis(300), (event) -> draw()));
        reDrow.setCycleCount(Timeline.INDEFINITE);
        reDrow.play();

        Timeline step = new Timeline(
                new KeyFrame(Duration.millis(1000), (event) -> charactors.forEach(s -> s.nextStep())));
        step.setCycleCount(Timeline.INDEFINITE);
        step.play();
    }

    protected void draw() {
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
        charactors.forEach(state -> {
            gc.drawImage(state.getImage(), state.getX(), state.getY(), state.getWidth(), state.getHeight(),
                    state.getPositionX(), state.getPositionY(), state.getWidth(), state.getHeight());
        });
    }

}