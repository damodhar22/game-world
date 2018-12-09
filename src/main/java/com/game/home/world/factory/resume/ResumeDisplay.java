package com.game.home.world.factory.resume;

import com.game.home.console.AbstractDisplay;
import com.game.home.world.entities.Entity;
import com.game.home.world.factory.resume.marshaller.Marshaller;

import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.notExists;

public class ResumeDisplay extends AbstractDisplay<ResumeCanvas> implements Resumer {
    private final Marshaller marshaller;

    public ResumeDisplay(ResumeCanvas canvas, Marshaller marshaller) {
        super(canvas);
        this.marshaller = marshaller;
    }

    @Override
    public List<List<Entity>> resume() {
        String path;
        do {
            display();
            path = canvas.getPath();
        } while (notExists(Paths.get(path)));
        return marshaller.unmarshall(path);
    }
}
