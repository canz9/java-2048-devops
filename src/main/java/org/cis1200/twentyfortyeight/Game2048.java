package org.cis1200.twentyfortyeight;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game2048 {
    Tile[][] board;
    private int score;
    private final Random random;
    private boolean gameOver;
    private int highScore;

    public Game2048() {
        board = new Tile[4][4];
        random = new Random();
        reset();
    }

    public void reset() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Tile(0);
            }
        }
        score = 0;
        gameOver = false;
        addRandomTile();
        addRandomTile();
    }

    public void addRandomTile() {
        ArrayList<Point> empty = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() == 0) {
                    empty.add(new Point(i, j));
                }
            }
        }
        if (!empty.isEmpty()) {
            Point p = empty.get(random.nextInt(empty.size()));
            board[p.x][p.y].setValue(random.nextDouble() < 0.9 ? 2 : 4);
        }
    }

    private boolean compressAndMergeRow(Tile[] row) {
        boolean moved = false;
        int[] values = new int[4];
        int index = 0;

        // Compress non-zero tiles
        for (Tile tile : row) {
            if (tile.getValue() != 0) {
                values[index++] = tile.getValue();
            }
        }

        // Merge same values next to each other
        for (int i = 0; i < 3; i++) {
            if (values[i] != 0 && values[i] == values[i + 1]) {
                values[i] *= 2;
                score += values[i];
                values[i + 1] = 0;
                moved = true;
            }
        }

        // Compress again after merge
        int[] newValues = new int[4];
        index = 0;
        for (int value : values) {
            if (value != 0) {
                newValues[index++] = value;
            }
        }

        // Check for any value change and update row
        for (int i = 0; i < 4; i++) {
            if (row[i].getValue() != newValues[i]) {
                row[i].setValue(newValues[i]);
                moved = true;
            }
        }

        return moved;
    }

    public boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            Tile[] row = board[i];
            if (compressAndMergeRow(row)) {
                moved = true;
            }
        }
        if (moved) {
            addRandomTile();
            checkGameOver();
        }
        return moved;
    }

    public boolean moveRight() {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            Tile[] row = new Tile[4];
            for (int j = 0; j < 4; j++) {
                row[j] = board[i][3 - j];
            }
            if (compressAndMergeRow(row)) {
                for (int j = 0; j < 4; j++) {
                    board[i][3 - j] = row[j];
                }
                moved = true;
            }
        }
        if (moved) {
            addRandomTile();
            checkGameOver(); // <--- Add this line
        }
        return moved;
    }

    public boolean moveUp() {
        boolean moved = false;
        for (int j = 0; j < 4; j++) {
            Tile[] col = new Tile[4];
            for (int i = 0; i < 4; i++) {
                col[i] = board[i][j];
            }
            if (compressAndMergeRow(col)) {
                for (int i = 0; i < 4; i++) {
                    board[i][j] = col[i];
                }
                moved = true;
            }
        }
        if (moved) {
            addRandomTile();
            checkGameOver(); // <--- Add this line
        }
        return moved;
    }

    public boolean moveDown() {
        boolean moved = false;
        for (int j = 0; j < 4; j++) {
            Tile[] col = new Tile[4];
            for (int i = 0; i < 4; i++) {
                col[i] = board[3 - i][j];
            }
            if (compressAndMergeRow(col)) {
                for (int i = 0; i < 4; i++) {
                    board[3 - i][j] = col[i];
                }
                moved = true;
            }
        }
        if (moved) {
            addRandomTile();
            checkGameOver(); // <--- Add this line
        }
        return moved;
    }

    public void checkGameOver() {
        // Check if any tile is 2048
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() == 2048) {
                    gameOver = true;
                    return;
                }
            }
        }

        // Check for empty tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() == 0) {
                    return; // game not over
                }
            }
        }

        // Check for possible moves on board
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j < 3 && board[i][j].getValue() == board[i][j + 1].getValue()) {
                    return;
                }
                if (i < 3 && board[i][j].getValue() == board[i + 1][j].getValue()) {
                    return;
                }
            }
        }

        gameOver = true;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0xbbada0));
        g.fillRect(0, 0, 400, 400);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j].draw(g, j * 100, i * 100);
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}