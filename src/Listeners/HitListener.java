package Listeners;

import Collidables.Block;
import Sprites.Ball;

/**
 * The HitListener interface for all the listeners.
 * @author Shlomo Shatz   */
public interface HitListener {

    /**
     * The method activates when a hit takes place, and does something.
     * @param beingHit The block that got hit
     * @param hitter The ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
