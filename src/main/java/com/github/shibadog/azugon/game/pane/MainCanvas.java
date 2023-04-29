package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.github.shibadog.azugon.game.model.CharacterState;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

@Component
public class MainCanvas extends Pane implements Initializable {
    private Set<CharacterState> characters = Collections.synchronizedSet(new HashSet<CharacterState>());

    public void addCharacter(CharacterState state) {
        characters.add(state);
    }

    @FXML
    private Canvas mainCanvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline reDraw = new Timeline(new KeyFrame(Duration.millis(300), event -> draw()));
        reDraw.setCycleCount(Timeline.INDEFINITE);
        reDraw.play();

        Timeline step = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> characters.forEach(s -> s.nextStep())));
        step.setCycleCount(Timeline.INDEFINITE);
        step.play();
    }

    protected void draw() {
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
        characters.forEach(state -> gc
                .drawImage(state.getImage(), state.getX(), state.getY(), state.getWidth(), state.getHeight(),
                    state.getPositionX(), state.getPositionY(), state.getWidth(), state.getHeight())
        );
    }

}
