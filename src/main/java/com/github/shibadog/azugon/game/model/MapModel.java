package com.github.shibadog.azugon.game.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapModel {
    private static int TIP = 32;
    private int width;
    private int height;
    private Map<Integer, Position> characters = Collections.synchronizedMap(new HashMap<Integer, Position>());
    private MapStatus[][] mapStatus;
    private int maxX;
    private int maxY;

    public MapModel(int width, int height) {
        this.width = width;
        this.height = height;
        maxX = width / TIP;
        maxY = height / TIP;
        this.mapStatus = new MapStatus[maxX][maxY];
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                this.mapStatus[x][y] = MapStatus.NONE;
            }
        }
    }

    public int createCharacter(int xx, int yy) {
        int count = characters.entrySet().size();
        Integer key = count + 1;
        characters.put(key, new Position(32, xx, yy));
        return key;
    }

    public int createCharacter() {
        return createCharacter(0, 0);
    }

    public Position getPosition(int key) {
        if (!characters.containsKey(key))
            throw new IllegalArgumentException("not exists character");
        return characters.get(key);
    }

    public enum MapStatus {
        NONE, CHARACTER, DONT_MOVE,;
    }

    public class Position {
        @Getter(value = AccessLevel.PACKAGE)
        private int x;
        @Getter(value = AccessLevel.PACKAGE)
        private int y;
        private int xx;
        private int yy;
        private int tile;

        public Position(int tile, int xx, int yy) {
            x = xx * tile;
            y = yy * tile;
            this.xx = xx;
            this.yy = yy;
            this.tile = tile;
            if (MapModel.this.mapStatus[xx][yy] != MapStatus.NONE)
                throw new IllegalArgumentException();
            MapModel.this.mapStatus[xx][yy] = MapStatus.CHARACTER;
        }

        public Position(int tile) {
            this(tile, 0, 0);
        }

        public Position up() {
            if (y - tile < 0)
                return this;
            if (cantMove(xx, yy - 1))
                return this;
            y = y - tile;
            return move(xx, yy, xx, --yy);
        }

        public Position down() {
            if (y + tile > MapModel.this.height)
                return this;
            if (cantMove(xx, yy + 1))
                return this;
            y = y + tile;
            return move(xx, yy, xx, ++yy);
        }

        public Position left() {
            if (x - tile < 0)
                return this;
            if (cantMove(xx - 1, yy))
                return this;
            x = x - tile;
            return move(xx, yy, --xx, yy);
        }

        public Position right() {
            if (x + tile > MapModel.this.width)
                return this;
            if (cantMove(xx + 1, yy))
                return this;
            x = x + tile;
            return move(xx, yy, ++xx, yy);
        }

        protected boolean cantMove(int nextXX, int nextYY) {
            try {
                if (MapModel.this.mapStatus.length <= nextXX)
                    return true;
                if (MapModel.this.mapStatus[nextXX].length <= nextYY)
                    return true;
                return MapModel.this.mapStatus[nextXX][nextYY] != MapStatus.NONE;
            } catch (RuntimeException e) {
                log.info(e.getMessage());
            }
            return true;
        }

        protected synchronized Position move(int xx, int yy, int nextXX, int nextYY) {
            MapModel.this.mapStatus[xx][yy] = MapStatus.NONE;
            MapModel.this.mapStatus[nextXX][nextYY] = MapStatus.CHARACTER;
            return this;
        }
    }
}
