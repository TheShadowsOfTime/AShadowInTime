package com.cjs07.ashadowintime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

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
    public static JButton newGame;
    public static JButton loadGame;
    public static JButton options;

    /*New Game GUI Components*/
    public static JButton newGameBack;
    public static JButton newGameContinue;

    /*Load Game GUI Components*/

    /*Options GUI Components*/

    /*Resource tilesets*/
    public static Image[] tileset1 = new Image[900];
    public static Image[] tileset2 = new Image[900];
    public static Image[] tileset3 = new Image[900];
    public static Image[] tileset4 = new Image[900];
    public static Image[] tileset5 = new Image[900];
    public static Image[] tileset6 = new Image[900];

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
                        scene = 5;
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

        newGameBack = new JButton("Cancel");
        newGameBack.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    scene = 0;
                }
            }
        );
        add(newGameBack);
        newGameBack.setVisible(false);

        newGameContinue = new JButton("Start");
        newGameContinue.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    scene = 3;
                }
            }
        );
        add(newGameContinue);
        newGameContinue.setVisible(false);

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++){
                tileset1[x + (y * 30)] = new ImageIcon("res/tileset1.png").getImage();
                tileset1[x + (y * 30)] = createImage(new FilteredImageSource(tileset1[x + (y * 30)].getSource(),
                        new CropImageFilter(x * 32, y * 32, 32, 32)));
            }
        }
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++){
                tileset2[x + (y * 30)] = new ImageIcon("res/tileset2.png").getImage();
                tileset2[x + (y * 30)] = createImage(new FilteredImageSource(tileset2[x + (y * 30)].getSource(),
                        new CropImageFilter(x * 32, y * 32, 32, 32)));
            }
        }
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++){
                tileset3[x + (y * 30)] = new ImageIcon("res/tileset3.png").getImage();
                tileset3[x + (y * 30)] = createImage(new FilteredImageSource(tileset3[x + (y * 30)].getSource(),
                        new CropImageFilter(x * 32, y * 32, 32, 32)));
            }
        }
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++){
                tileset4[x + (y * 30)] = new ImageIcon("res/tileset4.png").getImage();
                tileset4[x + (y * 30)] = createImage(new FilteredImageSource(tileset4[x + (y * 30)].getSource(),
                        new CropImageFilter(x * 32, y * 32, 32, 32)));
            }
        }
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++){
                tileset5[x + (y * 30)] = new ImageIcon("res/tileset5.png").getImage();
                tileset5[x + (y * 30)] = createImage(new FilteredImageSource(tileset5[x + (y * 30)].getSource(),
                        new CropImageFilter(x * 32, y * 32, 32, 32)));
            }
        }
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++){
                tileset6[x + (y * 30)] = new ImageIcon("res/tileset6.png").getImage();
                tileset6[x + (y * 30)] = createImage(new FilteredImageSource(tileset6[x + (y * 30)].getSource(),
                        new CropImageFilter(x * 32, y * 32, 32, 32)));
            }
        }

        running = true;
        scene = 0;
        thread.start();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        if (scene == 0) { //main menu

        } else if (scene == 1) { //load save menu

        } else if (scene == 2) { //options screen

        } else if (scene == 3) { //game play

        } else if (scene == 4) { //pause menu

        } else if (scene == 5) { //confirm overwrite from new game screen
            g.setFont(new Font("Times New Roman", Font.PLAIN, 28));
            g.setColor(Color.RED);
            String message = "WARNING: Creating a new game will overwrite any existing save data";
            g.drawString(message, 5, 50);
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
                newGame.setVisible(true);
                loadGame.setVisible(true);
                options.setVisible(true);
                newGameBack.setVisible(false);
                newGameContinue.setVisible(false);
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

            } else if (scene == 5) {
                newGame.setVisible(false);
                loadGame.setVisible(false);
                options.setVisible(false);

                newGameBack.setVisible(true);
                newGameContinue.setVisible(true);
            }
        }
    }
}
