package com.game.home.world.mission;

import com.game.home.world.entities.Entity;

public interface MissionCompletionStrategy {
    void complete(Entity user, Entity task);
}
