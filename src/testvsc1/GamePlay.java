package testvsc1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePlay extends JPanel implements ActionListener  {
    private boolean play = true;
    private int score = 0;
    private String playerName = "";

    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX = 120; //120
    private int ballposY = 350; //350
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;
    private GameInput gameInput;
    private GameScore gameScore;

    public GamePlay() {
        map = new MapGenerator(3, 7);
        //addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        gameInput = new GameInput(playerX);
        gameScore = new GameScore();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean playStatus = gameInput.handleKeyPressed(e);
                if (playStatus) {
                    play = true;
                    playerX = gameInput.getPlayerX();
                }
            }
        });
    }

// called using timer.start() in constructor of GamePlay
    public void paint(Graphics g) {

        //background color
        g.setColor(new Color(0XADD8E6));
        //g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        map.draw((Graphics2D)g);

        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 12);

        g.setColor(Color.RED);  // ball color
        g.fillOval(ballposX, ballposY, 20, 20);

        g.setColor(Color.black);
        g.setFont(new Font("MV Boli", Font.BOLD, 25));
        g.drawString("Score: " + score, 520, 30);


        if (totalBricks <= 0) { // if all bricks are destroyed then you win
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(new Color(0X351C75));
            g.setFont(new Font("MV Boli", Font.BOLD, 30));
            g.drawString("You Won", 260, 300);
            //updateScores(g,playerName, score);
            gameScore.updateScores(g, playerName, score);
        }

        if(ballposY > 570) { // if ball goes beyond player then you lose
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(new Color(0X351C75));
            g.setFont(new Font("MV Boli", Font.BOLD, 30));
            g.drawString("Game Over, Your score: " + score, 190, 300);
            //updateScores(g,playerName, score);
            gameScore.updateScores(g, playerName, score);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start(); // this calls paint method
        if(play) {
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }
            // A is a label to break out of nested for loop
            A: for(int i = 0; i < map.map.length; i++) {
                for(int j = 0; j <map.map[0].length; j++) {
                    if(map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        //Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }

                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if(ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if(ballposX > 670) {
                ballXdir = -ballXdir;
            }

            repaint();
        }
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

}
