package org.cis1200.twentyfortyeight;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class GameBoard2048 extends JPanel {

    private Game2048 game; // model for the game
    private JLabel status; // current status text
    private JLabel highScoreLabel;


    // Game constants
    public static final int BOARD_WIDTH = 300;
    public static final int BOARD_HEIGHT = 300;

    /**
     * Initializes the game board.
     */
    public GameBoard2048(JLabel statusInit, JLabel highScoreInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        game = new Game2048(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        highScoreLabel = highScoreInit;

        /*
         * Listens for key clicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                boolean moved = false;
                if (key == KeyEvent.VK_LEFT) {
                    moved = game.moveLeft();
                } else if (key == KeyEvent.VK_RIGHT) {
                    moved = game.moveRight();
                } else if (key == KeyEvent.VK_UP) {
                    moved = game.moveUp();
                } else if (key == KeyEvent.VK_DOWN) {
                    moved = game.moveDown();
                }
                if (moved) {
                    game.addRandomTile();
                    updateStatus();
                    repaint();
                }
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        game.reset();
        status.setText("Score: 0");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void showInstructions() {
        JOptionPane.showMessageDialog(this,
                "2048 Instructions:\n" +
                "Use arrow keys to slide tiles\n" +
                "up, down, left, and right to\n" +
                "keep combining matching tiles.\n" +
                "Reach 2048 to win.\n" +
                "Good Luck!:)",
                "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (game.isGameOver()) {
            status.setText("Game Over! Final Score: " + game.getScore());
        } else {
            status.setText("Score: " + game.getScore());
        }
        highScoreLabel.setText("High Score: " + game.getHighScore());
    }


    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // clears background
        game.draw(g); // draws grid & tiles
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}