package Listeners;

import Collidables.Block;
import Games.GameLevel;
import Sprites.Ball;

/**
 * A class that removes blocks.
 * @author Shlomo Shatz   */
public class BlockRemover implements HitListener {

    //The game being played
    private GameLevel game;
    //The remaining blocks counter
    private Counter remainingBlocks;

    /**
     * Sets the remaining blocks.
     * @param c The counter that the block counter is converted to
     */
    public void setRemainingBlocks(Counter c) {
        this.remainingBlocks = c;
    }

    /**
     * A constructor for the class.
     * @param game The game being played
     */
    public BlockRemover(GameLevel game) {
        this.game = game;
        this.remainingBlocks = new Counter(0);
    }

    /**
     * A different constructor to the class.
     * @param game The game being played
     * @param removedBlocks The number of removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = game.getBlockCounter();
        this.remainingBlocks.decrease(removedBlocks.getValue());
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.game.getBlockCounter().decrease(1);
    }
}