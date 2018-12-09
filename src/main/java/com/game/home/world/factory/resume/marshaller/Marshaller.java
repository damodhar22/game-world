package com.game.home.world.factory.resume.marshaller;

import com.game.home.world.entities.Entity;

import java.util.List;

public interface Marshaller {
    void marshall(List<List<Entity>> entities, String path);

    List<List<Entity>> unmarshall(String path);
}
