package Levels;

import Collidables.Block;
import Sprites.Sprite;
import Sprites.Velocity;

import java.util.List;

/**
 * The interface representing the information of levels.
 * @author Shlomo Shatz   */
public interface LevelInformation {

    /**
     * Returns the number of balls in the game.
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Initiates the balls' velocities into a list.
     * @return The list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     * @return The speed of the paddle
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     * @return The width of the paddle
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     * @return The name of the level
     */
    String levelName();

    /**
     * Returns the background as a sprite.
     * @return The background of the game
     */
    Sprite getBackground();

    /**
     * Returns a list of the blocks needed to be hit in the game.
     * @return The list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks needed to be removed from the game in order to win.
     * @return The number of the blocks in the game
     */
    int numberOfBlocksToRemove();

    /**
     * Returns the x value of the top left point of the paddle.
     * @return The x value of the top left point of the paddle
     */
    int getPaddleStartX();

    /**
     * Returns the y value of the top left point of the paddle.
     * @return The y value of the top left point of the paddle
     */
    int getPaddleStartY();
}