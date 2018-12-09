package com.game.home.play;

import com.game.home.world.GameWorld;

public class GameImpl implements Game {

    private final GameWorld map;

    private final GameCanvas canvas;

    public GameImpl(GameWorld map, GameCanvas canvas) {
        this.map = map;
        this.canvas = canvas;
    }

    @Override
    public void start() {
        canvas.paint(map);
        while (map.containsUserCharacter() && map.containsTasks() && !map.isSaveGame()) {
            map.goToNextIteration();
            canvas.paint(map);
        }

        if(map.isSaveGame()){
            canvas.showGameSavedMessage();
        } else if (map.containsUserCharacter()) {
            canvas.showWinnerMessage();
        } else {
            canvas.showGameOverMessage();
        }
    }
}
