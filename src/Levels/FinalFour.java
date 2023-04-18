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

/**
 * The class represents the fourth level - Final Four.
 * @author Shlomo Shatz   */
public class FinalFour implements LevelInformation {

    public static final int NUM_OF_BALLS = 3;
    public static final double STARTING_ANGLE = 30.0;
    public static final double ANGLE_CORRECTION = 0.00001;
    public static final double BALL_SPEED = 5.0;
    public static final int PADDLE_SPEED = 5;
    public static final int PADDLE_WIDTH = 100;
    public static final String LEVEL_NAME = "Final Four";
    public static final int BLOCK_HEIGHT = 25;
    public static final int BLOCK_WIDTH = 50;
    public static final int STARTING_Y = 110;
    public static final int NUM_OF_ROWS = 7;
    public static final int NUM_OF_RAIN = 10;
    public static final int RAIN_ANGLE1 = 90;
    public static final int RAIN_ANGLE2 = 590;
    public static final int RAIN_ANGLE = 620;
    public static final int START_LEFT_RAIN = 105;
    public static final int LEFT_RAIN_Y = 400;
    public static final int START_RIGHT_RAIN = 605;
    public static final int RIGHT_RAIN_Y = 520;
    public static final int PADDLE_UPPER_LEFT_X = 350;
    public static final int PADDLE_UPPER_LEFT_Y = 550;

    private LinkedList<Block> blocks;

    /**
     * Basic constructor for the level.
     */
    public FinalFour() {
        //Adds the blocks to the game
        this.blocks = new LinkedList<>();
        int blockY = STARTING_Y;
        Color color = Color.DARK_GRAY;
        for (int i = 0; i < NUM_OF_ROWS; ++i) {
            for (int j = 0; j < NUM_OF_ROWS * 2 + 1; ++j) {
                this.blocks.add(new Block(new Rectangle(new Point(j * BLOCK_WIDTH + BLOCK_HEIGHT, blockY),
                        BLOCK_WIDTH, BLOCK_HEIGHT, color)));
            }
            if (i == 0) {
                color = Color.RED;
            } else if (i == 1) {
                color = Color.YELLOW;
            } else if (i == 2) {
                color = Color.GREEN;
            } else if (i == 3) {
                color = Color.WHITE;
            } else if (i == 4) {
                color = Color.PINK;
            } else if (i == 5) {
                color = Color.CYAN;
            }
            blockY += BLOCK_HEIGHT;
        }
    }

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public LinkedList<Velocity> initialBallVelocities() {
        final LinkedList<Velocity> velocities = new LinkedList<>();
        for (double i = STARTING_ANGLE; i >= -STARTING_ANGLE; i -= STARTING_ANGLE) {
            velocities.add(Velocity.fromAngleAndSpeed(i + ANGLE_CORRECTION, BALL_SPEED));
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
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                //Draws background
                d.setColor(new Color(20, 140, 210));
                d.fillRectangle((int) GameLevel.BORDER_THICK, BLOCK_HEIGHT, d.getWidth(), d.getHeight());
                //Draw left rain
                d.setColor(Color.WHITE);
                for (int i = 0; i < NUM_OF_RAIN; i++) {
                    d.drawLine(START_LEFT_RAIN + i * NUM_OF_RAIN, LEFT_RAIN_Y,
                            RAIN_ANGLE1 + i * NUM_OF_RAIN, RAIN_ANGLE);
                }
                //Draw left clouds
                d.setColor(new Color(200, 200, 200));
                d.fillCircle(100, 400, 20);
                d.fillCircle(120, 420, 30);
                d.setColor(new Color(180, 180, 180));
                d.fillCircle(140, 390, 30);
                d.setColor(new Color(160, 160, 160));
                d.fillCircle(160, 420, 25);
                d.fillCircle(180, 400, 30);
                //Draw right rain
                d.setColor(Color.WHITE);
                for (int i = 0; i < NUM_OF_RAIN; ++i) {
                    d.drawLine(START_RIGHT_RAIN + i * NUM_OF_RAIN, RIGHT_RAIN_Y,
                            RAIN_ANGLE2 + i * NUM_OF_RAIN, RAIN_ANGLE);
                }
                //Draw right clouds
                d.setColor(new Color(200, 200, 200));
                d.fillCircle(600, 500, 23);
                d.fillCircle(620, 540, 27);
                d.setColor(new Color(180, 180, 180));
                d.fillCircle(640, 510, 29);
                d.setColor(new Color(160, 160, 160));
                d.fillCircle(660, 530, 22);
                d.fillCircle(680, 520, 32);
            }

            @Override
            public void timePassed() {
            }
        };
    }

    @Override
    public LinkedList<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
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
