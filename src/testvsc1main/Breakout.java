package testvsc1main;

//class from a different package testvsc1 is imported
import testvsc1.GamePlay;

import javax.swing.*;
import java.awt.*;


public class Breakout {

    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("C:/Users/HP/Desktop/java/Docs/javaimg.jpg");
        JLabel label = new JLabel(icon);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JLabel gameLabel = new JLabel("Brick Breaker Game");
        gameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameLabel.setForeground(Color.BLACK);
        gameLabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(gameLabel);
        JLabel nameLabel = new JLabel("Please enter your name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(nameLabel);
        panel.add(labelPanel, BorderLayout.NORTH);

        while (true) {
            JTextField nameField = new JTextField();
            nameField.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(nameField, BorderLayout.SOUTH);
            int result = JOptionPane.showConfirmDialog(null, panel, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String playerName = nameField.getText();
                if (!playerName.isEmpty()) {
                    GamePlay gamePlay = new GamePlay();
                    gamePlay.setPlayerName(playerName);
                    JFrame frame = new JFrame();
                    frame.setBounds(10, 10, 700, 600);
                    frame.setTitle("Brick Breaker");
                    frame.setResizable(false);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(gamePlay);
                    break; // Exit loop if a valid name was entered
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter your name to continue.");
                }
            } else {
                System.exit(0); // Exit program if Cancel button was clicked
            }
        }
    }
}
