package com.cjs07.ashadowintime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CJ on 1/25/14.
 * Developed for the A Shadow in Time project.
 */
public class ASTScreen extends JPanel implements Runnable {

    public Thread thread = new Thread(this);

    public static volatile ASTFrame frame;

    /*Controls painting*/
    public static volatile int scene;

    /*If the game is running*/
    public static volatile boolean running;
    /*If the game is paused*/
    public static volatile boolean paused = false;


    public ASTScreen (ASTFrame frame) {
        this.frame = frame;

        frame.addKeyListener(new InputListener());
        frame.addMouseListener(new InputListener());
        frame.addMouseMotionListener(new InputListener());

        running = true;
        scene = 0;
        thread.start();
    }

    @Override
    public void paintComponent (Graphics g) {
        if (scene == 0) { //main menu

        } else if (scene == 1) { //load save menu

        } else if (scene == 2) { //options screen

        } else if (scene == 3) { //game play

        } else if (scene == 4) { //pause menu

        }
    }

    public void run () {
        while (running) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (paused) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
