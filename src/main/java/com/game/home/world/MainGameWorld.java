package com.game.home.world;

import com.game.home.world.interaction.user.UserInput;
import com.game.home.world.entities.Entity;
import com.game.home.world.factory.save.Saver;
import com.game.home.world.mission.MissionCompletionStrategy;
import com.game.home.world.utils.Location;
import com.game.home.console.IntegerRange;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class MainGameWorld implements GameWorld {

    private final List<List<Entity>> entities;

    private final Predicate<Entity> taskDetectionCondition;

    private final UserInput userInput;

    private final MissionCompletionStrategy missionCompletionStrategy;

    private final Saver saver;

    private boolean saveGame;

    public MainGameWorld(List<List<Entity>> entities,
                         UserInput userInput,
                         Predicate<Entity> taskDetectionCondition,
                         MissionCompletionStrategy missionCompletionStrategy,
                         Saver saver) {
        this.entities = entities;
        this.taskDetectionCondition = taskDetectionCondition;
        this.userInput = userInput;
        this.missionCompletionStrategy = missionCompletionStrategy;
        this.saver = saver;
    }

    @Override
    public boolean containsUserCharacter() {
        return entities().anyMatch(Entity::containUserCharacter);
    }

    @Override
    public boolean containsTasks() {
        return entities().anyMatch(entity -> entity.containTasks(taskDetectionCondition));
    }

    private Stream<Entity> entities() {
        return entities.stream().flatMap(List::stream);
    }

    @Override
    public void goToNextIteration() {
        Location currentLocation = findFirstEntity(Entity::containUserCharacter)
                .orElseThrow(() -> new IllegalStateException("It is not possible to continue when no user character in the world"));
        Location nextLocation = userInput.getNextPosition(currentLocation);
        if (nextLocation.getX() == -1 && nextLocation.getY() == -1){
            saveGame();
            return;
        }
        if (isValid(nextLocation, entities.size() - 1)) {
            moveUser(currentLocation, nextLocation);
        }
        entities().forEach(Entity::relax);
    }

    private Optional<Location> findFirstEntity(Predicate<Entity> condition) {
        return range(0, entities.size())
                .boxed()
                .flatMap(top -> zip(top, findEntityIndex(entities.get(top), condition)))
                .findFirst();
    }

    private IntStream findEntityIndex(List<Entity> entities, Predicate<Entity> condition) {
        return range(0, entities.size()).filter(left -> condition.test(entities.get(left)));
    }

    private Stream<Location> zip(int y, IntStream leftCoordinates) {
        return leftCoordinates.mapToObj(left -> Location.of(left, y));
    }

    private boolean isValid(Location location, int maxCoordinate) {
        IntegerRange correctCoordinate = IntegerRange.of(0, maxCoordinate);
        return correctCoordinate.contains(location.getX())
                && correctCoordinate.contains(location.getY());
    }

    private void moveUser(Location currentLocation, Location nextLocation) {
        Entity containerEntity = entityOn(currentLocation);
        Entity newContainerEntity = entityOn(nextLocation);

        if (!newContainerEntity.canContainAnotherEntity()) {
            return;
        }

        if (newContainerEntity.containTasks(taskDetectionCondition)) {
            Entity userCharacter = containerEntity.findEntity(Entity::isUser);
            Entity taskEntity = newContainerEntity.findEntity(taskDetectionCondition);

            missionCompletionStrategy.complete(userCharacter, taskEntity);
        }

        containerEntity.getInnerEntity().ifPresent(userCharacter -> {
            containerEntity.clear();
            newContainerEntity.take(userCharacter);
        });

        if (!isUserAlive(newContainerEntity)) {
            newContainerEntity.clear();
        }
    }

    private Entity entityOn(Location location) {
        return entities.get(location.getY()).get(location.getX());
    }

    private boolean isUserAlive(Entity entity) {
        return entity.findEntity(Entity::isUser).isAlive();
    }

    private void saveGame(){
        saver.save(entities);
        this.saveGame = true;
    }

    public boolean isSaveGame(){
        return saveGame;
    }

    @Override
    public List<List<Entity>> getEntities() {
        return entities;
    }
}
