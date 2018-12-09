package com.game.home.world.entities.character.create;

import com.game.home.console.AbstractDisplay;
import com.game.home.world.entities.Entity;
import com.game.home.world.entities.character.Role;

import static java.util.Objects.requireNonNull;
import static com.game.home.world.entities.EntityFactory.userCharacter;
import static com.game.home.world.entities.character.create.NewCharacterCanvas.ActionDelegate;

public class NewCharacterDisplay extends AbstractDisplay<NewCharacterCanvas> implements NewCharacterFactory, ActionDelegate {

    private Role role;

    private String name;

    public NewCharacterDisplay(NewCharacterCanvas canvas) {
        super(canvas);
        canvas.setDelegate(this);
    }

    @Override
    public void onSelection(Role role) {
        this.role = role;
    }

    @Override
    public void onSelection(String name) {
        this.name = name;
    }

    @Override
    public void onCompleted() {
        requireNonNull(name, "Cannot create a character without name parameter");
        requireNonNull(role, "Cannot create a character without role parameter");
    }

    public Entity getGameCharacter() {
        display();
        return userCharacter(name, role);
    }
}
