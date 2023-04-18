package Games;

import Collidables.Block;
import Collidables.Collidable;
import Collidables.GameEnvironment;
import Collidables.Paddle;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.LevelInformation;
import Listeners.ScoreTrackingListener;
import Listeners.VoidListener;
import Listeners.Counter;
import Listeners.BlockRemover;
import Listeners.BallRemover;
import Listeners.HitListener;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import Sprites.Ball;
import Sprites.ScoreIndicator;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The GameLevel class represents the game itself, initializing the objects and keeps the game going.
 *
 * @author Shlomo Shatz  
 */
public class GameLevel implements Animation {
    //The drawn ball's radius
    public static final int DRAWN_RADIUS = 5;

    //The starting point's X value;
    public static final int STARTING_X = 400;

    //The starting point's Y value;
    public static final int STARTING_Y = 550 - 2 * DRAWN_RADIUS;

    //Numbers defining the ratio of the gui
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    //The thickness of the borders
    public static final double BORDER_THICK = 25.0;

    //The size of blocks being created
    public static final int BLOCK_HEIGHT = 20;

    //The score added to the winner
    public static final int FINISHER_SCORE = 100;

    //The collection of sprites
    private SpriteCollection sprites;

    //The environment of the game
    private GameEnvironment environment;

    //The GUI of the game
    private GUI gui;

    //The counter of blocks, balls and score
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;

    /**
     * The constructor of the class.
     * @param gui The gui of the game
     * @param runner The animation runner of the game
     * @param keyboard The keyboard being pressed
     * @param score The score counter of the user
     */
    public GameLevel(GUI gui, AnimationRunner runner, KeyboardSensor keyboard, Counter score) {
        this.gui = gui;
        this.runner = runner;
        this.keyboard = keyboard;
        this.score = score;
    }

    /**
     * Adds a collidable to the environment.
     *
     * @param c The collidable being added
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s The sprite being added
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Returns the GUI of the game.
     *
     * @return The GUI of the game
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Initializes the game and creating the objects in it.
     * @param currentLevel The current level
     */
    public void initialize(LevelInformation currentLevel) {
        //Initializes the sprite collection
        sprites = new SpriteCollection();
        //Initializes the environment
        environment = new GameEnvironment();
        //Creates the border blocks
        VoidListener vl = new VoidListener();
        sprites.addSprite(currentLevel.getBackground());
        createBorderBlocks(vl, currentLevel);
        //Creates the game blocks
        createGameBlocks(currentLevel);
        //Creates the paddle
        createPaddle(vl, currentLevel);
    }

    /**
     * Runs the game and keeps it going.
     * @param level The current level
     */
    public void run(LevelInformation level) {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, level)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Creates the balls with a random angle.
     * @param currentLevel The current level
     * @return The ball remover for the balls, used later in the program
     */
    public BallRemover createBalls(LevelInformation currentLevel) {
        //Initializes the counter for balls
        this.ballCounter = new Counter(currentLevel.numberOfBalls());
        //Creating a ball remover and updates its counter
        BallRemover deathListener = new BallRemover(this);
        deathListener.setRemainingBalls(this.ballCounter);
        //Initializes the random instance
        //Using a for loop to create balls based on the constant number
        for (int i = 0; i < currentLevel.numberOfBalls(); i++) {
            //Creates a ball at the starting point, at a specific radius and color
            Ball ball = new Ball(STARTING_X, STARTING_Y, DRAWN_RADIUS, Color.RED);
            ball.firstSetHitListeners();
            ball.addHitListener(deathListener);
            //Sets the velocity and environment of the ball
            ball.setVelocity(currentLevel.initialBallVelocities().get(i));
            ball.setEnvironment(environment);
            //Adds the ball to the game
            ball.addToGame(this);
        }
        return deathListener;
    }

    /**
     * Creates the paddle of the game.
     * @param hl The hit listener of the block
     * @param currentLevel The current level
     */
    public void createPaddle(HitListener hl, LevelInformation currentLevel) {
        //Creates the paddle
        Paddle paddle = new Paddle(new Block(new Rectangle(new Point(currentLevel.getPaddleStartX(),
                currentLevel.getPaddleStartY()), currentLevel.paddleWidth(), BLOCK_HEIGHT, Color.ORANGE)),
                currentLevel.paddleSpeed());
        //Adds the paddle to the game
        paddle.getBlock().firstSetHitListeners();
        paddle.getBlock().addHitListener(hl);
        paddle.addToGame(this);
    }

    /**
     * Creates the border blocks.
     * @param hl The hit listener of the blocks
     * @param currentLevel The current level
     */
    public void createBorderBlocks(HitListener hl, LevelInformation currentLevel) {
        //Initializes the balls' hit listener
        BallRemover deathListener = createBalls(currentLevel);
        //Creates a block for the score display and adds it to the game
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0), WIDTH, BORDER_THICK, Color.WHITE));
        scoreBlock.firstSetHitListeners();
        scoreBlock.addHitListener(hl);
        scoreBlock.addToGame(this);
        //Creates the top block border
        Block topBorder = new Block(new Rectangle(new Point(0, BORDER_THICK), WIDTH, BORDER_THICK, Color.GRAY));
        //Adds it to the game
        topBorder.firstSetHitListeners();
        topBorder.addHitListener(hl);
        topBorder.addToGame(this);
        //Creates the left block border
        Block leftBorder = new Block(new Rectangle(new Point(0, BORDER_THICK * 2),
                BORDER_THICK, HEIGHT - (BORDER_THICK * 2), Color.GRAY));
        //Adds it to the game
        leftBorder.firstSetHitListeners();
        leftBorder.addHitListener(hl);
        leftBorder.addToGame(this);
        //Creates the right block border
        Block rightBorder = new Block(new Rectangle(new Point(WIDTH - BORDER_THICK, BORDER_THICK * 2),
                BORDER_THICK, HEIGHT - BORDER_THICK * 2, Color.GRAY));
        //Adds it to the game
        rightBorder.firstSetHitListeners();
        rightBorder.addHitListener(hl);
        rightBorder.addToGame(this);
        //Creates the lower block border
        Block lowerBorder = new Block(new Rectangle(new Point(BORDER_THICK, HEIGHT - BORDER_THICK / 2),
                WIDTH - 2 * BORDER_THICK, BORDER_THICK / 2, Color.RED));
        //Adds it to the game
        lowerBorder.firstSetHitListeners();
        lowerBorder.addHitListener(deathListener);
        lowerBorder.addToGame(this);
    }

    /**
     * Creates the gaming blocks.
     * @param currentLevel The current level
     */
    public void createGameBlocks(LevelInformation currentLevel) {
        //Initializes a score tracker
        ScoreTrackingListener scoreListener = createScoreTracker(currentLevel);
        this.blockCounter = new Counter(currentLevel.numberOfBlocksToRemove());
        BlockRemover br = new BlockRemover(this);
        //Creates the blocks of the first line
        for (Block b : currentLevel.blocks()) {
            b.firstSetHitListeners();
            b.addHitListener(br);
            b.addHitListener(scoreListener);
            //Adds the row to the game
            b.addToGame(this);
        }
        //Updates the listener's counter
        br.setRemainingBlocks(this.blockCounter);
    }

    /**
     * Creates a score tracking listener.
     * @param currentLevel The current level
     * @return The created score tracker
     */
    public ScoreTrackingListener createScoreTracker(LevelInformation currentLevel) {
        //Creates the tracker and connects it to the game score
        ScoreTrackingListener scl = new ScoreTrackingListener(this.score);
        ScoreIndicator scr = new ScoreIndicator(this.score, currentLevel.levelName());
        //Adds it to the game and returns it
        scr.addToGame(this);
        return scl;
    }

    /**
     * Removes a collidable from the game.
     * @param c The removed collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     * @param s The removed sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * A getter for the block counter.
     * @return The block counter
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * A getter for the ball counter.
     * @return The ball counter
     */
    public Counter getBallCounter() {
        return this.ballCounter;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //Draws all the sprites
        this.sprites.drawAllOn(d);
        //Checks if pause is needed
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation keypress = new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard, this.sprites));
            runner.run(keypress);
        }
        //Checks if there are no more balls, and the user lost
        if (this.ballCounter.getValue() <= 0) {
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            this.running = false;
        }
        //Checks if there are no more blocks
        if (this.blockCounter.getValue() <= 0) {
            //Raises the score a final amount
            score.increase(FINISHER_SCORE);
            //Updates the scoreboard to show the final score
            this.sprites.notifyAllTimePassed();
            this.sprites.drawAllOn(d);
            this.running = false;
        }
        //Tells all the sprites the time has passed
        this.sprites.notifyAllTimePassed();
    }
}