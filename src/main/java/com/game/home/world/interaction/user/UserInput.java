package com.game.home.world.interaction.user;

import com.game.home.world.utils.Location;

public interface UserInput {
    Location getNextPosition(Location currentPosition);
}
