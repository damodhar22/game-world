package com.game.home.world.factory.save;

import com.game.home.console.AbstractConsoleCanvas;
import com.game.home.console.TextWidget;

public class SaveConsoleCanvas extends AbstractConsoleCanvas implements SaveCanvas {
    private final TextWidget path = new TextWidget("Type file name: ");

    @Override
    public void paint() {
        path.paint();
    }

    @Override
    public String getPath() {
        return path.getValue();
    }
}