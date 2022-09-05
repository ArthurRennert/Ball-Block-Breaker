import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 *
 */
public class Game {
    private static final int FRAME_WIDTH = 1800;
    private static final int FRAME_HEIGHT = 900;

    public static final int BALL_SPEED = 10;
    private static final int BALL_ANGLE = 20;

    private static final Color DARK_BLUE = new Color(0, 0, 139);
    private static final Color MID_DARK_GRAY = new Color(104, 104, 104);

    private static GUI gui;

    private SpriteCollection sprites;
    private GameEnvironment environment;

    public Game() {
        environment = new GameEnvironment(FRAME_WIDTH, FRAME_HEIGHT);
        sprites = new SpriteCollection();
    }

    /**
     * @return the frame width of the game.
     */
    public static int getFrameWidth() {
        return FRAME_WIDTH;
    }

    /**
     * @return the frame height of the game.
     */
    public static int getFrameHeight() {
        return FRAME_HEIGHT;
    }

    /**
     * @param c - the Collidable to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * @param s - the Sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }


    /**
     *
     */
    public void initialize() {
        gui = new GUI("Ball Block Breaker", FRAME_WIDTH, FRAME_HEIGHT);
        Block upperScreen = new Block(new Point(0, 0), FRAME_WIDTH, (int) (FRAME_HEIGHT * 0.05), "UpperScreen", 0,MID_DARK_GRAY, false); //upper screen block
        upperScreen.addToGame(this);
        Block lowerScreen = new Block(new Point(0, FRAME_HEIGHT * 0.95), FRAME_WIDTH, (int) (FRAME_HEIGHT * 0.05), "LowerScreen", 0, MID_DARK_GRAY, false); //lower screen block
        lowerScreen.addToGame(this);
        Block leftScreen = new Block(new Point(0, FRAME_HEIGHT * 0.05), (int) (FRAME_WIDTH * 0.05), (int) (FRAME_HEIGHT * 0.90), "LeftScreen", 0, MID_DARK_GRAY, false); //left screen block
        leftScreen.addToGame(this);
        Block rightScreen = new Block(new Point(FRAME_WIDTH * 0.95, FRAME_HEIGHT * 0.05), (int) (FRAME_WIDTH * 0.05), (int) (FRAME_HEIGHT * 0.90), "RightScreen", 0, MID_DARK_GRAY, false); //right screen block
        rightScreen.addToGame(this);

        //add first row
        int blocksWidth = 100;
        int blocksHeight = 40;
        double xStart = FRAME_WIDTH * 0.95 - blocksWidth;
        double yStart = FRAME_HEIGHT * 0.30;
        for (int i = 0; i < 12; i++) {
            new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "first row", 5, Color.GRAY, true).addToGame(this);
            xStart -= blocksWidth;
        }

        //add second row
        xStart = FRAME_WIDTH * 0.95 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + blocksHeight;
        for (int i = 0; i < 11; i++) {
            new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "second row", 5, Color.RED, true).addToGame(this);
            xStart -= blocksWidth;
        }

        //add third row
        xStart = FRAME_WIDTH * 0.95 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + (2 * blocksHeight);
        for (int i = 0; i < 10; i++) {
            new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "third row", 5, Color.YELLOW, true).addToGame(this);
            xStart -= blocksWidth;
        }

        //add fourth row
        xStart = FRAME_WIDTH * 0.95 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + (3 * blocksHeight);
        for (int i = 0; i < 9; i++) {
            new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "fourth row", 5, Color.BLUE, true).addToGame(this);
            xStart -= blocksWidth;
        }

        //add fifth row
        xStart = FRAME_WIDTH * 0.95 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + (4 * blocksHeight);
        for (int i = 0; i < 8; i++) {
            new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "fifth row", 5, Color.PINK, true).addToGame(this);
            xStart -= blocksWidth;
        }

        //add sixth row
        xStart = FRAME_WIDTH * 0.95 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + (5 * blocksHeight);
        for (int i = 0; i < 7; i++) {
            new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "sixth row", 5, Color.GREEN, true).addToGame(this);
            xStart -= blocksWidth;
        }

//        Block b1 = new Block(new Point(150, 150), (int) (FRAME_WIDTH * 0.15), (int) (FRAME_HEIGHT * 0.05), "random", 5, Color.DARK_GRAY, true); //random block
//        b1.addToGame(this);
//        Block b2 = new Block(new Point(350, 350), (int) (FRAME_WIDTH * 0.20), (int) (FRAME_HEIGHT * 0.08), "random", 10, Color.CYAN, true); //random block
//        b2.addToGame(this);
//        Block b3 = new Block(new Point(550, 550), (int) (FRAME_WIDTH * 0.25), (int) (FRAME_HEIGHT * 0.11), "random", 15, Color.RED, true); //random block
//        b3.addToGame(this);

        Ball ball1 = new Ball(new Point(FRAME_WIDTH / 5, FRAME_HEIGHT / 5), 10, Color.WHITE);
        Ball ball2 = new Ball(new Point(FRAME_WIDTH / 5, FRAME_HEIGHT / 5), 10, Color.WHITE);
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED));
        ball2.setVelocity(Velocity.fromAngleAndSpeed(BALL_ANGLE * 2, BALL_SPEED / 2));
        ball1.setGameEnvironment(environment);
        ball2.setGameEnvironment(environment);

        Paddle paddle = new Paddle();
        paddle.setGUI(gui);
        paddle.addToGame(this);
    }


    /**
     *
     */
    public void run() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(DARK_BLUE);
            d.fillRectangle((int) (FRAME_WIDTH * 0.05), (int) (FRAME_HEIGHT * 0.05), (int) (FRAME_WIDTH * 0.90), (int) (FRAME_HEIGHT * 0.90));
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