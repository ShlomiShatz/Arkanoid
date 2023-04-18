package Sprites;

import Games.GameLevel;
import Listeners.Counter;
import biuoop.DrawSurface;

/**
 * A class for the score indicator at the top of the game.
 * @author Shlomo Shatz   */
public class ScoreIndicator implements Sprite {

    //The basic stats of the indicator
    public static final int SCORE_X = 360;
    public static final int SCORE_Y = 22;
    public static final int FONT_SIZE = 18;

    //The score counter
    private Counter scoreCounter;
    //The full text being shown
    private String scoreText;
    private String levelName;


    /**
     * A basic constructor for the class.
     * @param c The counter being set for the indicator
     * @param levelName The name of the level
     */
    public ScoreIndicator(Counter c, String levelName) {
        this.scoreCounter = c;
        this.scoreText = "Score: " + scoreCounter.getValue();
        this.levelName = "Level Name: " + levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(SCORE_X, SCORE_Y, scoreText, FONT_SIZE);
        d.drawText(SCORE_X + 200, SCORE_Y, levelName, FONT_SIZE);
    }

    @Override
    public void timePassed() {
        this.scoreText = "Score: " + scoreCounter.getValue();
    }

    /**
     * Adds the indicator to a given game.
     * @param g The game that the indicator is being added to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
