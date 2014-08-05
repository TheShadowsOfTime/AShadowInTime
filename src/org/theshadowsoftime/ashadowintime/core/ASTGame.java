package org.theshadowsoftime.ashadowintime.core;

import org.theshadowsoftime.ashadowintime.files.Map;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class ASTGame extends JPanel {

    ASTFrame frame;

    Map currentMap;

    public ASTGame(ASTFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(currentMap.getMapImage(), getWidth()/25, getHeight()/25, this);
    }
}
