package com.cjs07.ashadowintime.files;

public class Level {

    int viewDistance = 19;

    int[][] map;

    public void findCenterOfMap (int gridX, int gridY) {
        for (int x = 0; x < viewDistance; x++) {
            for (int y = 0; y < viewDistance; y++) {
                double divisorX = gridX / 9.5;
                double divisorY = gridY / 9.5;
                if (map[x][y] / 2 == 9.5) {

                }
            }
        }
    }
}
