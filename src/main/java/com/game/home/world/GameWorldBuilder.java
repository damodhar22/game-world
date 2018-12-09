package com.game.home.world;

import com.game.home.world.entities.Entity;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class GameWorldBuilder {

    private final List<List<Entity>> entities = new ArrayList<>();

    private long size = 0;

    private GameWorldBuilder() {}

    public static GameWorldBuilder map() {
        return new GameWorldBuilder();
    }

    public GameWorldBuilder line(Entity... entities) {
        if (entities.length == 0) {
            throw new IllegalStateException("It is not possible to create empty line of world");
        }

        if (size == 0) {
            size = entities.length;
        } else if (size != entities.length) {
            throw new IllegalStateException("It is not possible to create world lines with different size");
        }

        this.entities.add(unmodifiableList(asList(entities)));
        return this;
    }

    public List<List<Entity>> create() {
        if (entities.isEmpty()) {
            throw new IllegalStateException("It is not possible to create empty world");
        }
        return unmodifiableList(entities);
    }
}
