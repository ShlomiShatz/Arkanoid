package Levels;

import Collidables.Block;
import Games.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class represents the second level - Wide Easy.
 * @author Shlomo Shatz   */
public class WideEasy implements LevelInformation {

    public static final int NUM_OF_BALLS = 10;
    public static final int NUM_OF_BLOCKS = 15;
    public static final double BALL_SPEED = 5.0;
    public static final double STARTING_ANGLE = 50.0;
    public static final int PADDLE_SPEED = 2;
    public static final int PADDLE_WIDTH = 600;
    public static final String LEVEL_NAME = "Wide Easy";
    public static final int BLOCK_THICK = 30;
    public static final int CIRCLE_CENTER = 150;
    public static final int NUM_OF_SUNRAYS = 200;
    public static final int BLOCK_Y = 230;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BLOCK_WIDTH = 50;
    public static final int PADDLE_UPPER_LEFT_X = 100;
    public static final int PADDLE_UPPER_LEFT_Y = 550;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<Velocity>();
        for (double i = STARTING_ANGLE; i >= -STARTING_ANGLE; i -= 10.0) {
            if (i == 0.0) {
                continue;
            }
            velocities.add(Velocity.fromAngleAndSpeed(i, BALL_SPEED));
        }
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
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                //Draws the background
                d.setColor(Color.WHITE);
                d.fillRectangle((int) GameLevel.BORDER_THICK, BLOCK_THICK, d.getWidth(), d.getHeight());
                //Draws the sun and rays
                d.setColor(new Color(240, 230, 175));
                d.fillCircle(CIRCLE_CENTER, CIRCLE_CENTER, 60);
                for (int i = 1; i <= NUM_OF_SUNRAYS; ++i) {
                    d.drawLine(CIRCLE_CENTER, CIRCLE_CENTER, 4 * i, BLOCK_Y);
                }
                d.setColor(new Color(235, 210, 70));
                d.fillCircle(CIRCLE_CENTER, CIRCLE_CENTER, 50);
                d.setColor(new Color(255, 255, 20));
                d.fillCircle(CIRCLE_CENTER, CIRCLE_CENTER, 40);
            }

            @Override
            public void timePassed() {
            }
        };
        return background;
    }

    @Override
    public List<Block> blocks() {
        //Adds the blocks
        List<Block> blocks = new LinkedList<Block>();
        Color color = Color.RED;
        for (int i = BLOCK_HEIGHT; i <= 730; i += BLOCK_WIDTH) {
            blocks.add(new Block(new Rectangle(new Point(i, BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT, color)));
            if (i == 75) {
                color = Color.ORANGE;
            } else if (i == 175) {
                color = Color.YELLOW;
            } else if (i == 275) {
                color = Color.GREEN;
            } else if (i == 425) {
                color = Color.BLUE;
            } else if (i == 525) {
                color = Color.PINK;
            } else if (i == 625) {
                color = Color.CYAN;
            }
        }
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
