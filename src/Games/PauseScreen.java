package Games;

import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The class that represents the pause screen.
 * @author Shlomo Shatz 316093202 */
public class PauseScreen implements Animation {

    public static final int FONT_X = 320;
    public static final int FONT_SIZE = 32;

    private KeyboardSensor keyboard;
    private boolean running;
    private SpriteCollection background;

    /**
     * The constructor of the class.
     * @param k The keyboard of the game
     * @param b The background of the game
     */
    public PauseScreen(KeyboardSensor k, SpriteCollection b) {
        this.keyboard = k;
        this.background = b;
        this.running = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //Draws the background
        this.background.drawAllOn(d);
        //Draws the text
        d.setColor(Color.MAGENTA);
        d.drawText(FONT_X, d.getHeight() / 2, "PAUSED!", FONT_SIZE + 8);
        d.drawText(FONT_X - 100, d.getHeight() / 2 + 50, "press SPACE to continue", FONT_SIZE);
        //Checks if the key is pressed to continue the game
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.running = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.running;
    }
}