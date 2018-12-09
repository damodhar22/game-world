package com.game.home.console;

public abstract class AbstractDisplay<T extends Canvas> implements Display {

    protected final T canvas;

    public AbstractDisplay(T canvas) {
        this.canvas = canvas;
    }

    @Override
    public void display() {
        canvas.paint();
    }
}