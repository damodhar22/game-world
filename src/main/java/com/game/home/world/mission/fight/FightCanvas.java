package com.game.home.world.mission.fight;

import com.game.home.console.Canvas;
import com.game.home.world.entities.Entity;

public interface FightCanvas extends Canvas<FightCanvas.ActionDelegate> {

    void paintUser(Entity user);

    void paintEnemy(Entity enemy);

    void paintFight(Entity attacker, Entity defender, int damage);

    interface ActionDelegate {
        void onUserAttack();

        void onUserDefend();

        void onUserDoNothing();
    }
}
