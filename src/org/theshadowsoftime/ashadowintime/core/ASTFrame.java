package org.theshadowsoftime.ashadowintime.core;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CJ on 1/25/14.
 * Developed for the A Shadow in Time project.
 */
public class ASTFrame extends JFrame{

    /*The Title of the game. Appears on the game window*/
    public static final String TITLE = "A Shadow In Time";

    /*Game version. used for auto updating (if used)*/
    public static final String VERSION = "Indev 0.0.1";

    /*Dimension of the window*/
    public static volatile Dimension size = new Dimension(800, 600);

    public ASTFrame (String title) {
        super(title);
        setSize(size);
        setResizable(false);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("res/icon.png").getImage());
        setLayout(new FlowLayout());
        setVisible(true);

        add(new ASTScreen(this));
    }

    public static void main (String[] args) {
        ASTFrame frame = new ASTFrame(TITLE);
    }
}
