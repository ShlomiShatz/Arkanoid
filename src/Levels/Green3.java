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
 * The class represents the third level - Green 3.
 * @author Shlomo Shatz   */
public class Green3 implements LevelInformation {

    public static final int NUM_OF_BALLS = 2;
    public static final double BALL_SPEED = 5.0;
    public static final double BALL_ANGLE = 30.0;
    public static final int PADDLE_SPEED = 5;
    public static final int PADDLE_WIDTH = 100;
    public static final String LEVEL_NAME = "Green 3";
    public static final int BLOCK_THICK = 25;
    public static final int START_BLOCK_Y = 150;
    public static final int BLOCK_WIDTH = 50;
    public static final int NUM_OF_ROWS = 5;
    public static final int BUILDING_X = 72;
    public static final int BUILDING_Y = 460;
    public static final int NUM_OF_WINDOWS = 4;
    public static final int WINDOW_THICK = 10;
    public static final int PADDLE_UPPER_LEFT_X = 350;
    public static final int PADDLE_UPPER_LEFT_Y = 550;

    private LinkedList<Block> blocks;

    /**
     * The constructor of the level.
     */
    public Green3() {
        //Adds the blocks to the game
        this.blocks = new LinkedList<Block>();
        Color color = Color.DARK_GRAY;
        int blockY = START_BLOCK_Y;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = i + NUM_OF_ROWS; j < (NUM_OF_ROWS * 3); j++) {
                this.blocks.add(new Block(new Rectangle(new Point(j * BLOCK_WIDTH + BLOCK_THICK, blockY),
                        BLOCK_WIDTH, BLOCK_THICK, color)));
            }
            if (i == 0) {
                color = Color.RED;
            } else if (i == 1) {
                color = Color.YELLOW;
            } else if (i == 2) {
                color = Color.BLUE;
            } else if (i == 3) {
                color = Color.WHITE;
            }
            blockY += BLOCK_THICK;
        }
    }

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public LinkedList<Velocity> initialBallVelocities() {
        LinkedList<Velocity> velocities = new LinkedList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(BALL_ANGLE - 2 * BALL_ANGLE, BALL_SPEED));
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
                //Draw background
                d.setColor(new Color(40, 130, 20));
                d.fillRectangle((int) GameLevel.BORDER_THICK, BLOCK_THICK, d.getWidth(), d.getHeight());
                //Draw building antenna
                d.setColor(new Color(80, 75, 70));
                d.fillRectangle(110, 250, 10, 200);
                //Draw outer circle
                d.setColor(new Color(215, 170, 100));
                d.fillCircle(115, 250, 12);
                //Draw middle circle
                d.setColor(new Color(250, 80, 55));
                d.fillCircle(115, 250, 8);
                //Draw inner circle
                d.setColor(Color.WHITE);
                d.fillCircle(115, 250, 3);
                d.setColor(new Color(60, 60, 55));
                //Draw antenna base
                d.fillRectangle(100, 400, 30, 200);
                //Draw building
                d.setColor(new Color(45, 40, 40));
                d.fillRectangle(65, 450, 100, 200);
                //Draw windows
                d.setColor(Color.WHITE);
                for (int i = 0; i < NUM_OF_ROWS; i++) {
                    for (int j = 0; j < NUM_OF_WINDOWS; j++) {
                        d.fillRectangle(BUILDING_X + j * BLOCK_THICK, BUILDING_Y + i * 32,
                                WINDOW_THICK, BLOCK_THICK);
                    }
                }
            }

            @Override
            public void timePassed() {
            }
        };
        return background;
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