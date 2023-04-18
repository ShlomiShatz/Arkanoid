package Collidables;

import Games.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Listeners.HitListener;
import Listeners.HitNotifier;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The Block class represents a block containing a rectangle. The block is both a Collidable and a Sprite.
 * @author Shlomo Shatz 316093202 */
public class Block implements Collidable, Sprite, HitNotifier {


    //A number used in reversing velocities
    public static final int NEGATIVE = -1;

    //The hit listeners of the block
    private ArrayList<HitListener> hitListeners;

    //The rectangle of the block
    private Rectangle rectangle;

    /**
     * The constructor of the block.
     *
     * @param r The rectangle of the constructed block
     */
    public Block(Rectangle r) {
        this.rectangle = r;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Defines new velocity
        Velocity afterHit = new Velocity(currentVelocity);
        //If the collision point is on the sides of the block, reverse the velocity's dx
        if (this.rectangle.getLeftLine().isPointOnLine(collisionPoint)
            || this.rectangle.getRightLine().isPointOnLine(collisionPoint)) {
            afterHit.setDx(afterHit.getDx() * NEGATIVE);
        }
        //If the collision point is on the top or bottom of the block, reverse the velocity's dy
        if (this.rectangle.getUpperLine().isPointOnLine(collisionPoint)
        || this.rectangle.getLowerLine().isPointOnLine(collisionPoint)) {
            afterHit.setDy(afterHit.getDy() * NEGATIVE);
        }

        this.notifyHit(hitter);
        //Return the updated velocity
        return afterHit;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //Draw the insides of the block based on its color
        surface.setColor(this.getCollisionRectangle().getColor());
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        //Sets the color to black and draws the outer lines of the block
        surface.setColor(Color.GRAY);
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * Adds the block to a given game.
     *
     * @param g The game that the block is added to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes a block from a given game.
     * @param game The game that the block is removed from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies the block that a hit has occured.
     * @param hitter The ball that hits
     */
    private void notifyHit(Ball hitter) {
        //Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<>(this.hitListeners);
        //Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Sets the hit listeners list.
     */
    public void firstSetHitListeners() {
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}
