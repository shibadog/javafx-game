package com.github.shibadog.azugon.game.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import com.github.shibadog.azugon.game.gamepad.ControllerInput;
import com.github.shibadog.azugon.game.gamepad.GamepadControllerInput;
import com.github.shibadog.azugon.game.gamepad.KeyboardControllerInput;
import com.github.shibadog.azugon.game.gamepad.StickControllerInput;
import com.github.shibadog.azugon.game.model.CharacterState;
import com.github.shibadog.azugon.game.model.MapModel;

@Configuration
public class GameConfig {
    
    @Bean("mainMap")
    MapModel mainMap() {
        return new MapModel(200, 200);
    }

    @Bean("blackCat")
    @Primary
    CharacterState blackCat(MapModel mapModel,
            @Value("${classpath:/image/blackcat.png}") Resource resource) throws IOException {
        int key = mapModel.createCharacter();
        return new CharacterState(resource.getURL(), 32, 32, mapModel.getPosition(key));
    }
    
    @Bean("whiteCat")
    CharacterState whiteCat(MapModel mapModel,
            @Value("${classpath:/image/whitecat.png}") Resource resource) throws IOException {
        int key = mapModel.createCharacter(2, 3);
        var whiteCat = new CharacterState(resource.getURL(), 32, 32, mapModel.getPosition(key));
        return whiteCat;
    }

    @Bean
    ControllerInput keyControllerInput() {
        return new KeyboardControllerInput();
    }

    @Bean
    ControllerInput stickControllerInput() {
        return new StickControllerInput();
    }

    @Bean
    ControllerInput gamepadControllerInput() {
        return new GamepadControllerInput();
    }
}
