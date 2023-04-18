package Levels;

import Collidables.Block;
import Games.GameLevel;
import Geometry.Rectangle;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import Geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The class represents the first level - Direct Hit.
 * @author Shlomo Shatz 316093202 */
public class DirectHit implements LevelInformation {

    public static final int NUM_OF_BALLS = 1;
    public static final int NUM_OF_BLOCKS = 1;
    public static final double STARTING_ANGLE = 0.00000000001;
    public static final double BALL_SPEED = 5.0;
    public static final int PADDLE_SPEED = 5;
    public static final int PADDLE_WIDTH = 100;
    public static final String LEVEL_NAME = "Direct Hit";
    public static final int CENTER_X = 400;
    public static final int CENTER_Y = 162;
    public static final int CIRCLE_RADIUS = 90;
    public static final int BLOCK_THICK = 30;
    public static final int PADDLE_UPPER_LEFT_X = 350;
    public static final int PADDLE_UPPER_LEFT_Y = 550;


    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        velocities.add(Velocity.fromAngleAndSpeed(STARTING_ANGLE, BALL_SPEED));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                //Background
                d.fillRectangle((int) GameLevel.BORDER_THICK, (int) GameLevel.BORDER_THICK,
                        d.getWidth(), d.getHeight());
                d.setColor(Color.BLUE);
                //Inner circle
                d.drawCircle(CENTER_X, CENTER_Y, CIRCLE_RADIUS - 30);
                //Middle circle
                d.drawCircle(CENTER_X, CENTER_Y, CIRCLE_RADIUS);
                //Outer circle
                d.drawCircle(CENTER_X, CENTER_Y, CIRCLE_RADIUS + 30);
                //Lower line
                d.drawLine(CENTER_X, CENTER_Y + 13, CENTER_X, CENTER_Y + 140);
                //Right line
                d.drawLine(CENTER_X, CENTER_Y, CENTER_X + 140, CENTER_Y);
                //Left line
                d.drawLine(CENTER_X, CENTER_Y, CENTER_X - 140, CENTER_Y);
                //Top line
                d.drawLine(CENTER_X, CENTER_Y - 13, CENTER_X, CENTER_Y - 140);
            }

            @Override
            public void timePassed() {
            }
        };
    }

    @Override
    public List<Block> blocks() {
        //Adds the blocks
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(CENTER_X - 15, CENTER_Y - 12),
                BLOCK_THICK, BLOCK_THICK, Color.RED)));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }

    @Override
    public int getPaddleStartX() {
        return PADDLE_UPPER_LEFT_X;
    }

    @Override
    public int getPaddleStartY() {
        return PADDLE_UPPER_LEFT_Y;
    }
}
