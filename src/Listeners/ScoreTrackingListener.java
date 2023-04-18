package Listeners;

import Collidables.Block;
import Sprites.Ball;

/**
 * The class representing a listener for scores.
 * @author Shlomo Shatz   */
public class ScoreTrackingListener implements HitListener {

    //The score being added every time a block is hit
    public static final int SCORE_INCREASED = 5;

    //The counter for the current score
    private Counter currentScore;

    /**
     * The basic constructor for the class.
     * @param scoreCounter The counter being set to the class
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(SCORE_INCREASED);
    }
}