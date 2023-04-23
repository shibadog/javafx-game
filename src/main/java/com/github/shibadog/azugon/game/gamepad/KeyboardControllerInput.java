package com.github.shibadog.azugon.game.gamepad;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;

@Slf4j
public class KeyboardControllerInput implements ControllerInput {

    final Controller controller;

    volatile boolean spaceKeyReleased;

    public KeyboardControllerInput() {
        this.controller = ControllerInput.detectController(Controller.Type.KEYBOARD);
        this.spaceKeyReleased = true;
    }

    @Override
    public boolean available() {
        return controller != NullController.INSTANCE;
    }

    @Override
    public Optional<ControllerInput.State> getState() {
        if (controller.poll()) {
            int x = 0;
            if (controller.getComponent(Identifier.Key.LEFT).getPollData() > 0.0f) {
                x = -1;
            } else if(controller.getComponent(Identifier.Key.RIGHT).getPollData() > 0.0f) {
                x = 1;
            }
            int y = 0;
            if (controller.getComponent(Identifier.Key.UP).getPollData() > 0.0f) {
                y = -1;
            } else if(controller.getComponent(Identifier.Key.DOWN).getPollData() > 0.0f) {
                y = 1;
            }
            // Spaceキー
            boolean spaceKeyPushed = controller.getComponent(Identifier.Key.SPACE).getPollData() > 0.0f;
            boolean attackButtonPushed = spaceKeyReleased && spaceKeyPushed;
            spaceKeyReleased = !spaceKeyPushed;
            if (x != 0 || y != 0 || attackButtonPushed) {
                var state = new ControllerInput.State(x, y, attackButtonPushed);
                log.debug("debug input: {}", state);
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }

}
