package com.game.home.console;

public interface Canvas<T> {
    void setDelegate(T delegate);

    void paint();

    void clean();
}
