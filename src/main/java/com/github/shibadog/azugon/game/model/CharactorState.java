package com.github.shibadog.azugon.game.model;

import java.net.URL;

import com.github.shibadog.azugon.game.model.MapModel.Position;

import javafx.scene.image.Image;
import lombok.Getter;

public class CharactorState {
    private boolean isOneStep;
    private Direction direction;
    private Position position;
    @Getter
    Image image;
    @Getter
    private int width;
    @Getter
    private int height;

    public CharactorState(URL image, int width, int height, Position position) {
        this.width = width;
        this.height = height;
        direction = Direction.FRONT;
        this.position = position;
        this.image = new Image(image.toString());
    }

    public CharactorState up() {
        direction = Direction.BACK;
        position.up();
        return this;
    }

    public CharactorState left() {
        direction = Direction.LEFT;
        position.left();
        return this;
    }

    public CharactorState right() {
        direction = Direction.RIGHT;
        position.right();
        return this;
    }

    public CharactorState down() {
        direction = Direction.FRONT;
        position.down();
        return this;
    }

    public CharactorState nextStep() {
        this.isOneStep = !isOneStep;
        return this;
    }

    public int getX() {
        return isOneStep ? 0 : width * 2;
    }

    public int getY() {
        switch (direction) {
            case FRONT:
                return 0;
            case LEFT:
                return height * 1;
            case RIGHT:
                return height * 2;
            case BACK:
                return height * 3;
            default:
                return 0;
        }
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public enum Direction {
        FRONT, LEFT, RIGHT, BACK,;
    }

}