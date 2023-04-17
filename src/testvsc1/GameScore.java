package testvsc1;

import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GameScore {
    private DatabaseConnection dbConnection; // changed to final( in case problem occurs remove final)

    public GameScore() {
        dbConnection = new DatabaseConnection();
    }

    /*
    Usage of updateScores method to avoid repetition of code
    Try-catch block as well as database connectivity
    To store high scores in an ArrayList and display them
     */
    public void updateScores(Graphics g, String playerName, int score) {
        try (Connection con = dbConnection.getConnection()) {
            Statement stmt = con.createStatement();

            String sql = "INSERT INTO scores (name, score) VALUES ('" + playerName + "', " + score + ") ON DUPLICATE KEY UPDATE score = GREATEST(score," + score + ")";
            stmt.executeUpdate(sql);

            String sql1 = "SELECT name, score FROM scores ORDER BY score DESC LIMIT 10";
            ResultSet rs = stmt.executeQuery(sql1);

            ArrayList<String> highScores = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                int playerScore = rs.getInt("score");
                highScores.add(name + ": " + playerScore);
            }

            g.setFont(new Font("MV Boli", Font.BOLD, 20));
            g.drawString("High Scores", 300, 350);
            int y = 380;
            for (String highScore : highScores) {
                g.drawString(highScore, 300, y);
                y += 30;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}