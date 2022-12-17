package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.GameEnvironment;
import collision.listeners.hit_listeners.*;
import gui.ScreenSettings;
import gui.animations.infrastructure.Animation;
import gui.animations.infrastructure.AnimationRunner;
import gui.levels.infrastructure.LevelInformation;
import gui.motion.Velocity;
import gui.shapes.Point;
import music.MusicPlayer;
import music.Sound;
import sprites.Ball;
import sprites.Block;
import sprites.GameInformation;
import collision.Collidable;
import sprites.backgrounds.infrastructure.Background;
import sprites.infrastructure.Sprite;
import sprites.infrastructure.SpriteCollection;
import utilities.Counter;
import utilities.Timer;

import java.awt.*;
import java.util.List;


/**
 *
 */
public class GameLevel implements Animation {
    private static BlockRemover blockRemoverListener;
    private static ScoreTracking scoreTrackingListener;
    private static BallRemover ballRemoverListener;
    private static SoundMaker soundMakerListener;
    private static BallAdder ballAdderListener;
    private static GameInformation gameInformation;
    private static Timer timer;
    private static MusicPlayer levelSounds;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private Counter lives;
    private AnimationRunner animationRunner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
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
        levelInformation = levelInfo;
        keyboardSensor = ks;
        animationRunner = ar;
        levelSounds = new MusicPlayer();
        this.score = score;
        this.lives = lives;
    }

    /**
     *
     */
    public void initialize() {
        timer = new Timer(0, 0, 0);
        initializeBackground();
        initializeSounds();
        initializeListeners();
        initializeFrameBlocks();
        initializeGameBlocks();
        initializePitBlocks();
        initializePaddle();
        initializeBalls();
        initializeGameInformation();
    }

    private void initializeBackground() {
        levelInformation.getBackground().addToGame(this);
    }

    private void initializeSounds() {
        levelSounds.setPaddleHit(levelInformation.getPaddleHitSound());
        levelSounds.setFrameBlockHit(levelInformation.getFrameBlockHitSound());
        levelSounds.setPitBlockHit(levelInformation.getPitBlockHitSound());
        levelSounds.setGameBlockHit(levelInformation.getGameBlockHitSound());
        levelSounds.setBackgroundMusic(levelInformation.getBackgroundMusic());
    }

    private void initializeListeners() {
        blockRemoverListener = new BlockRemover(this, blocksCounter);
        scoreTrackingListener = new ScoreTracking(score);
        ballRemoverListener = new BallRemover(this, ballsCounter, lives);
        soundMakerListener = new SoundMaker(levelSounds);
        //        BallAdder ballAdderListener = new BallAdder(this, ballsCounter);
    }

    private void initializeFrameBlocks() {
        for (Block elem : levelInformation.getFrameBlocksList()) {
            elem.addToGame(this);
        }
    }

    private void initializeGameBlocks() {
        blocksCounter.setValue(levelInformation.numberOfBlocksToRemove());
        for (Block elem : levelInformation.getGameBlocksList()) {
            elem.addToGame(this);
            elem.addHitListener(blockRemoverListener);
            elem.addHitListener(scoreTrackingListener);
        }
    }

    private void initializePitBlocks() {
        for (Block elem : levelInformation.getPitBlocksList()) {
            elem.addToGame(this);
            elem.addHitListener(ballRemoverListener);
        }
    }

    private void initializePaddle() {
        levelInformation.getPaddle().setGUI(animationRunner.getGUI());
        levelInformation.getPaddle().addToGame(this);
    }

    private void initializeBalls() {
        ballsCounter.setValue(levelInformation.numberOfBalls());
        List<Ball> ballList = levelInformation.getBallsList();
        List<Velocity> velocityList = levelInformation.getVelocityList();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            ballList.get(i).addToGame(this);
            ballList.get(i).setGameEnvironment(environment);
            ballList.get(i).setVelocity(velocityList.get(i));
            ballList.get(i).addHitListener(soundMakerListener);
        }
    }

    private void initializeGameInformation() {
        gameInformation = new GameInformation(score, lives, levelInformation.levelName(), timer, new Point(0, 0), ScreenSettings.FRAME_WIDTH, (int) (ScreenSettings.FRAME_HEIGHT * 0.04));
        gameInformation.addToGame(this);
    }

    /**
     *
     */
    public void run() {
//        levelSounds.playBackgroundMusic();
        this.running = true;
        timer.timerInit();
        this.animationRunner.run(this);
    }

    /**
     *
     */
    public void playBackgroundMusic() {
        levelSounds.playBackgroundMusic();
    }

    /**
     * @return
     */
    public SpriteCollection getSpritesListForInstructionAnimation() {
        SpriteCollection s = new SpriteCollection(sprites);
        s.removeSprite(levelInformation.getPaddle());
        for (Ball elem : levelInformation.getBallsList()) {
            s.removeSprite(elem);
        }
        for (Block elem : levelInformation.getGameBlocksList()) {
            s.removeSprite(elem);
        }
        s.removeSprite(levelInformation.getBackground());
        s.addSprite(new Background(Color.BLACK));
        return s;
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
    public void restartAfterLiveLost() {
        //add here stop music
        timer.stopTimer();
        if (lives.getValue() == 0) {
            return;
        }
        levelInformation.getPaddle().setLocation(levelInformation.paddleInitialPoint());
        levelInformation.resetBalls();
        initializeBalls();
        this.animationRunner.run(new Countdown(2, this.sprites, animationRunner, keyboardSensor));
        timer.restartTimer();
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
            timer.stopTimer();
            this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new PauseScreen(this.keyboardSensor, this.sprites)));
            timer.restartTimer();
        }
        if (blocksCounter.getValue() == 0) {
            score.increase(100);
            timer.stopTimer();
            this.running = false;
        }
        if (lives.getValue() == 0) {
            timer.stopTimer();
            this.running = false;
        }
    }

    /**
     * @return
     */
    public Timer getTimer() {
        return timer;
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