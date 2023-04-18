package Games;

import Levels.LevelInformation;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class represents the countdown in the start of every level.
 * @author Shlomo Shatz 316093202 */
public class CountdownAnimation implements Animation {

    public static final int FONT_X = 385;
    public static final int FONT_Y = 400;
    public static final int FONT_SIZE = 50;
    public static final double MILLI = 1000.0;

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private LevelInformation level;
    private boolean start;
    private boolean isStarting;
    private long startMillis;

    /**
     * The constructor of the class.
     * @param numOfSeconds The duration of the pause
     * @param countFrom The number to count from
     * @param gameScreen The screen of the game
     * @param level The current level
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, LevelInformation level) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.level = level;
        this.start = false;
        this.isStarting = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //Checks if this is the start of the level and checks the starting time
        if (this.isStarting) {
            this.startMillis = System.currentTimeMillis();
            this.isStarting = false;
        }
        //Draws the background and the game screen
        this.level.getBackground().drawOn(d);
        this.gameScreen.drawAllOn(d);
        //Calculates the time that is right now
        long currentMillisecond = System.currentTimeMillis();
        //Calculates the time needed between counts
        double betweenCounts = this.numOfSeconds / this.countFrom;
        //Calculates the current number needed to be shown(rounded down)
        int currentNumber = (int) (1 + this.countFrom - (currentMillisecond - this.startMillis)
                / (betweenCounts * MILLI));
        //Draws the needed text
        d.setColor(Color.MAGENTA);
        if (currentNumber != 0) {
            d.drawText(FONT_X, FONT_Y, Integer.toString(currentNumber), FONT_SIZE);
        } else {
            d.drawText(FONT_X - 20, FONT_Y, "GO!", FONT_SIZE);
        }
        //Continues the game
        if (currentMillisecond - this.startMillis > this.numOfSeconds * MILLI) {
            this.start = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.start;
    }
}