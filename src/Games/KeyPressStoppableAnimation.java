package Games;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class that deals with key-stoppable animations.
 * @author Shlomo Shatz   */
public class KeyPressStoppableAnimation implements Animation {

    private Boolean stop;
    private Boolean isAlreadyPressed;
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;

    /**
     * The constructor of the class.
     * @param sensor The keyboard of the game
     * @param key The key that is pressed to continue the animation
     * @param animation The animation being run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        //Debug the continue-while-pause bug described in the assignment
        if (this.sensor.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.sensor.isPressed(this.key)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
