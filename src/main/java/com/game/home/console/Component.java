package com.game.home.console;

import static java.util.stream.IntStream.rangeClosed;

public interface Component {

    void paint();

    default void repaint() {
        clean();
        paint();
    }

    default void clean() {
        rangeClosed(1, 50).forEach(value -> System.out.println());
    }
}
