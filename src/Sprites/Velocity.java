package Sprites;

import Geometry.Point;

/**
 * The Velocity class represents the velocity of a ball.
 * @author Shlomo Shatz   */
public class Velocity {
    //A number used to correct the angle of the ball's direction
    public static final int ANGLE_CORRECT = 90;

    //The ball's velocity sideways
    private double dx;
    //The ball's velocity vertically
    private double dy;

    /**
     * The basic velocity constructor.
     *
     * <p>The function constructs an instance using dx & dy parameters</p>
     *
     * @param dx The motion sideways
     * @param dy The motion vertically
     */
    public Velocity(double dx, double dy) {
        //Sets the dx & dy of the velocity by the parameters
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The velocity constructor based on other one.
     *
     * <p>The function constructs an instance using another velocity</p>
     *
     * @param other The velocity created by
     */
    public Velocity(Velocity other) {
        this.dx = other.getDx();
        this.dy = other.getDy();
    }

    /**
     * Applies speed to point.
     *
     * <p>The function adds speed to a given point using dx and dy</p>
     *
     * @param p The point being added speed to
     * @return The point after speed adding
     */
    public Point applyToPoint(Point p) {
        //Returns the new point with the dx and dy added
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Returns the dx.
     *
     * @return The dx of the velocity
     */
    public double getDx() {
        //Returns the dx of the velocity
        return this.dx;
    }

    /**
     * Returns the dy.
     *
     * @return The dy of the velocity
     */
    public double getDy() {
        //Returns the dy of the velocity
        return this.dy;
    }

    /**
     * Sets the dx of the velocity.
     *
     * @param dx The dx being set to the velocity
     */
    public void setDx(double dx) {
        //Sets the dx by the parameter
        this.dx = dx;
    }

    /**
     * Sets the dy of the velocity.
     *
     * @param dy The dy being set to the velocity
     */
    public void setDy(double dy) {
        //Sets the dy by the parameter
        this.dy = dy;
    }

    /**
     * Constructs velocity based on angle and speed.
     *
     * <p>The function constructs and returns velocity based on angle and speed instead of dx and dy</p>
     *
     * @param angle The angle being used as the direction of the movement
     * @param speed The speed of the ball
     * @return The constructed velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //Converts the angle and speed to dx and dy using sin and cos functions
        double dx = Math.cos(Math.toRadians(angle - ANGLE_CORRECT)) * speed;
        double dy = Math.sin(Math.toRadians(angle - ANGLE_CORRECT)) * speed;
        //Fixing the cos(90) bug
        if (angle == 0.0) {
            dx = 0.0;
        }
        //returns the velocity with the calculated dx and dy
        return new Velocity(dx, dy);
    }

    /**
     * Returns the speed.
     * @return The calculated speed
     */
    public double getSpeed() {
        return Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
    }
}
