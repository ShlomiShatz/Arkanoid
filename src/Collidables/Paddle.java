package Collidables;

import Games.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The Paddle class represents the paddle which is controlled by the player.
 * @author Shlomo Shatz   */
public class Paddle implements Sprite, Collidable {

    //The angles used while hitting the paddle in different spots
    public static final int FAR_LEFT_ANGLE = 300;
    public static final int LEFT_ANGLE = 330;
    public static final int RIGHT_ANGLE = 30;
    public static final int FAR_RIGHT_ANGLE = 60;

    //The block that is the paddle
    private Block block;

    //The keyboardSensor used to detect player presses
    private KeyboardSensor keyboard;

    //The gui used to activate the keyboardSensor
    private GUI gui;

    private int paddleMovement;

    /**
     * The constructor of the paddle using a block.
     *
     * @param block The block which the paddle is constructed from
     * @param paddleMovement The speed of the paddle
     */
    public Paddle(Block block, int paddleMovement) {
        this.block = block;
        this.paddleMovement = paddleMovement;
    }

    /**
     * A getter for the block of the paddle.
     * @return The paddle's block
     */
    public Block getBlock() {
        return this.block;
    }

    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * The method moves the block leftward.
     */
    public void moveLeft() {
        //Checks if the block is on the far left
        if (this.block.getCollisionRectangle().getUpperLeft().getX() > GameLevel.BORDER_THICK) {
            //If not - moves it left
            this.setNewSpot(new Point(this.block.getCollisionRectangle().getUpperLeft().getX() - this.paddleMovement,
                    this.block.getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * The method moves the block rightward.
     */
    public void moveRight() {
        //Checks if the block is on the far right
        if (this.block.getCollisionRectangle().getUpperLeft().getX() + this.block.getCollisionRectangle().getWidth()
                < GameLevel.WIDTH - GameLevel.BORDER_THICK) {
            //If not - moves it right
            this.setNewSpot(new Point(this.block.getCollisionRectangle().getUpperLeft().getX() + paddleMovement,
                    this.block.getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * The method sets a new upperLeft point value for the paddle.
     *
     * @param p The point being set to
     */
    public void setNewSpot(Point p) {
        this.block.getCollisionRectangle().setUpperLeft(p);
    }

    @Override
    public void timePassed() {
        //Checks if the player pressed the right key
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            //Calls the proper method
            this.moveRight();
        }
        //Checks if the player pressed the left key
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            //Calls the proper method
            this.moveLeft();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    /**
     * The method returns the rectangle of the paddle.
     *
     * @return The rectangle of the block of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Divides the width of the paddle to 5 regions
        double eachPart = this.block.getCollisionRectangle().getWidth() / 5;
        //If the ball hits the paddle in the far left, changes the direction of it accordingly
        if (collisionPoint.getX() >= this.block.getCollisionRectangle().getUpperLeft().getX()
            && collisionPoint.getX() < this.block.getCollisionRectangle().getUpperLeft().getX() + eachPart) {
            return Velocity.fromAngleAndSpeed(FAR_LEFT_ANGLE, currentVelocity.getSpeed());
        }
        //If the ball hits the paddle in the left of the middle, changes the direction of it accordingly
        if (collisionPoint.getX() >= this.block.getCollisionRectangle().getUpperLeft().getX() + eachPart
                && collisionPoint.getX() < this.block.getCollisionRectangle().getUpperLeft().getX() + 2 * eachPart) {
            return Velocity.fromAngleAndSpeed(LEFT_ANGLE, currentVelocity.getSpeed());
        }
        //If the ball hits the paddle in the right of the middle, changes the direction of it accordingly
        if (collisionPoint.getX() >= this.block.getCollisionRectangle().getUpperLeft().getX() + 3 * eachPart
            && collisionPoint.getX() < this.block.getCollisionRectangle().getUpperLeft().getX() + 4 * eachPart) {
            return Velocity.fromAngleAndSpeed(RIGHT_ANGLE, currentVelocity.getSpeed());
        }
        //If the ball hits the paddle in the far right, changes the direction of it accordingly
        if (collisionPoint.getX() >= this.block.getCollisionRectangle().getUpperLeft().getX() + 4 * eachPart
                && collisionPoint.getX() < this.block.getCollisionRectangle().getUpperLeft().getX() + 5 * eachPart) {
            return Velocity.fromAngleAndSpeed(FAR_RIGHT_ANGLE, currentVelocity.getSpeed());
        }
        //If the ball hits the paddle in the middle region, plays the same as a regular block
        return this.block.hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * Adds the paddle to a given game.
     *
     * @param g The game that the paddle is being added to
     */
    public void addToGame(GameLevel g) {
        //Adds the paddle as a collidable
        g.addCollidable(this);
        //Adds the paddle as a sprite
        g.addSprite(this);
        //Defines the GUI of the by the GUI of the game
        this.gui = g.getGui();
        //Sets the keyboardSensor to the game gui
        this.keyboard = gui.getKeyboardSensor();
    }
}