package Sprites;

import Collidables.Collidable;
import Collidables.CollisionInfo;
import Collidables.GameEnvironment;
import Games.GameLevel;
import Geometry.Line;
import Geometry.Point;
import Listeners.HitListener;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Ball class represents a moving ball, that is a Sprite.
 * @author Shlomo Shatz   */
public class Ball implements Sprite {

    //The center point of the ball
    private Point center;
    //The radius of the ball
    private int radius;
    //The color of the ball
    private Color color;
    //The velocity of the ball
    private Velocity velocity;
    //The game environment of the ball
    private GameEnvironment environment;
    //The hit listeners of the ball
    private ArrayList<HitListener> hitListeners;

    /**
     * Constructs the ball using coordinates.
     *
     * <p>The method constructs a ball using coordinates for the center, radius and color parameters</p>
     *
     * @param x The X value of the center point
     * @param y The Y value of the center point
     * @param r The radius of the ball
     * @param color The color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        //Defines the center point using the x & y parameters
        this.center = new Point(x, y);
        //Checks if the radius is not bigger than the ratios of the gui, if it is - uses the lower limit
        this.radius = Math.min(r, Math.min(GameLevel.HEIGHT, GameLevel.WIDTH));
        //Defines the color of the ball
        this.color = color;
        environment = new GameEnvironment();
    }

    /**
     * Returns the X value of the center point.
     *
     * @return The X value of the center point
     */
    public int getX() {
        //Returns the X value of the center point
        return (int) this.center.getX();
    }

    /**
     * Returns the Y value of the center point.
     *
     * @return The Y value of the center point
     */
    public int getY() {
        //Returns the Y value of the center point
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return The radius of the ball
     */
    public int getSize() {
        //Returns the radius of the ball.
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return The color of the ball
     */
    public Color getColor() {
        //Returns the color of the ball.
        return this.color;
    }

    /**
     * Sets the velocity to the ball using another velocity.
     *
     * @param v The velocity being set to the ball
     */
    public void setVelocity(Velocity v) {
        //Sets the velocity using the dx and dy values of the given velocity
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * Returns the ball's velocity.
     *
     * @return The ball's velocity
     */
    public Velocity getVelocity() {
        //Returns the velocity
        return this.velocity;
    }

    /**
     * Sets the environment of the ball.
     *
     * @param environment The given environment that the ball is being set to
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Moves the ball.
     *
     *<p>The function is used to move the ball one step in a direction and calculates where it should go next</p>
     */
    public void moveOneStep() {
        //If the velocity of the ball is null, return
        if (this.getVelocity() == null) {
            return;
        }
        //Apply the velocity to the ball
        this.center = this.getVelocity().applyToPoint(this.center);
        Point before = this.center;
        //Calculates the ball's trajectory
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.getVelocity().applyToPoint(this.center).getX(),
                this.getVelocity().applyToPoint(this.center).getY());
        //Calculate the next closest hit
        CollisionInfo nextHit = this.environment.getClosestCollision(trajectory);
        if (nextHit != null) {
            //If there is a collision happening, changes the balls velocity based on it
            this.velocity = nextHit.collisionObject().hit(this, nextHit.collisionPoint(), this.velocity);
            //Checks where the ball hit, and moves its center to look smooth in animation(avoiding entering the block)
            for (Collidable iterator : environment.getEnvironment()) {
                //For every collidable, checks each line and corrects the center of the ball accordingly
                if (iterator.getCollisionRectangle().getLeftLine().isPointOnLine(nextHit.collisionPoint())) {
                    this.center = new Point(nextHit.collisionPoint().getX() - this.radius, this.center.getY());
                }
                if (iterator.getCollisionRectangle().getRightLine().isPointOnLine(nextHit.collisionPoint())) {
                    this.center = new Point(nextHit.collisionPoint().getX() + this.radius, this.center.getY());
                }
                if (iterator.getCollisionRectangle().getUpperLine().isPointOnLine(nextHit.collisionPoint())) {
                    this.center = new Point(this.center.getX(), nextHit.collisionPoint().getY() - this.radius);
                }
                if (iterator.getCollisionRectangle().getLowerLine().isPointOnLine(nextHit.collisionPoint())) {
                    this.center = new Point(this.center.getX(), nextHit.collisionPoint().getY() + this.radius);
                }
            }
        }
        //Checks if the ball got inside a block, if so returns it to the point before the entrance
        for (Collidable iterator : environment.getEnvironment()) {
            if (iterator.getCollisionRectangle().isPointInRectangle(this.center)) {
                this.center = before;
            }
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //Sets the color of the drawn ball as needed
        surface.setColor(this.getColor());
        //Fills a circle in the point and radius needed
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
      this.moveOneStep();
    }

    /**
     * Adds the ball to a given game.
     *
     * @param g The game that the ball is added to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball from a given game.
     * @param g The game that the ball is removed from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Sets the Hit Listeners as a new list.
     */
    public void firstSetHitListeners() {
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Adds a hit listener to the ball.
     * @param hl The hit listener being added.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the ball.
     * @param hl The hit listener being removed.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}