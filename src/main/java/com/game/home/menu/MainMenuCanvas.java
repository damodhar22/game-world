package com.game.home.menu;

import com.game.home.console.Canvas;

public interface MainMenuCanvas extends Canvas<MainMenuCanvas.ActionDelegate> {
    interface ActionDelegate {
        void onStartSelection();

        void onResumeSelection();
    }
}
