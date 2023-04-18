package Sprites;

import biuoop.DrawSurface;

/**
 * The Sprite interface being used in sprites.
 * @author Shlomo Shatz   */
public interface Sprite {

    /**
     * Draws the sprite.
     *
     * <p>The method is used to draw the sprite on a given surface</p>
     *
     * @param d The surface being drawn on
     */
    void drawOn(DrawSurface d);

    /**
     * Tells the sprites the time has passed.
     *
     * <p>The method is used to tell the sprite a time has passed and gets it to do something in this time</p>
     */
    void timePassed();
}