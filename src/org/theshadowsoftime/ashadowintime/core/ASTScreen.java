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
public class ASTScreen extends JPanel {

    /*Responsible for logic relating to music*/
    Thread musicThread = new Thread(new RunnableOverlapLoop());

    ASTFrame frame;

    /*Controls painting*/
    private int state;

    /*If the game is running*/
    private boolean running;
    /*If the game is paused*/
    private boolean paused = false;

    /*Main Menu Buttons*/
    public static JButton newGame;
    public static JButton loadGame;
    public static JButton options;

    public ASTScreen (ASTFrame frame) {
        this.frame = frame;

        frame.addKeyListener(new InputListener());

        newGame = new JButton("New Game");
        newGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        state = 3;
                        updateLayout();
                    }
                }
        );
        add(newGame, BorderLayout.NORTH);

        loadGame = new JButton("Resume Game");
        loadGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        state = 1;
                        updateLayout();
                    }
                }
        );
        add(loadGame, BorderLayout.SOUTH);

        options = new JButton("Options");
        options.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    state = 2;
                    updateLayout();
                }
            }
        );
        add(options, BorderLayout.SOUTH);

        running = true;
        state = 0;
        musicThread.start();
    }

    public void updateLayout() {
        switch (state) {
            case 0:
                newGame.setVisible(true);
                loadGame.setVisible(true);
                options.setVisible(true);
                break;
            case 1:
                newGame.setVisible(false);
                loadGame.setVisible(false);
                options.setVisible(false);
                break;
            case 2:
                newGame.setVisible(false);
                loadGame.setVisible(false);
                options.setVisible(false);
                break;
            case 3:
                newGame.setVisible(false);
                loadGame.setVisible(false);
                options.setVisible(false);
                break;
            case 4:
                break;
            default:
                throw new IllegalStateException("State is not a valid value");
        }
        repaint();
        revalidate();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        if (state == 0) { //main menu

        } else if (state == 1) { //load save menu
            g.drawRect(0, 0, getWidth(), getHeight());
        } else if (state == 2) { //options screen

        } else if (state == 3) { //game play

        } else if (state == 4) { //pause menu

        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
