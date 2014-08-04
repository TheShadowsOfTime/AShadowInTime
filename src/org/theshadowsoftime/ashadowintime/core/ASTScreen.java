package org.theshadowsoftime.ashadowintime.core;

import org.theshadowsoftime.ashadowintime.input.InputListener;
import org.theshadowsoftime.ashadowintime.util.RunnableOverlapLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class ASTScreen extends JPanel implements Runnable {

    public Thread thread = new Thread(this);

    /*Responsible for logic relating to music*/
    public Thread musicThread = new Thread(new RunnableOverlapLoop());

    public static volatile ASTFrame frame;

    /*Controls painting*/
    public static volatile int scene;

    /*If the game is running*/
    public static volatile boolean running;
    /*If the game is paused*/
    public static volatile boolean paused = false;

    /*Main Menu Buttons*/
    public static JButton newGame;
    public static JButton loadGame;
    public static JButton options;

    public ASTScreen (ASTFrame frame) {
        this.frame = frame;

        frame.addKeyListener(new InputListener());
        frame.addMouseListener(new InputListener());
        frame.addMouseMotionListener(new InputListener());

        newGame = new JButton("New Game");
        newGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scene = 3;
                    }
                }
        );
        add(newGame, BorderLayout.NORTH);

        loadGame = new JButton("Resume Game");
        loadGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scene = 1;
                    }
                }
        );
        add(loadGame, BorderLayout.SOUTH);

        options = new JButton("Options");
        options.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    scene = 2;
                }
            }
        );
        add(options, BorderLayout.SOUTH);

        running = true;
        scene = 0;
        thread.start();
        musicThread.start();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        if (scene == 0) { //main menu

        } else if (scene == 1) { //load save menu
            g.drawRect(0, 0, getWidth(), getHeight());
        } else if (scene == 2) { //options screen

        } else if (scene == 3) { //game play

        } else if (scene == 4) { //pause menu

        }
    }

    public void run () {
        while (running) {
            repaint();
            revalidate();
            try {
                Thread.sleep(2);
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

            if (scene == 0) {

            } else if (scene == 1) {
                newGame.setVisible(false);
                loadGame.setVisible(false);
                options.setVisible(false);
            } else if (scene == 2) {
                newGame.setVisible(false);
                loadGame.setVisible(false);
                options.setVisible(false);
            } else if (scene == 3) {
                newGame.setVisible(false);
                loadGame.setVisible(false);
                options.setVisible(false);
            } else if (scene == 4) {

            }
        }
    }
}
