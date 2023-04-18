package Geometry;

/**
 * The Line class represents a line connecting two Points(start & end).
 * @author Shlomo Shatz 316093202 */
public class Line {

    // A double value represents undefined slope
    public static final double VERTICAL = Double.MIN_VALUE;

    // A constant used to calculate halves
    public static final double HALF = 2.0;

    // The starting point of the line
    private Point start;

    // The ending point of the line
    private Point end;

    /**
     * The constructor using points function.
     *
     * <p>The method constructs a Line using two given points</p>
     *
     * @param start The starting point of the line
     * @param end   The end point of the line
     */
    public Line(Point start, Point end) {
        //Constructs the line using start and end points
        this.start = start;
        this.end = end;
    }

    /**
     * The constructor using coordinates function.
     *
     * <p>The method constructs a point using given coordinates of a start point and an end point</p>
     *
     * @param x1 The X value of the starting point
     * @param y1 The Y value of the starting point
     * @param x2 The X value of the ending point
     * @param y2 The Y value of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        //Constructs the line using points defined by the parameters
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length.
     *
     * <p>The method uses the distance function of Point to calculate the length of the line</p>
     *
     * @return The length of the line
     */
    public double length() {
        //Returns the length of the line using points distance function
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point.
     *
     * <p>The method calculates and returns the middle point of the line, using the middle point formula</p>
     *
     * @return The middle point of the line
     */
    public Point middle() {
        //Calculates the X and Y of the middle point
        double midX = (this.start.getX() + this.end.getX()) / HALF;
        double midY = (this.start.getY() + this.end.getY()) / HALF;
        //Returns the point calculated
        return new Point(midX, midY);
    }

    /**
     * Returns the start point.
     *
     * <p>The method returns the starting point of the line</p>
     *
     * @return The starting point
     */
    public Point start() {
        //Returns the start point of the line
        return this.start;
    }

    /**
     * Returns the end point.
     *
     * <p>The method returns the ending point of the line</p>
     *
     * @return The ending point
     */
    public Point end() {
        //Returns the ending point of the line
        return this.end;
    }

    /**
     * Checks for intersection of lines.
     *
     * <p>The method checks if a given line intersects with this line(equals and contained included)</p>
     *
     * @param other The line being compared to
     * @return True if intersects, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //Checks if the lines are the same (equal or inverted) or share start/end point
        if (this.equals(other) || (this.start.equals(other.end)) || (this.end.equals(other.start))
                || (this.start.equals(other.start)) || (this.end.equals(other.end))) {
            //If so, return true
            return true;
        } else {
            //Otherwise, calculates the slope of the two lines
            double slopeThis = this.slopeCalculate();
            double slopeOther = other.slopeCalculate();
            //Checks if the slope is defined properly(not vertical)
            if (slopeThis != VERTICAL && slopeOther != VERTICAL) {
                //If so, calculates the difference of the Y axis(the b value in the equation) from the two line
                double yInterceptThis = this.start.getY() - (slopeThis * this.start.getX());
                double yInterceptOther = other.start.getY() - (slopeOther * other.start.getX());
                //If the slopes are equal and at the same y difference, return true
                if (slopeThis == slopeOther) {
                    return (yInterceptOther == yInterceptThis);
                } else {
                    //If not, calculate the X value of the intersection point
                    double xIntersection = (yInterceptOther - yInterceptThis) / (slopeThis - slopeOther);
                    //Checks if the intersection is in the range of the two lines(and not beyond or before it)
                    return (xIntersection >= this.start.getX() && xIntersection <= this.end.getX()
                            || xIntersection <= this.start.getX() && xIntersection >= this.end.getX())
                            && (xIntersection >= other.start.getX() && xIntersection <= other.end.getX()
                            || xIntersection <= other.start.getX() && xIntersection >= other.end.getX());
                }
            } else if (slopeOther == VERTICAL && slopeThis != VERTICAL) {
                //If one of the slopes is vertical, checks if the other intersects with it
                return this.start.getX() <= other.start.getX() && this.end.getX() >= other.start.getX()
                        || this.start.getX() >= other.start.getX() && this.end.getX() <= other.start.getX();
            } else if (slopeOther != VERTICAL) {
                //If the other slope is vertical, checks if the other intersects with it
                return other.start.getX() <= this.start.getX() && other.end.getX() >= this.start.getX()
                        || other.start.getX() >= this.start.getX() && other.end.getX() <= this.start.getX();
            } else {
                //If both of the slopes are vertical, checks for containment
                return this.start.getX() == other.start.getX() && (this.start.getY() <= other.start.getY()
                        && this.end.getY() >= other.start.getY()) || (this.start.getY() >= other.start.getY()
                        && this.end.getY() <= other.start.getY()) || (this.start.getY() <= other.end.getY()
                        && this.end.getY() >= other.end.getY()) || (this.start.getY() >= other.end.getY()
                        && this.end.getY() <= other.end.getY());
            }
        }
    }

    /**
     * Returns intersection point.
     *
     * <p>The method calculates and returns the intersection point between this line and another</p>
     *
     * @param other The other line being compared with
     * @return The intersection point, null if it doesn't exist or there are more than one point(same or contained)
     */
    public Point intersectionWith(Line other) {
        //If the line is equal or does not intersect, return null
        if (this.equals(other) || !this.isIntersecting(other)) {
            return null;
        } else {
            //Calculates the slope of the lines
            double slopeThis = this.slopeCalculate();
            double slopeOther = other.slopeCalculate();
            //Checks if the lines are not vertical
            if (slopeThis != VERTICAL && slopeOther != VERTICAL) {
                //If so, calculates the difference of the Y axis(the b value in the equation) from the two line
                double yInterceptThis = this.start.getY() - (slopeThis * this.start.getX());
                double yInterceptOther = other.start.getY() - (slopeOther * other.start.getX());
                //Checks if the slopes are equal and returns null in this case(relevant cases have been checked)
                if (slopeThis == slopeOther) {
                    return null;
                } else {
                    //If not, calculates the X value of the intersection point
                    double xIntersection = (yInterceptOther - yInterceptThis) / (slopeThis - slopeOther);
                    //Checks if the X value is in range of the two lines
                    if ((xIntersection >= this.start.getX() && xIntersection <= this.end.getX()
                            || xIntersection <= this.start.getX() && xIntersection >= this.end.getX())
                            && (xIntersection >= other.start.getX() && xIntersection <= other.end.getX()
                            || xIntersection <= other.start.getX() && xIntersection >= other.end.getX())) {
                        //If so, sets the X and Y accordingly to the point defined earlier, and returns it
                        return new Point(xIntersection, slopeThis * xIntersection + yInterceptThis);
                    }
                }
                //Checks if one of the slopes is vertical, and checks for intersection point
            } else if (slopeOther == VERTICAL && slopeThis != VERTICAL) {
                if ((this.start.getX() <= other.start.getX() && this.end.getX() >= other.start.getX()
                        || this.start.getX() >= other.start.getX() && this.end.getX() <= other.start.getX())
                && (this.start.getY() <= other.start.getY() && this.end.getY() >= other.start.getY()
                        || this.start.getY() >= other.start.getY() && this.end.getY() <= other.end.getY())) {
                    //Calculates the X and Y of the intersection point and returns it
                    double yInterceptThis = this.start.getY() - (slopeThis * this.start.getX());
                    return new Point(other.start.getX(), slopeThis * other.start.getX() + yInterceptThis);
                }
                //Checks if the other line is the one that is vertical and checks for intersection point
            } else if (slopeOther != VERTICAL) {
                if (other.start.getX() <= this.start.getX() && other.end.getX() >= this.start.getX()
                        || other.start.getX() >= this.start.getX() && other.end.getX() <= this.start.getX()) {
                    //Calculates the X and Y of the intersection point and returns it
                    double yInterceptOther = other.start.getY() - (slopeOther * other.start.getX());
                    return new Point(this.start.getX(), slopeOther * this.start.getX() + yInterceptOther);
                }
            }
        }
        //If no point was returned so far, returns null
        return null;
    }

    /**
     * Checks line equality.
     *
     * <p>The method checks if the two lines are the same(equals or inverted)</p>
     *
     * @param other The line being compared with
     * @return True if equals, False otherwise
     */
    public boolean equals(Line other) {
        //Compares the start and end point of the two lines using the Point equal function
        return (this.start.equals(other.start()) && this.end.equals(other.end())
                || (this.start.equals(other.end()) && this.end.equals(other.start())));
    }

    /**
     * Calculate the slope.
     *
     * <p>The method calculates the slope of a line using slope formula</p>
     *
     * @return The slope if exists or a number representing vertical slope
     */
    public double slopeCalculate() {
        //Checks if the slope is well-defined(not vertical)
        if (this.start.getX() - this.end.getX() != 0) {
            //If so, calculates it and returns it
            return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        }
        //If not, return a number representing vertical slope
        return VERTICAL;
    }

    /**
     * The method returns the closest point to start that intersects with a given rectangle.
     *
     * @param rect The rectangle being checked for intersection points
     * @return The closest point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //If there are no intersection points, return null
        if (rect.intersectionPoints(this) == null) {
            return null;
        } else {
            //Iterates through the list of points, checking for the closest one
            Point min = rect.intersectionPoints(this).get(0);
            double minDist = this.start.distance(min);
            //Uses the distance method to determine the closest point
            for (Point iterator : rect.intersectionPoints(this)) {
                //If the point is closer than the one before, defines the min point by it
                if (this.start.distance(iterator) < minDist) {
                    min = iterator;
                    minDist = this.start.distance(iterator);
                }
            }
            //Returns the closest point
            return min;
        }
    }

    /**
     * The method checks if a given point is on the line.
     *
     * @param checked The point being checked
     * @return True if the point is on the line, false otherwise
     */
    public boolean isPointOnLine(Point checked) {
        //Using the distance between the points to determine if the point is on the line
        return (this.start.distance(checked) + this.end.distance(checked) == this.length());
    }
}
