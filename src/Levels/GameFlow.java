package Levels;

import Games.AnimationRunner;
import Games.GameLevel;
import Games.KeyPressStoppableAnimation;
import Games.EndScreen;
import Listeners.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.LinkedList;

/**
 * The class controlling the flow of the game.
 * @author Shlomo Shatz 316093202 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Boolean didWin;

    /**
     * The constructor of the class.
     * @param gui The GUI of the game
     * @param r The animation runner of the game
     * @param k The keyboard played with
     */
    public GameFlow(GUI gui, AnimationRunner r, KeyboardSensor k) {
        this.gui = gui;
        this.animationRunner = r;
        this.keyboardSensor = k;
        this.didWin = true;
    }

    /**
     * The method that runs the levels of the game.
     * @param levels The list of levels the level is run by
     */
    public void runLevels(LinkedList<LevelInformation> levels) {
        //Sets the counter
        Counter scoreCounter = new Counter(0);
        //Iterates through the levels
        for (LevelInformation level : levels) {
            GameLevel gameLevel = new GameLevel(this.gui, this.animationRunner, this.keyboardSensor, scoreCounter);
            gameLevel.initialize(level);
            gameLevel.run(level);
            //Checks if the player lost
            if (gameLevel.getBallCounter().getValue() == 0) {
                didWin = false;
                break;
            }
        }
        //After the game is finished, shows the end screen and closes the game
        KeyPressStoppableAnimation keypress = new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, new EndScreen(scoreCounter, this.keyboardSensor, didWin));
        this.animationRunner.run(keypress);
        gui.close();
    }
}
