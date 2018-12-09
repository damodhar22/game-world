package com.game.home.world.entities.character;

import com.game.home.world.entities.SimpleEntity;

import static com.game.home.world.entities.EntityType.CHARACTER;

public class GameCharacter extends SimpleEntity {

    private final boolean isUserCharacter;

    private final Role role;

    public GameCharacter(String name, boolean isUserCharacter, Role role, int attackPower) {
        super(name, CHARACTER, attackPower);
        this.isUserCharacter = isUserCharacter;
        this.role = role;
    }

    @Override
    public boolean isUser() {
        return isUserCharacter;
    }

    public Role getRole() {
        return role;
    }
}
