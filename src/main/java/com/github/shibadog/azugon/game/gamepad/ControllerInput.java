package com.github.shibadog.azugon.game.gamepad;

import java.util.Optional;
import java.util.stream.Stream;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public interface ControllerInput {
    Optional<ControllerInput.State> getState();
    boolean available();
    static Controller detectController(Controller.Type type) {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        return Stream.of(controllers)
            .filter(cnt -> cnt != null && cnt.getType() == type)
            .findAny()
            .orElse(NullController.INSTANCE);
    }

    static record State(
        int x,
        int y,
        boolean attackButtonPushed
    ) {}
}
