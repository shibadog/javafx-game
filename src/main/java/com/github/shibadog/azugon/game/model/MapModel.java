package com.github.shibadog.azugon.game.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapModel {
    private int width;
    private int height;
    private Map<Integer, Position> charactors = Collections.synchronizedMap(new HashMap<Integer, Position>());

    public MapModel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int createCharactor() {
        int count = charactors.entrySet().size();
        Integer key = count + 1;
        charactors.put(key, new Position(32));
        return key;
    }

    public Position getPosition(int key) {
        if (!charactors.containsKey(key))
            throw new IllegalArgumentException("not exists charactor");
        return charactors.get(key);
    }

    public class Position {
        private int x;
        private int y;
        private int tile;
        public Position(int tile) {
            x = 0;
            y = 0;
            this.tile = tile;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    
        public Position up() {
            if (y - tile < 0) return this;
            y = y - tile;
            return this;
        }

        public Position down() {
            if (y + tile > MapModel.this.height) return this;
            y = y + tile;
            return this;
        }

        public Position left() {
            if (x - tile < 0) return this;
            x = x - tile;
            return this;
        }

        public Position right() {
            if (x + tile > MapModel.this.width) return this;
            x = x + tile;
            return this;
        }
    }
}