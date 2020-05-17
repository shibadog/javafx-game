package com.github.shibadog.azugon.game.model;

import java.net.URL;

import com.github.shibadog.azugon.game.model.MapModel.Position;

import javafx.scene.image.Image;

public class CharactorState {
    Image image;
    public boolean isOneStep;
    private int width;
    private int height;
    private Direction direction;
    private Position position;

    public final Direction getDirection() {
        return direction;
    }
    public final void setDirection(Direction direction) {
        this.direction = direction;
    }
    public final Position getPosition() {
        return position;
    }
    public final Image getImage() {
        return image;
    }

    public CharactorState(URL image, int width, int height, Position position) {
        this.width = width;
        this.height = height;
        direction = Direction.FRONT;
        this.position = position;
        this.image = new Image(image.toString());
    }

    public CharactorState nextStep() {
        this.isOneStep = !isOneStep;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public enum Direction {
        FRONT, LEFT, RIGHT, BACK,;
    }

}