package testvsc1;

import java.awt.event.KeyEvent;

public class GameInput {
    private int playerX;
    private static final int MOVE_DISTANCE = 20;

    public GameInput(int playerX) {
        this.playerX = playerX;
    }

    public boolean handleKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
            return true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
            return true;
        }
        return false;
    }

    private void moveRight() {
        playerX += MOVE_DISTANCE;
    }

    private void moveLeft() {
        playerX -= MOVE_DISTANCE;
    }

    public int getPlayerX() {
        return playerX;
    }
}