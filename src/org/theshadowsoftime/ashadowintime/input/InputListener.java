package org.theshadowsoftime.ashadowintime.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 */
public class InputListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
