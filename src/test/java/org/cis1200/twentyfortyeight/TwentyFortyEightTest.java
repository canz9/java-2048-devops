package org.cis1200.twentyfortyeight;

import org.junit.Test;
import static org.junit.Assert.*;


public class TwentyFortyEightTest {

    @Test
    public void testGameStartsNotOverAndScoreNonNegative() {
        Game2048 game = new Game2048();

        assertFalse(game.isGameOver());

        assertTrue(game.getScore() >= 0);
    }

    @Test
    public void testMovesDoNotThrowAndKeepScoreValid() {
        Game2048 game = new Game2048();

        game.moveLeft();
        game.moveRight();
        game.moveUp();
        game.moveDown();

        assertTrue(game.getScore() >= 0);
    }

    @Test
    public void testResetKeepsGamePlayable() {
        Game2048 game = new Game2048();

        game.moveLeft();
        game.moveDown();

        game.reset();

        assertFalse(game.isGameOver());
        assertTrue(game.getScore() >= 0);
    }

    @Test
    public void testAddRandomTilesDoesNotCrash() {
        Game2048 game = new Game2048();

        game.addRandomTile();
        game.addRandomTile();
        game.addRandomTile();

        assertTrue(game.getScore() >= 0);
    }
}