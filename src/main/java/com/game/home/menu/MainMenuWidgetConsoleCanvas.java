package com.game.home.menu;

import com.game.home.console.AbstractConsoleCanvas;
import com.game.home.console.MenuWidget;

public class MainMenuWidgetConsoleCanvas extends AbstractConsoleCanvas<MainMenuCanvas.ActionDelegate> implements MainMenuCanvas {

    private final MenuWidget<MainMenuItem> menu = new MenuWidget<>("Main menu", MainMenuItem.values());

    @Override
    public void paint() {
        menu.paint();
        switch (menu.selectItem()) {
            case START:
                delegate.onStartSelection();
                break;
            case RESUME:
                delegate.onResumeSelection();
                break;
            default:
        }
    }

    enum MainMenuItem {
        START("Start new game"),
        RESUME("Resume saved game");

        private final String title;

        MainMenuItem(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
