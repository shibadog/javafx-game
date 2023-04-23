package com.github.shibadog.azugon.game.gamepad;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;

@Slf4j
public class GamepadControllerInput implements ControllerInput {

    final Controller controller;

    volatile boolean button3Released;

    public GamepadControllerInput() {
        this.controller = ControllerInput.detectController(Controller.Type.GAMEPAD);
        this.button3Released = true;
    }

    @Override
    public boolean available() {
        return controller != NullController.INSTANCE;
    }

    @Override
    public Optional<ControllerInput.State> getState() {
        if (controller.poll()) {
            float x0 = controller.getComponent(Identifier.Axis.X).getPollData();
            float y0 = controller.getComponent(Identifier.Axis.Y).getPollData();
            int x = x0 > 0.0f
                ? 1
                : x0 < -0.1f
                    ? -1
                    : 0;
            int y = y0 > 0.0f
                ? 1
                : y0 < -0.1f
                    ? -1
                    : 0;
            float b3 = controller.getComponent(Identifier.Button._2).getPollData();
            System.out.println("b3=" + b3);
            // Button._2はボタン３（0オリジン）
            boolean button3Pushed = controller.getComponent(Identifier.Button._2).getPollData() > 0.0f;
            boolean attackButtonPushed = button3Released && button3Pushed;
            button3Released = !button3Pushed;
            if (x != 0 || y != 0 || attackButtonPushed) {
                var state = new ControllerInput.State(x, y, attackButtonPushed);
                log.debug("debug input: {}", state);
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }

}
