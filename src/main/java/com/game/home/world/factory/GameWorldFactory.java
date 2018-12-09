package com.game.home.world.factory;

import com.game.home.world.GameWorld;

public interface GameWorldFactory {
    GameWorld create();

    GameWorld resume();
}
