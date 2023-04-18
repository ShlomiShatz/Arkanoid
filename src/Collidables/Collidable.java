package Collidables;

import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Velocity;

/**
 * The Collidable interface being used with objects that collides.
 * @author Shlomo Shatz   */
public interface Collidable {

    /**
     * Returns the rectangle of the collided object.
     *
     * @return The rectangle of the collided object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that collided that a collision happened.
     *
     * <p>The method is used during collisions, given the collision point and the velocity
     * of the collided object, and returns the objects needed velocity after the hit</p>
     *
     * @param hitter The hitting ball
     * @param collisionPoint The point of collision
     * @param currentVelocity The current velocity of the collided object
     * @return The velocity of the collided object needed after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
