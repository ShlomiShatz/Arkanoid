package Geometry;

/**
 * The Point class represents points contains x & y coordinates.
 *
 * @author Shlomo Shatz 316093202
 */
public class Point {

    //A small number being used in double comparison
    public static final double EPSILON = Math.pow(10, -10);
    //The variable represents the location of the point on the X axis
    private double x;
    //The variable represents the location of the point on the X axis
    private double y;

    /**
     * The basic constructor.
     *
     * <p>The basic function that constructs the Point using x & y</p>
     *
     * @param x The location on the X axis
     * @param y The location on the Y axis
     */
    public Point(double x, double y) {
        //Constructs the x and y of this point
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a point based on another point.
     *
     * @param other The point being set to
     */
    public Point(Point other) {
        //Constructs the x and y of this point
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     * Calculates the distance.
     *
     * <p>The function calculates the distance between this point and a given one using distance formula</p>
     *
     * @param other The second point that the distance is calculated between
     * @return The double value of the distance result
     */
    public double distance(Point other) {
        //Calculates and returns the distance between the points
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * Checks equality between points.
     *
     * <p>The function checks if a given point is the same as this one, using the X and Y values</p>
     *
     * @param other The other point being compared
     * @return True if equals, False otherwise
     */
    public boolean equals(Point other) {
        //Compares the X and Y values of the points to check for equality(in a double manner)
        return Math.abs(this.x - other.getX()) < EPSILON
                && Math.abs(this.y - other.getY()) < EPSILON;
    }

    /**
     * The X getter.
     *
     * <p>The function returns the X value of this point</p>
     *
     * @return The X value of the point
     */
    public double getX() {
        //Returns the X value of the point
        return this.x;
    }

    /**
     * The X setter.
     *
     * <p>The function sets the X value of this point</p>
     *
     * @param x The X value of the point being set
     */
    public void setX(double x) {
        //Sets the X value of the point
        this.x = x;
    }

    /**
     * The Y getter.
     *
     * <p>The function returns the Y value of this point</p>
     *
     * @return The Y value of the point
     */
    public double getY() {
        //Returns the Y value of the point
        return this.y;
    }

    /**
     * The Y setter.
     *
     * <p>The function sets the Y value of this point</p>
     *
     * @param y The X value of the point being set
     */
    public void setY(double y) {
        //Sets the Y value of the point
        this.y = y;
    }

    @Override
    public String toString() {
        return ("(" + Double.toString(this.x) + ", " + Double.toString(this.y) + ")");
    }
}
