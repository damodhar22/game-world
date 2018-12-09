package com.game.home.world.entities.character;

public enum Role {
    ASLAN("Aslan"),
    EDMUND("Edmund"),
    LUCY("Lucy"),
    PETER("Peter"),
    SUSAN("Susan");

    private final String title;

    Role(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
