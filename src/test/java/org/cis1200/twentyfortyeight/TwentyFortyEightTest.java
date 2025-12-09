package org.cis1200.twentyfortyeight;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Simple JUnit tests for Game2048 that check basic behavior
 * without modifying the internal board representation.
 */
public class TwentyFortyEightTest {

    @Test
    public void testGameStartsNotOverAndScoreNonNegative() {
        Game2048 game = new Game2048();

        // Game should not be over right after creation
        assertFalse(game.isGameOver());

        // Score should start at zero or some non-negative value
        assertTrue(game.getScore() >= 0);
    }

    @Test
    public void testMovesDoNotThrowAndKeepScoreValid() {
        Game2048 game = new Game2048();

        // These calls should not throw exceptions
        game.moveLeft();
        game.moveRight();
        game.moveUp();
        game.moveDown();

        // Score should still be a valid non-negative number
        assertTrue(game.getScore() >= 0);
    }

    @Test
    public void testResetKeepsGamePlayable() {
        Game2048 game = new Game2048();

        // Make some moves first
        game.moveLeft();
        game.moveDown();

        // Reset the game; this should not throw
        game.reset();

        // After reset, game should not be over and score non-negative
        assertFalse(game.isGameOver());
        assertTrue(game.getScore() >= 0);
    }

    @Test
    public void testAddRandomTilesDoesNotCrash() {
        Game2048 game = new Game2048();

        // Calling addRandomTile a few times should not crash
        game.addRandomTile();
        game.addRandomTile();
        game.addRandomTile();

        // Game should still be in a valid state
        assertTrue(game.getScore() >= 0);
    }
}