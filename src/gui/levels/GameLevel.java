package gui.levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.GameEnvironment;
import gui.*;
import gui.motion.Velocity;
import gui.shapes.Point;
import sprites.Ball;
import sprites.Collidable;
import sprites.SpriteCollection;
import sprites.Sprite;
import sprites.Block;
import sprites.Paddle;
import utilities.Counter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GameLevel implements Animation {
    private static final int FRAME_WIDTH = 1800;
    private static final int FRAME_HEIGHT = 900;

    public static final int BALL_SPEED = 10;
    private static final int BALL_ANGLE = 20;

    private static final Color DARK_BLUE = new Color(0, 0, 139);
    private static final Color MID_DARK_GRAY = new Color(104, 104, 104);


    private SpriteCollection sprites;
    private GameEnvironment environment;
    private utilities.Counter blocksCounter;
    private utilities.Counter ballsCounter;
    private utilities.Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private utilities.Timer timer;

    /**
     *
     */
    public GameLevel() {
        environment = new GameEnvironment(FRAME_WIDTH, FRAME_HEIGHT);
        sprites = new SpriteCollection();
        blocksCounter = new Counter();
        ballsCounter = new Counter();
        score = new Counter();
        score.setValue(0);
        runner = new AnimationRunner(60, "Ball Block Breaker", FRAME_WIDTH, FRAME_HEIGHT);
        keyboard = runner.getGUI().getKeyboardSensor();
        timer = new utilities.Timer(0, 0, 0);
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
     *
     */
    public void initialize() {
        Background background = new Background(FRAME_WIDTH, FRAME_HEIGHT, DARK_BLUE);
        background.addToGame(this);
        ballsCounter.setValue(1);
        BallRemover listener2 = new BallRemover(this, ballsCounter);
        BallAdder listener3 = new BallAdder(this, ballsCounter);
        ScoreTrackingListener listener4 = new ScoreTrackingListener(score);
        Block upperScreen = new Block(new Point(0, FRAME_HEIGHT * 0.04), (int) (FRAME_WIDTH * 0.999999), (int) (FRAME_HEIGHT * 0.05), "UpperScreen", 0,MID_DARK_GRAY, false); //upper screen block
        upperScreen.addToGame(this);
        Block lowerScreen = new Block(new Point(0, FRAME_HEIGHT * 0.995), FRAME_WIDTH, (int) (FRAME_HEIGHT * 0.005), "LowerScreen", 0, Color.BLACK, false); //lower screen block
        lowerScreen.addToGame(this);
        lowerScreen.addHitListener(listener2);
        Block leftScreen = new Block(new Point(0, FRAME_HEIGHT * 0.09), (int) (FRAME_WIDTH * 0.03), (int) (FRAME_HEIGHT * 0.91), "LeftScreen", 0, MID_DARK_GRAY, false); //left screen block
        leftScreen.addToGame(this);
        Block rightScreen = new Block(new Point(FRAME_WIDTH * 0.97, FRAME_HEIGHT * 0.09), (int) (FRAME_WIDTH * 0.02999999), (int) (FRAME_HEIGHT * 0.91), "RightScreen", 0, MID_DARK_GRAY, false); //right screen block
        rightScreen.addToGame(this);

        List<Block> list = new ArrayList<>();
        //add first row
        int blocksWidth = 100;
        int blocksHeight = 40;
        double xStart = FRAME_WIDTH * 0.97 - blocksWidth;
        double yStart = FRAME_HEIGHT * 0.30;
        for (int i = 0; i < 12; i++) {
//            if (i == 5) {
//                list.add(new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "first row", 5, Color.BLACK, true));
//                list.get(i).addHitListener(listener2);
//            } else {
                list.add(new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "first row", 5, Color.GRAY, true));
//            }
            xStart -= blocksWidth;
        }

        //add second row
        xStart = FRAME_WIDTH * 0.97 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + blocksHeight;
        for (int i = 0; i < 11; i++) {
            list.add(new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "second row", 5, Color.RED, true));
            xStart -= blocksWidth;
        }

        //add third row
        xStart = FRAME_WIDTH * 0.97 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + (2 * blocksHeight);
        for (int i = 0; i < 10; i++) {
            list.add(new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "third row", 5, Color.YELLOW, true));
            xStart -= blocksWidth;
        }

        //add fourth row
        xStart = FRAME_WIDTH * 0.97 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + (3 * blocksHeight);
        for (int i = 0; i < 9; i++) {
            list.add(new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "fourth row", 5, Color.BLUE, true));
            xStart -= blocksWidth;
        }

        //add fifth row
        xStart = FRAME_WIDTH * 0.97 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + (4 * blocksHeight);
        for (int i = 0; i < 8; i++) {
            list.add(new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "fifth row", 5, Color.PINK, true));
            xStart -= blocksWidth;
        }

        //add sixth row
        xStart = FRAME_WIDTH * 0.97 - blocksWidth;
        yStart = FRAME_HEIGHT * 0.30 + (5 * blocksHeight);
        for (int i = 0; i < 7; i++) {
//            if (i == 5) {
//                list.add(new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "sixth row", 5, Color.cyan, true));
//                list.get(list.size() - 1).addHitListener(listener3);
//            } else {
                list.add(new Block(new Point(xStart, yStart), blocksWidth, blocksHeight, "sixth row", 5, Color.GREEN, true));
//            }
                xStart -= blocksWidth;
//            }
        }

        blocksCounter.setValue(57);
        gui.BlockRemover listener = new gui.BlockRemover(this, blocksCounter);
        for (Block elem : list) {
            elem.addToGame(this);
            elem.addHitListener(listener);
            elem.addHitListener(listener4);
        }

        Ball ball1 = new Ball(new Point(FRAME_WIDTH / 5, FRAME_HEIGHT / 5), 10, Color.WHITE);
//        Ball ball2 = new Ball(new Point(FRAME_WIDTH / 4, FRAME_HEIGHT / 4), 10, Color.WHITE);
//        Ball ball3 = new Ball(new Point(150, 150), 10, Color.WHITE);
        ball1.addToGame(this);
//        ball2.addToGame(this);
//        ball3.addToGame(this);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED));
//        ball2.setVelocity(Velocity.fromAngleAndSpeed(BALL_ANGLE * 2, BALL_SPEED));
//        ball3.setVelocity(Velocity.fromAngleAndSpeed(BALL_ANGLE * 3, BALL_SPEED));
        ball1.setGameEnvironment(environment);
//        ball2.setGameEnvironment(environment);
//        ball3.setGameEnvironment(environment);

        Paddle paddle = new Paddle();
        paddle.setGUI(runner.getGUI());
        paddle.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score, new Point(0, 0), FRAME_WIDTH, (int) (FRAME_HEIGHT * 0.04));
        scoreIndicator.addToGame(this);

    }


    /**
     *
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(1, 3, this.sprites));
        timer.timerInit();
        this.runner.run(this);
    }

    private void createBalls() {

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
     * @param c
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * @param s
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("space")) {
            this.runner.run(new PauseScreen(this.keyboard, FRAME_WIDTH, FRAME_HEIGHT, this.sprites));
        }
        if (blocksCounter.getValue() == 0) {
            score.increase(100);
            timer.stopTimer();
            this.running = false;
        }
        if (ballsCounter.getValue() == 0) {
            timer.stopTimer();
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}