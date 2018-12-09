package com.game.home.world.entities.character.create;

import com.game.home.console.Canvas;
import com.game.home.world.entities.character.Role;

public interface NewCharacterCanvas extends Canvas<NewCharacterCanvas.ActionDelegate> {
    interface ActionDelegate {
        void onSelection(Role role);

        void onSelection(String name);

        void onCompleted();
    }
}
