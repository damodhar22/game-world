package com.game.home.menu;

import com.game.home.console.AbstractDisplay;
import com.game.home.play.GameFactory;

public class MainMenuDisplay extends AbstractDisplay<MainMenuCanvas> implements MainMenuCanvas.ActionDelegate {

    private final GameFactory gameFactory;

    public MainMenuDisplay(MainMenuCanvas canvas, GameFactory gameFactory) {
        super(canvas);
        this.canvas.setDelegate(this);
        this.gameFactory = gameFactory;
    }

    @Override
    public void onStartSelection() {
        gameFactory.create().start();
    }

    @Override
    public void onResumeSelection() {
        gameFactory.resume().start();
    }
}
