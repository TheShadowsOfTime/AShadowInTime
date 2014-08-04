package org.theshadowsoftime.ashadowintime.files;

import java.awt.*;

/**
 *
 */
public class Resource {

    static Image[] tileset;

    public Resource(){

    }

    public static Image getTilesetImage(int index) {
        return tileset[index];
    }

    public static Image[] getTileset() {
        return tileset;
    }
}
