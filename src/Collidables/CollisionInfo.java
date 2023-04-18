package Collidables;

import Geometry.Point;

/**
 * The CollisionInfo class holds the information about collision - the point of collision and the object of it.
 */
public class CollisionInfo {

    //The point of collision
    private Point collisionPoint;
    //The collided object
    private Collidable collisionObject;

    /**
     * A constructor by given point and Collidable.
     *
     * @param p The given point of collision
     * @param c The colliding object
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collisionObject = c;
    }

    /**
     * A constructor by another CollisionInfo.
     *
     * @param other The other instance of CollisionInfo being set to
     */
    public CollisionInfo(CollisionInfo other) {
        this.collisionPoint = other.collisionPoint;
        this.collisionObject = other.collisionObject;
    }

    /**
     * The method returns the collision point.
     *
     * @return The collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * The method returns the colliding object.
     *
     * @return The colliding object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
