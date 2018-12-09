package com.game.home.play;

import com.game.home.console.Canvas;
import com.game.home.world.GameWorld;

public interface GameCanvas extends Canvas {
    void paint(GameWorld gameWorld);

    void showWinnerMessage();

    void showGameOverMessage();

    void showGameSavedMessage();
}
