package testvsc1;

import java.awt.*;

public abstract class Map {
    public int[][] map;
    public int brickWidth;
    public int brickHeight;

    public Map(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }

        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    //Usage in MapGenerator and implemented in GamePlay
    public abstract void draw(Graphics2D g);

    // this sets the value of brick to 0 if it is hit by the ball. implemented in GamePlay
    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}