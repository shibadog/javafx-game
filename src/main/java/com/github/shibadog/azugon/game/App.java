package com.github.shibadog.azugon.game;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer = 
            ac -> {
                ac.registerBean(Application.class, () -> App.this);
                ac.registerBean(Parameters.class, this::getParameters);
                ac.registerBean(HostServices.class, this::getHostServices);
            };

        this.context = new SpringApplicationBuilder()
            .sources(BootifulApp.class)
            .initializers(initializer)
            .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(final Stage stage) {
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

    public static class StageReadyEvent extends ApplicationEvent {
        public Stage getStage() {
            return Stage.class.cast(getSource());
        }
    
        public StageReadyEvent(Stage source) {
            super(source);
        }
    }
}