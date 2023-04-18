package Listeners;

import Collidables.Block;
import Sprites.Ball;

/**
 * A class representing a void listener - a listener that does nothing.
 * @author Shlomo Shatz   */
public class VoidListener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //Does nothing, used for boarder blocks that doesn't change during the game
    }
}
