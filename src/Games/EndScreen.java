package Games;

import Listeners.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The class represents the ending screen of the game.
 * @author Shlomo Shatz 316093202 */
public class EndScreen implements Animation {

    public static final String WIN_MSG = "You Win!";
    public static final String LOSE_MSG = "Game Over!";
    public static final int LOSE_MSG_X = 225;
    public static final int WIN_MSG_X = 270;
    public static final int MAIN_MSG_Y = 200;
    public static final int MAIN_MSG_FONT = 60;
    public static final int SCORE_MSG_X = 280;
    public static final int SCORE_MSG_Y = 400;
    public static final int SCORE_MSG_FONT = 25;
    public static final int PRESS_MSG_X = 240;
    public static final int PRESS_MSG_Y = 500;
    public static final int PRESS_MSG_FONT = 32;

    private Counter score;
    private KeyboardSensor keyboardSensor;
    private boolean didWin;
    private boolean stop;

    /**
     * The constructor of the class.
     * @param score The current score of the player
     * @param keyboardSensor The keyboard sensor of the game
     * @param didWin True if the player won, false otherwise
     */
    public EndScreen(Counter score, KeyboardSensor keyboardSensor, boolean didWin) {
        this.score = score;
        this.keyboardSensor = keyboardSensor;
        this.didWin = didWin;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //Draws the background
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //Checks if the player won, and shows the proper message
        if (this.didWin) {
            d.setColor(Color.GREEN);
            d.drawText(WIN_MSG_X, MAIN_MSG_Y, WIN_MSG, MAIN_MSG_FONT);
        } else {
            d.setColor(Color.RED);
            d.drawText(LOSE_MSG_X, MAIN_MSG_Y, LOSE_MSG, MAIN_MSG_FONT);
        }
        //Draws the score and instructions
        d.setColor(Color.BLACK);
        d.drawText(SCORE_MSG_X, SCORE_MSG_Y, "Your final score: " + this.score.getValue(), SCORE_MSG_FONT);
        d.drawText(PRESS_MSG_X, PRESS_MSG_Y, "Press SPACE to exit", PRESS_MSG_FONT);
        //Checks if the player pressed the key to continue
        if (this.keyboardSensor.isPressed("space")) {
            this.stop = true;
        }
    }
}
