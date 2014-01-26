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

    /*Main Menu Buttons*/
    public static JButton play;
    public static JButton options;
    /*Load Save Menu Buttons*/
    public static JButton[] loadSaveMenuButtons = new JButton[3];
    /*Options Menu Buttons*/
    public static JButton[] optionMenuButtons = new JButton[5];
    /*Pause Menu Buttons*/

    public ASTScreen (ASTFrame frame) {
        this.frame = frame;

        //variable definitions
        play = new JButton("Play");
        options = new JButton("Options");

        frame.setBackground(Color.BLACK);

        running = true;
        scene = 0;

        play.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scene = 1;
                    }
                }
        );

        add(play);
        add(options);

        thread.start();
    }

    @Override
    public void paintComponent (Graphics g) {
        //g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
        if (scene == 0) { //main menu

        } else if (scene == 1) { //load save menu
            //g.setColor(Color.WHITE);
            //g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
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
            System.out.println(scene);
        }
    }
}
