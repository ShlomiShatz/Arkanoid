import Games.AnimationRunner;

import Levels.GameFlow;
import Levels.WideEasy;
import Levels.DirectHit;
import Levels.Green3;
import Levels.FinalFour;
import Levels.LevelInformation;
import biuoop.GUI;

import java.util.LinkedList;

/**
 * The Ass6Game class is the one that starts the game.
 * @author Shlomo Shatz   */
public class Ass6Game {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    /**
     * The main method that creates the game, initializes it and runs it.
     *
     * @param args The arguments received in the command line
     */
    public static void main(String[] args) {
        //Sets the title of the GUI
        String gameTitle = "Arkanoid";
        GUI gui = new GUI(gameTitle, WIDTH, HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(gui, runner, gui.getKeyboardSensor());
        //Creates the level list based on the arguments of the command line
        LinkedList<LevelInformation> levels = new LinkedList<>();
        for (String arg : args) {
            if (arg.equals("1")) {
                levels.add(new DirectHit());
            } else if (arg.equals("2")) {
                levels.add(new WideEasy());
            } else if (arg.equals("3")) {
                levels.add(new Green3());
            } else if (arg.equals("4")) {
                levels.add(new FinalFour());
            }
        }
        //If no level is added, adds the four levels one by one
        if (levels.size() == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        //Runs the game
        gameFlow.runLevels(levels);
    }
}
