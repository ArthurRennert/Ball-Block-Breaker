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

import java.util.List;


/**
 *
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private utilities.Counter blocksCounter;
    private utilities.Counter ballsCounter;
    private utilities.Counter score;
    private AnimationRunner animationRunner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private utilities.Timer timer;
    private LevelInformation levelInformation;


    /**
     * @param levelInfo
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter score) {
        environment = new GameEnvironment(ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
        sprites = new SpriteCollection();
        blocksCounter = new Counter();
        ballsCounter = new Counter();
//        animationRunner = new AnimationRunner(60, "Ball Block Breaker", ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
//        keyboard = animationRunner.getGUI().getKeyboardSensor();
        timer = new utilities.Timer(0, 0, 0);
        levelInformation = levelInfo;
        keyboardSensor = ks;
        animationRunner = ar;
        this.score = score;
    }


    /**
     *
     */
    public void initialize() {
        BlockRemover blockRemoverListener = new BlockRemover(this, blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        BallRemover ballRemoverListener = new BallRemover(this, ballsCounter);
        levelInformation.getBackground().addToGame(this);
        for (Block elem : levelInformation.getBlocksList()) {
            elem.addToGame(this);
            elem.addHitListener(blockRemoverListener);
            elem.addHitListener(scoreTrackingListener);
        }

        for (Block elem : levelInformation.getFrameBlocksList()) {
            elem.addToGame(this);
        }

        for (Block elem : levelInformation.getPitBlocksList()) {
            elem.addToGame(this);
            elem.addHitListener(ballRemoverListener);
        }

        blocksCounter.setValue(levelInformation.numberOfBlocksToRemove());
        ballsCounter.setValue(levelInformation.numberOfBalls());

        List<Ball> ballList = levelInformation.getBallsList();
        List<Velocity> velocityList = levelInformation.getVelocityList();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            ballList.get(i).addToGame(this);
            ballList.get(i).setGameEnvironment(environment);
            ballList.get(i).setVelocity(velocityList.get(i));
        }

        Paddle paddle = new Paddle(levelInformation.paddleWidth(), levelInformation.paddleSpeed());
        paddle.setGUI(animationRunner.getGUI());
        paddle.addToGame(this);

//        BallAdder ballAdderListener = new BallAdder(this, ballsCounter);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score, new Point(0, 0), ScreenSettings.FRAME_WIDTH, (int) (ScreenSettings.FRAME_HEIGHT * 0.04));
        scoreIndicator.addToGame(this);
    }


    /**
     *
     */
    public void run() {
        this.running = true;
        this.animationRunner.run(new CountdownAnimation(1, 3, this.sprites));
        timer.timerInit();
//        KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(keyboardSensor, "space", this);
        this.animationRunner.run(this);
    }

    /**
     * @return
     */
    public SpriteCollection getSprites() {
        return this.sprites;
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
        if (this.keyboardSensor.isPressed("enter")) {
            this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space", new PauseScreen(this.keyboardSensor, this.sprites)));
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

    /**
     * @return
     */
    public int getNumOfBallsLeft() {
        return ballsCounter.getValue();
    }

    /**
     * @return
     */
    public int getNumOfBlocksLeft() {
        return blocksCounter.getValue();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}