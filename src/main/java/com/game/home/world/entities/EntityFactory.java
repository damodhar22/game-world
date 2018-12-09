package com.game.home.world.entities;

import com.game.home.world.entities.character.GameCharacter;
import com.game.home.world.entities.character.Role;

import static com.game.home.world.entities.EntityType.*;

public final class EntityFactory {

    private EntityFactory() {}

    public static Entity path() {
        return new ContainerEntity("Path", PATH, 0);
    }

    public static Entity path(Entity entity) {
        Entity path = path();
        path.take(entity);
        return path;
    }

    public static Entity witch() {
        return new SimpleEntity("Witch", WITCH, 10);
    }

    public static Entity bull() {
        return new SimpleEntity("Bull", BULL, 20);
    }

    public static Entity tree() {
        return new SimpleEntity("Tree", TREE, 0);
    }

    public static Entity hill() {
        return new SimpleEntity("Hill", HILL, 0);
    }

    public static Entity userCharacter(String name, Role role) {
        return new GameCharacter(name, true, role, 50);
    }

    public static Entity character(String name, Role role) {
        return character(name, role, 30);
    }

    public static Entity character(String name, Role role, int attachPower) {
        return new GameCharacter(name, false, role, attachPower);
    }
}
