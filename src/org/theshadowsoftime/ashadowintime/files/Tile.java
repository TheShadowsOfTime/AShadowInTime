package org.theshadowsoftime.ashadowintime.files;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 *
 */
public class Tile {

    int id;
    boolean mobSpawn;

    Image image;

    public Tile(int id, boolean mobSpawn) {
        this.id = id;
        this.mobSpawn = mobSpawn;

        image = Resource.getTilesetImage(id);
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public void draw(Graphics g, int x, int y, ImageObserver imageObserver) {
        g.drawImage(image, x, y, imageObserver);
    }
}
