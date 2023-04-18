package Games;

import biuoop.DrawSurface;

/**
 * The Animation interface being used with objects that are animations.
 * @author Shlomo Shatz 316093202 */
public interface Animation {

    /**
     * The animation activity every frame.
     * @param d The draw-surface of the current animation
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation needs to stop.
     * @return True if it needs to stop, false otherwise
     */
    boolean shouldStop();
}