package Geometry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents rectangle.
 * @author Shlomo Shatz 316093202 */
public class Rectangle {

    //The upper-left point of the rectangle, the one that the rest of the definitions relies on
    private Point upperLeft;
    //The width of the rectangle
    private double width;
    //The height of the rectangle
    private double height;
    //The color of the rectangle
    private Color color;

    /**
     * The rectangle constructor.
     *
     * <p>The method constructs a rectangle using a point of reference, scale parameters and color</p>
     *
     * @param upperLeft The point of the rectangle which the other parameters rely on
     * @param width The given width of the rectangle
     * @param height The given height of the rectangle
     * @param color The given color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        //Applies the given parameters to the rectangle
        this.upperLeft = new Point(upperLeft);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Setter for the upper-left point.
     *
     * @param p The point being set to
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * Checks for intersections with line.
     *
     * <p>The method is used to check for intersection points of the rectangle with a given line</p>
     *
     * @param line The line being checked
     * @return A list of the intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        //A boolean variable used to tell if a point has been added to the list
        boolean didAdd = false;
        //Creates a new list
        List<Point> pointList = new ArrayList<>();
        //Checks for intersection points with the upper line
        if (line.intersectionWith(this.getUpperLine()) != null) {
            //If there are, adds it and sets the boolean variable
            pointList.add(line.intersectionWith(this.getUpperLine()));
            didAdd = true;
        }
        //Checks for intersection points with the left line
        if (line.intersectionWith(this.getLeftLine()) != null) {
            //If there are, adds it and sets the boolean variable
            pointList.add(line.intersectionWith(this.getLeftLine()));
            didAdd = true;
        }
        //Checks for intersection points with the right line
        if (line.intersectionWith(this.getRightLine()) != null) {
            //If there are, adds it and sets the boolean variable
            pointList.add(line.intersectionWith(this.getRightLine()));
            didAdd = true;
        }
        //Checks for intersection points with the lower line
        if (line.intersectionWith(this.getLowerLine()) != null) {
            //If there are, adds it and sets the boolean variable
            pointList.add(line.intersectionWith(this.getLowerLine()));
            didAdd = true;
        }
        //If the boolean variable has changed, and points has been added, returns the list
        if (didAdd) {
            return pointList;
        }
        //If no points were added, returns null
        return null;
    }

    /**
     * Checks if a point is in the rectangle.
     *
     * <p>The method checks if a given point is within the borders of the rectangle</p>
     * @param p The point being checked
     * @return True if it is, False otherwise
     */
    public boolean isPointInRectangle(Point p) {
        //Checks if the point is in the range of the rectangle
        return (p.getX() > this.upperLeft.getX() && p.getX() < this.upperLeft.getX() + this.getWidth()
                && p.getY() > this.upperLeft.getY() && p.getY() < this.upperLeft.getY() + this.getHeight());
    }

    /**
     * A getter for the upper line.
     *
     * @return The upper line
     */
    public Line getUpperLine() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX() + width, this.upperLeft.getY()));
    }

    /**
     * A getter for the left line.
     *
     * @return The left line
     */
    public Line getLeftLine() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX(), this.upperLeft.getY() + height));
    }

    /**
     * A getter for the right line.
     *
     * @return The right line
     */
    public Line getRightLine() {
        return new Line(this.upperLeft.getX() + width, this.upperLeft.getY(),
                this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * A getter for the lower line.
     *
     * @return The lower line
     */
    public Line getLowerLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY() + height,
                this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * A getter for the width of the rectangle.
     *
     * @return The width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * A getter for the height of the rectangle.
     *
     * @return The height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * A getter for the upper-left point of the rectangle.
     *
     * @return The upper-left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * A getter for the color of the rectangle.
     *
     * @return The color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }
}
