package Collidables;

import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;

/**
 * The GameEnvironment class represents the collection of collidables in the game.
 * @author Shlomo Shatz   */
public class GameEnvironment {

    //The list of collidables
    private ArrayList<Collidable> environment;

    /**
     * The basic constructor which initializes the list.
     */
    public GameEnvironment() {
        this.environment = new ArrayList<>();
    }

    /**
     * The basic getter of the collection of collidables.
     *
     * @return The list of collidables
     */
    public ArrayList<Collidable> getEnvironment() {
        return this.environment;
    }


    /**
     * Adds a collidable to the collection.
     *
     * @param c The given collidable being added to the list
     */
    public void addCollidable(Collidable c) {
        environment.add(c);
    }


    /**
     * Calculates the closest collision of a moving object.
     *
     * <p>The method calculates the closest collision of a given moving object using its trajectory</p>
     * @param trajectory The trajectory of the given object
     * @return An instance of CollisionInfo containing the point of collision and the collided object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //Initializes two variables
        Collidable minCollided = null;
        Point minPoint = null;
        //Iterates through the list to find the first point of collision
        for (int i = 0; i < environment.size(); i++) {
            minPoint = trajectory.closestIntersectionToStartOfLine(environment.get(i).getCollisionRectangle());
            if (minPoint != null) {
                //Based on the first point of collision as reference, finds the closest collision point
                minCollided = environment.get(i);
                for (Collidable iterator : environment) {
                    //Using the closestIntersectionToStartOfLine method to find the closest collision
                    if (trajectory.closestIntersectionToStartOfLine(iterator.getCollisionRectangle()) != null) {
                        //Using distance method to check if it is closer than the one before
                        if (minPoint != null && trajectory.start().distance(trajectory.
                                closestIntersectionToStartOfLine(iterator.getCollisionRectangle()))
                                < trajectory.start().distance(minPoint)) {
                            minPoint = trajectory.closestIntersectionToStartOfLine(iterator.getCollisionRectangle());
                            minCollided = iterator;
                        }
                    }
                }
                //Exits the external loop
                break;
            }
        }
        //If no point was given, returns null
        if (minCollided == null) {
            return null;
        }
        //Otherwise, returns the point and the collided object as CollisionInfo
        return new CollisionInfo(minPoint, minCollided);
    }

    /**
     * Removes a collidable from the environment.
     * @param c The removed collidable
     */
    public void removeCollidable(Collidable c) {
        environment.remove(c);
    }
}
