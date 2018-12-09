package com.game.home.world.mission.fight;

import com.game.home.console.AbstractDisplay;
import com.game.home.world.entities.Entity;
import com.game.home.world.mission.MissionCompletionStrategy;

public class FightStrategy extends AbstractDisplay<FightCanvas> implements MissionCompletionStrategy, FightCanvas.ActionDelegate {

    private Entity user;

    private Entity opponent;

    public FightStrategy(FightCanvas canvas) {
        super(canvas);
        this.canvas.setDelegate(this);
    }

    @Override
    public void complete(Entity user, Entity enemy) {
        this.user = user;
        this.opponent = enemy;
        nextIteration();
    }

    private void nextIteration() {
        if (user.isAlive() && opponent.isAlive()) {
            canvas.paintUser(user);
            canvas.paintEnemy(opponent);
            display();
        }
    }

    @Override
    public void onUserAttack() {
        canvas.paintFight(user, opponent, opponent.isBeatenBy(user));
        canvas.paintFight(opponent, user, user.isBeatenBy(opponent));
        nextIteration();
    }

    @Override
    public void onUserDefend() {
        user.defense();
        canvas.paintFight(opponent, user, user.isBeatenBy(opponent));
        nextIteration();
    }

    @Override
    public void onUserDoNothing() {
        canvas.paintFight(opponent, user, user.isBeatenBy(opponent));
        nextIteration();
    }
}
