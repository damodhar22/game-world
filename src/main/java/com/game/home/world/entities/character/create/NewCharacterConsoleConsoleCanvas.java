package com.game.home.world.entities.character.create;

import com.game.home.console.AbstractConsoleCanvas;
import com.game.home.console.MenuWidget;
import com.game.home.console.TextWidget;
import com.game.home.world.entities.character.Role;

public class NewCharacterConsoleConsoleCanvas extends AbstractConsoleCanvas<NewCharacterCanvas.ActionDelegate> implements NewCharacterCanvas {

    private final TextWidget characterName = new TextWidget("Create character by entering name:");

    private final MenuWidget<Role> raceMenu = new MenuWidget<>("Select Role:", Role.values());

    @Override
    public void paint() {
        System.out.println("New Character Menu");

        characterName.paint();
        delegate.onSelection(characterName.getValue());

        raceMenu.paint();
        delegate.onSelection(raceMenu.selectItem());

        delegate.onCompleted();
    }
}
