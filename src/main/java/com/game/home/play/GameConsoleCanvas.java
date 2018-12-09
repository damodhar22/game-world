package com.game.home.play;

import com.game.home.console.AbstractConsoleCanvas;
import com.game.home.world.GameWorld;
import com.game.home.world.entities.Entity;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

public class GameConsoleCanvas extends AbstractConsoleCanvas implements GameCanvas {

    private static final char CELL_SEPARATOR = '|';

    private static final int MAP_SIZE_MARGIN = 2;

    @Override
    public void paint(GameWorld gameWorld) {
        String lineSeparator = prepareLineSeparator(gameWorld.getEntities().size());

        System.out.println("MAP");
        System.out.println(lineSeparator);
        gameWorld.getEntities().forEach(this::drawLine);
        System.out.println(lineSeparator);
    }

    private String prepareLineSeparator(int numberEntitiesInLine) {
        return generate(() -> "-").limit(numberEntitiesInLine + MAP_SIZE_MARGIN).collect(joining());
    }

    private void drawLine(List<Entity> entities) {
        System.out.print(CELL_SEPARATOR);
        entities.forEach(this::drawEntity);
        System.out.println(CELL_SEPARATOR);
    }

    private void drawEntity(Entity entity) {
        switch (entity.getType()) {
            case PATH:
                entity.getInnerEntity().ifPresent(this::drawEntity);
                if (!entity.containAnotherEntity()) {
                    System.out.print(' ');
                }
                break;
            case CHARACTER:
                if (entity.isUser()) {
                    System.out.print('U');
                    break;
                }
            default:
                System.out.print(entity.getName().charAt(0));
        }
    }

    @Override
    public void showWinnerMessage() {
        System.out.println("You won");
    }

    @Override
    public void showGameOverMessage() {
        System.out.println("Game Over");
    }

    @Override
    public void showGameSavedMessage() {
        System.out.println("Game Saved");
    }

    @Override
    public void paint() {
        throw new UnsupportedOperationException("This method is not supported");
    }
}
