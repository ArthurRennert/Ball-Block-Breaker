import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

public class Game {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;

    public static final int BALL_SPEED = 10;
    private static final int BALL_ANGLE = 20;

    private static GUI gui;

    private SpriteCollection sprites;
    private GameEnvironment environment;

    public Game() {
        environment = new GameEnvironment(FRAME_WIDTH, FRAME_HEIGHT);
        sprites = new SpriteCollection();
    }

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        gui = new GUI("Ball Block Breaker", FRAME_WIDTH, FRAME_HEIGHT);
        Block upperScreen = new Block(new Point(0, 0), FRAME_WIDTH, (int) (FRAME_HEIGHT * 0.05), "UpperScreen", 0, Color.GRAY, false); //upper screen block
        upperScreen.addToGame(this);
        Block lowerScreen = new Block(new Point(0, FRAME_HEIGHT * 0.95), FRAME_WIDTH, (int) (FRAME_HEIGHT * 0.05), "LowerScreen", 0, Color.GRAY, false); //lower screen block
        lowerScreen.addToGame(this);
        Block leftScreen = new Block(new Point(0, FRAME_HEIGHT * 0.05), (int) (FRAME_WIDTH * 0.05), (int) (FRAME_HEIGHT * 0.90), "LeftScreen", 0, Color.GRAY, false); //left screen block
        leftScreen.addToGame(this);
        Block rightScreen = new Block(new Point(FRAME_WIDTH * 0.95, FRAME_HEIGHT * 0.05), (int) (FRAME_WIDTH * 0.05), (int) (FRAME_HEIGHT * 0.90), "RightScreen", 0, Color.GRAY, false); //right screen block
        rightScreen.addToGame(this);
        Block b1 = new Block(new Point(150, 150), (int) (FRAME_WIDTH * 0.15), (int) (FRAME_HEIGHT * 0.05), "random", 5, Color.DARK_GRAY, true); //random block
        b1.addToGame(this);
        Block b2 = new Block(new Point(350, 350), (int) (FRAME_WIDTH * 0.20), (int) (FRAME_HEIGHT * 0.08), "random", 10, Color.CYAN, true); //random block
        b2.addToGame(this);
        Block b3 = new Block(new Point(550, 550), (int) (FRAME_WIDTH * 0.25), (int) (FRAME_HEIGHT * 0.11), "random", 15, Color.RED, true); //random block
        b3.addToGame(this);

        Ball ball = new Ball(new Point(120, 120), 10, Color.BLUE);
        ball.addToGame(this);
        ball.setVelocity(Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED));
        ball.setGameEnvironment(environment);

        Paddle paddle = new Paddle(gui);
        paddle.addToGame(this);


    }

    // Run the game -- start the animation loop.
    public void run() {
        //...
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}