package com.game.home.world.interaction.user;

import com.game.home.console.MenuWidget;
import com.game.home.world.utils.Location;

public class UserConsoleInput implements UserInput {

    private final MenuWidget<Selection> menu = new MenuWidget<>("Choose character movement:", Selection.values());

    @Override
    public Location getNextPosition(Location currentLocation) {
        menu.paint();
        Selection item = menu.selectItem();
        switch (item) {
            case UP:
                return Location.of(currentLocation.getX(), currentLocation.getY() - 1);
            case DOWN:
                return Location.of(currentLocation.getX(), currentLocation.getY() + 1);
            case RIGHT:
                return Location.of(currentLocation.getX() + 1, currentLocation.getY());
            case LEFT:
                return Location.of(currentLocation.getX() - 1, currentLocation.getY());
            case SAVE:
                return Location.of(-1, -1);
            default:
                throw new IllegalStateException("Unsupported menu item: " + item);
        }
    }

    private enum Selection {
        UP("Move up"),
        DOWN("Move down"),
        RIGHT("Move right"),
        LEFT("Move left"),
        SAVE("Save and Exit");

        private final String title;

        Selection(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
