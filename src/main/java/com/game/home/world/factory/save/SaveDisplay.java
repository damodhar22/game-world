package com.game.home.world.factory.save;

import com.game.home.console.AbstractDisplay;
import com.game.home.world.entities.Entity;
import com.game.home.world.factory.resume.marshaller.Marshaller;

import java.util.List;

public class SaveDisplay extends AbstractDisplay<SaveCanvas> implements Saver {
    private final Marshaller marshaller;

    public SaveDisplay(SaveCanvas view, Marshaller marshaller) {
        super(view);
        this.marshaller = marshaller;
    }

    @Override
    public void save(List<List<Entity>> entities) {
        display();
        String path = canvas.getPath();
        marshaller.marshall(entities, path);
    }
}
