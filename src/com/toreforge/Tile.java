package com.toreforge;

public class Tile {
    private char value;
    private int x;
    private int y;

    public Tile(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
