package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.GameEnvironment;
import collision.listeners.hit_listeners.BallRemover;
import collision.listeners.hit_listeners.BlockRemover;
import collision.listeners.hit_listeners.ScoreTracking;
import gui.*;
import gui.animations.infrastructure.Animation;
import gui.animations.infrastructure.AnimationRunner;
import gui.levels.infrastructure.LevelInformation;
import gui.motion.Velocity;
import gui.shapes.Point;
import sprites.*;
import collision.Collidable;
import sprites.infrastructure.Sprite;
import sprites.infrastructure.SpriteCollection;
import utilities.Counter;
import utilities.Timer;

import java.util.List;


/**
 *
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private Counter lives;
    private AnimationRunner animationRunner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private Timer timer;
    private LevelInformation levelInformation;


    /**
     * @param levelInfo
     * @param ks
     * @param ar
     * @param score
     * @param lives
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter score, Counter lives) {
        environment = new GameEnvironment(ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
        sprites = new SpriteCollection();
        blocksCounter = new Counter();
        ballsCounter = new Counter();
//        animationRunner = new AnimationRunner(60, "Ball Block Breaker", ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
//        keyboard = animationRunner.getGUI().getKeyboardSensor();
        timer = new Timer(0, 0, 0);
        levelInformation = levelInfo;
        keyboardSensor = ks;
        animationRunner = ar;
        this.score = score;
        this.lives = lives;
    }


    /**
     *
     */
    public void initialize() {
        BlockRemover blockRemoverListener = new BlockRemover(this, blocksCounter);
        ScoreTracking scoreTrackingListener = new ScoreTracking(score);
        BallRemover ballRemoverListener = new BallRemover(this, ballsCounter, lives);
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

        levelInformation.getPaddle().setGUI(animationRunner.getGUI());
        levelInformation.getPaddle().addToGame(this);

//        BallAdder ballAdderListener = new BallAdder(this, ballsCounter);

        GameInformation gameInformation = new GameInformation(score, lives, levelInformation.getName(), new Point(0, 0), ScreenSettings.FRAME_WIDTH, (int) (ScreenSettings.FRAME_HEIGHT * 0.04));
        gameInformation.addToGame(this);
//                ScoreIndicator scoreIndicator = new ScoreIndicator(score, new Point(0, 0), ScreenSettings.FRAME_WIDTH, (int) (ScreenSettings.FRAME_HEIGHT * 0.04));
//        scoreIndicator.addToGame(this);
    }


    /**
     *
     */
    public void run() {
        this.running = true;
        this.animationRunner.run(new Countdown(3, this.sprites, animationRunner, keyboardSensor));
        timer.timerInit();
//        KeyPressStoppable keyPressStoppableAnimation = new KeyPressStoppable(keyboardSensor, "space", this);
        this.animationRunner.run(this);
    }

    /**
     * @return
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }


    /**
     *
     */
    public void restart() {
        if (lives.getValue() == 0) {
            return;
        }
        levelInformation.getPaddle().setLocation(levelInformation.paddleInitialPoint());
        levelInformation.resetBallsLocation();
        ballsCounter.setValue(levelInformation.numberOfBalls());
        List<Ball> ballList = levelInformation.getBallsList();
        List<Velocity> velocityList = levelInformation.getVelocityList();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            ballList.get(i).addToGame(this);
            ballList.get(i).setGameEnvironment(environment);
            ballList.get(i).setVelocity(velocityList.get(i));
        }
        this.animationRunner.run(new Countdown(2, this.sprites, animationRunner, keyboardSensor));
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
        System.out.println("before remove: " + sprites.getSize());
        sprites.removeSprite(s);
        System.out.println("after remove: " + sprites.getSize());
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboardSensor.isPressed("enter")) {
            this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new PauseScreen(this.keyboardSensor, this.sprites)));
        }
        if (blocksCounter.getValue() == 0) {
            score.increase(100);
            timer.stopTimer();
            this.running = false;
        }
        if (lives.getValue() == 0) {
            timer.stopTimer();
            this.running = false;
            System.out.println("ok");
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