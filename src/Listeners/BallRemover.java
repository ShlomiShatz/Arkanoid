package Listeners;

import Collidables.Block;
import Games.GameLevel;
import Sprites.Ball;

/**
 * A class representing a listener that removes a ball from the game.
 * @author Shlomo Shatz   */
public class BallRemover implements HitListener {

    //The game being played
    private GameLevel game;
    //The counter of the balls
    private Counter remainingBalls;

    /**
     * The constructor of the class.
     * @param game The game being played
     */
    public BallRemover(GameLevel game) {
        this.game = game;
        this.remainingBalls = new Counter(0);
    }

    /**
     * Sets the remaining balls counter.
     * @param c The counter that the remaining ball is converted to
     */
    public void setRemainingBalls(Counter c) {
        this.remainingBalls = c;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeHitListener(this);
        hitter.removeFromGame(this.game);
        this.game.getBallCounter().decrease(1);
    }
}
