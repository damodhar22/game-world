package com.game.home.world.factory.resume;

import com.game.home.console.AbstractConsoleCanvas;
import com.game.home.console.TextWidget;

public class ResumeConsoleCanvas extends AbstractConsoleCanvas implements ResumeCanvas {
    private final TextWidget path = new TextWidget("Type file path: ");

    @Override
    public void paint() {
        path.paint();
    }

    @Override
    public String getPath() {
        return path.getValue();
    }
}
