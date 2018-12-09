package com.game.home.world.factory;

import com.game.home.world.interaction.user.UserInput;
import com.game.home.world.entities.Entity;
import com.game.home.world.entities.EntityFactory;
import com.game.home.world.entities.character.create.NewCharacterFactory;
import com.game.home.world.factory.resume.Resumer;
import com.game.home.world.factory.save.Saver;
import com.game.home.world.GameWorld;
import com.game.home.world.MainGameWorld;
import com.game.home.world.mission.MissionCompletionStrategy;

import java.util.List;
import java.util.function.Predicate;

import static com.game.home.world.GameWorldBuilder.map;

public class StaticGameWorldFactory implements GameWorldFactory {

    private final NewCharacterFactory newCharacterFactory;

    private final UserInput userInput;

    private final Predicate<Entity> taskDetectionCondition;

    private final MissionCompletionStrategy missionCompletionStrategy;

    private final Saver saver;

    private final Resumer resumer;

    public StaticGameWorldFactory(NewCharacterFactory newCharacterFactory,
                                  UserInput userInput,
                                  Predicate<Entity> taskDetectionCondition,
                                  MissionCompletionStrategy missionCompletionStrategy,
                                  Saver saver,
                                  Resumer resumer) {
        this.newCharacterFactory = newCharacterFactory;
        this.userInput = userInput;
        this.taskDetectionCondition = taskDetectionCondition;
        this.missionCompletionStrategy = missionCompletionStrategy;
        this.saver = saver;
        this.resumer = resumer;
    }

    @Override
    public GameWorld create() {
        return create(entities(newCharacterFactory.getGameCharacter()));
    }

    private GameWorld create(List<List<Entity>> entities) {
        return new MainGameWorld(entities, userInput, taskDetectionCondition, missionCompletionStrategy, saver);
    }

    private List<List<Entity>> entities(Entity character) {
        return map()
                .line(EntityFactory.path(), EntityFactory.path(), EntityFactory.path(EntityFactory.witch()), EntityFactory.tree(), EntityFactory.path())
                .line(EntityFactory.path(), EntityFactory.path(), EntityFactory.path(), EntityFactory.tree(), EntityFactory.tree())
                .line(EntityFactory.path(), EntityFactory.path(), EntityFactory.path(character), EntityFactory.path(), EntityFactory.path(EntityFactory.bull()))
                .line(EntityFactory.path(), EntityFactory.hill(), EntityFactory.path(), EntityFactory.path(), EntityFactory.path())
                .line(EntityFactory.path(EntityFactory.witch()), EntityFactory.tree(), EntityFactory.path(), EntityFactory.path(), EntityFactory.path())
                .create();
    }

    @Override
    public GameWorld resume() {
        return create(resumer.resume());
    }
}
