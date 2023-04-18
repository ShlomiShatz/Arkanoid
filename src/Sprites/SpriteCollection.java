package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The class represents a collection of sprites - the objects on the surface.
 * @author Shlomo Shatz   */
public class SpriteCollection {

    //The list used to hold the sprites in the collection
    private LinkedList<Sprite> sprites;

    /**
     * The basic SpriteCollection constructor.
     *
     * <p>The method initializes a new list, ready to be used</p>
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * Adding a sprite.
     *
     * <p>The method adds a sprite to the collection</p>
     *
     * @param s The sprite added
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Calls timePassed for all sprites.
     *
     * <p>The method calls the timePassed method for all the sprites in the collection</p>
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesList = new ArrayList<Sprite>(this.sprites);
        for (Sprite iterator : spritesList) {
            iterator.timePassed();
        }
    }

    /**
     * Calls drawOn for all sprites.
     *
     * <p>The method calls the drawOn method for all the sprites in the collection</p>
     *
     * @param d The surface being drawn on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite iterator : this.sprites) {
            iterator.drawOn(d);
        }
    }

    /**
     * Removes a sprite from the game.
     * @param s The sprite being removed
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}