package com.game.home.world.mission.fight;

import com.game.home.console.AbstractConsoleCanvas;
import com.game.home.world.entities.Entity;
import com.game.home.world.entities.character.GameCharacter;
import com.game.home.console.MenuWidget;

import static java.lang.String.format;

public class FightConsoleCanvas extends AbstractConsoleCanvas<FightCanvas.ActionDelegate> implements FightCanvas {

    private final MenuWidget<ActionFight> menu = new MenuWidget<>("Choose your action:", ActionFight.values());

    @Override
    public void paint() {
        menu.paint();
        switch (menu.selectItem()) {
            case ATTACK:
                delegate.onUserAttack();
                break;
            case DEFENSE:
                delegate.onUserDefend();
                break;
            case DO_NOTHING:
                delegate.onUserDoNothing();
                break;
            default:
        }
    }

    @Override
    public void paintUser(Entity user) {
        paintEntity(user);
    }

    @Override
    public void paintEnemy(Entity enemy) {
        paintEntity(enemy);
    }

    @Override
    public void paintFight(Entity attacker, Entity defender, int damage) {
        System.out.println(format(
                "%s attacks %s. %s got a damage - %d",
                attacker.getName(), defender.getName(), defender.getName(), damage
        ));
    }

    private void paintEntity(Entity entity) {
        if (entity instanceof GameCharacter) {
            GameCharacter character = (GameCharacter) entity;
            System.out.println(String.format(
                    "Name: %s; Role: %s; Health: %d; Attack power: %d",
                    character.getName(), character.getRole(), character.getHealth(), character.getAttackPower()

            ));
        } else {
            System.out.println(format(
                    "Name: %s; Type: %s; Health: %d; Attack power: %d",
                    entity.getName(), entity.getType(), entity.getHealth(), entity.getAttackPower()
            ));
        }
    }

    private enum ActionFight {
        ATTACK("Attack the opponent"),
        DEFENSE("Defend yourself"),
        DO_NOTHING("Do nothing");

        private final String title;

        ActionFight(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
