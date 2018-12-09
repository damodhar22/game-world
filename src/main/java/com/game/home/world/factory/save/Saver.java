package com.game.home.world.factory.save;

import com.game.home.world.entities.Entity;

import java.util.List;

public interface Saver {
    void save(List<List<Entity>> entities);
}
