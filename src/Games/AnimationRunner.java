package Games;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The class that runs the animations.
 * @author Shlomo Shatz 316093202 */
public class AnimationRunner {

    //A number used to divide by thousand
    public static final int MILLI = 1000;

    //Frames per second
    public static final int FPS = 60;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * The constructor of the class.
     * @param gui The gui being used in the game
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = FPS;
        this.sleeper = new Sleeper();
    }

    /**
     * The method that runs the animation given.
     * @param animation The running animation
     */
    public void run(Animation animation) {
        //Calculates the milliseconds needed
        int millisecondsPerFrame = MILLI / this.framesPerSecond;
        //The animation loop
        while (!animation.shouldStop()) {
            //Times the animation
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            //Shows the changes
            gui.show(d);
            //Sleeps for the needed time
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}