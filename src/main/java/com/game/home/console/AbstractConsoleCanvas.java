package com.game.home.console;

public abstract class AbstractConsoleCanvas<T> implements Canvas<T>, Component {

    protected T delegate;

    @Override
    public void setDelegate(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public void clean() {
        Component.super.clean();
    }
}