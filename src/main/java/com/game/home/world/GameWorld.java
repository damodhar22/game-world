package com.game.home.world;

import com.game.home.world.entities.Entity;

import java.util.List;

public interface GameWorld {

    boolean containsUserCharacter();

    boolean containsTasks();

    boolean isSaveGame();

    void goToNextIteration();

    List<List<Entity>> getEntities();
}
