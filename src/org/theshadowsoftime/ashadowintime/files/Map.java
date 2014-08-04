package org.theshadowsoftime.ashadowintime.files;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 */
public class Map {

    int[][] map;

    FileInputStream in;
    InputStreamReader reader;
    Scanner scanner;

    public Map(String name, int width, int height) throws FileNotFoundException {
        map = new int[width][height];
        in = new FileInputStream("res/" + name);
        reader = new InputStreamReader(in);
        scanner = new Scanner(reader);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = scanner.nextInt();
            }
        }
    }

    public Image createMapImage(){
        BufferedImage mapImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g = mapImage.getGraphics();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                g.drawImage(Resource.getTilesetImage(map[x][y]), x*10, y*10, null);
            }
        }
        return mapImage;
    }
}
