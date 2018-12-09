package com.game.home;

import com.game.home.menu.MainMenuDisplay;
import com.game.home.menu.MainMenuWidgetConsoleCanvas;
import com.game.home.play.GameCanvas;
import com.game.home.play.GameConsoleCanvas;
import com.game.home.play.GameFactory;
import com.game.home.world.interaction.user.UserConsoleInput;
import com.game.home.world.interaction.user.UserInput;
import com.game.home.world.entities.Entity;
import com.game.home.world.entities.EntityType;
import com.game.home.world.entities.character.create.NewCharacterConsoleConsoleCanvas;
import com.game.home.world.entities.character.create.NewCharacterDisplay;
import com.game.home.world.factory.StaticGameWorldFactory;
import com.game.home.world.factory.GameWorldFactory;
import com.game.home.world.factory.resume.ResumeConsoleCanvas;
import com.game.home.world.factory.resume.ResumeDisplay;
import com.game.home.world.factory.resume.Resumer;
import com.game.home.world.factory.resume.marshaller.Marshaller;
import com.game.home.world.factory.resume.marshaller.StandardJavaMarshaller;
import com.game.home.world.factory.save.SaveConsoleCanvas;
import com.game.home.world.factory.save.SaveDisplay;
import com.game.home.world.factory.save.Saver;
import com.game.home.world.mission.MissionCompletionStrategy;
import com.game.home.world.mission.fight.FightConsoleCanvas;
import com.game.home.world.mission.fight.FightStrategy;
import com.game.home.console.Display;
import com.game.home.play.GameFactoryImpl;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

class StartPointFactory {

    private StartPointFactory() {}

    static Display newEntryPoint() {
        return new MainMenuDisplay(new MainMenuWidgetConsoleCanvas(), gameFactory());
    }

    private static GameFactory gameFactory() {
        return new GameFactoryImpl(mapFactory(), gameView());
    }

    private static GameWorldFactory mapFactory() {
        return new StaticGameWorldFactory(characterDisplay(), userMovementInput(), missionDetectionCondition(), taskCompletionStrategy(), saver(), restorer());
    }

    private static NewCharacterDisplay characterDisplay() {
        return new NewCharacterDisplay(new NewCharacterConsoleConsoleCanvas());
    }

    private static UserInput userMovementInput() {
        return new UserConsoleInput();
    }

    private static Predicate<Entity> missionDetectionCondition() {
        Set<EntityType> enemies = EnumSet.of(EntityType.CHARACTER, EntityType.WITCH, EntityType.BULL);
        return entity -> enemies.contains(entity.getType());
    }

    private static MissionCompletionStrategy taskCompletionStrategy() {
        return new FightStrategy(new FightConsoleCanvas());
    }

    private static GameCanvas gameView() {
        return new GameConsoleCanvas();
    }

    private static Resumer restorer() {
        return new ResumeDisplay(new ResumeConsoleCanvas(), marshaller());
    }

    private static Saver saver() {
        return new SaveDisplay(new SaveConsoleCanvas(), marshaller());
    }

    private static Marshaller marshaller() {
        return new StandardJavaMarshaller();
    }
}
