package org.cis1200.twentyfortyeight;

import java.io.*;

public class Utils {
    private static final String fileName = "highscore.txt";

    // Reads the high score from a text file
    public static int readHighScore() {
        try {
            FileReader reader = new FileReader(fileName);
            int ch = reader.read();
            String scoreStr = "";

            while (ch != -1) {
                // Ignore any new line characters
                if (ch != '\n' && ch != '\r') {
                    scoreStr = scoreStr + (char) ch;
                }
                ch = reader.read();
            }

            reader.close();
            return Integer.parseInt(scoreStr);
        } catch (IOException e) {
            return 0;
        }// File doesn't exist or can't be read
    }

    // Writes the high score to a text file
    public static void writeHighScore(int score) {
        try {
            FileWriter writer = new FileWriter(fileName); // overwrite mode
            writer.write(Integer.toString(score));
            writer.close();
        } catch (IOException e) {
            // If writing fails, do nothing
        }
    }
}
