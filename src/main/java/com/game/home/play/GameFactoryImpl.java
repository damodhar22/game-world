package com.game.home.play;

import com.game.home.world.GameWorld;
import com.game.home.world.factory.GameWorldFactory;

public class GameFactoryImpl implements GameFactory {

    private final GameWorldFactory gameWorldFactory;

    private final GameCanvas canvas;

    public GameFactoryImpl(GameWorldFactory gameWorldFactory, GameCanvas canvas) {
        this.gameWorldFactory = gameWorldFactory;
        this.canvas = canvas;
    }

    @Override
    public Game create() {
        return create(gameWorldFactory.create());
    }

    @Override
    public Game resume() {
        return create(gameWorldFactory.resume());
    }

    private Game create(GameWorld map) {
        return new GameImpl(map, canvas);
    }
}
