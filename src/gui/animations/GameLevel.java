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
 * The GameLevel class manages a single level in the game.
 *
 * It initializes and runs all game components such as blocks, balls, paddle,
 * background, counters, and manages collisions, animations, sounds, and game logic.
 */
public class GameLevel implements Animation {
    private static BlockRemover blockRemoverListener;
    private static ScoreTracking scoreTrackingListener;
    private static BallRemover ballRemoverListener;
    private static SoundMaker soundMakerListener;
    private static BallAdder ballAdderListener;
    private static GameInformation gameInformation;
    private static Timer timer;

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
    private MusicPlayer musicPlayer;

    /**
     * Constructs a new GameLevel.
     *
     * @param levelInfo the level configuration and information
     * @param ks the keyboard sensor to detect user input
     * @param ar the animation runner to run animations
     * @param score the counter tracking the score
     * @param lives the counter tracking the remaining lives
     * @param musicPlayer the music player for sound effects and background music
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter score, Counter lives, MusicPlayer musicPlayer) {
        environment = new GameEnvironment(ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
        sprites = new SpriteCollection();
        blocksCounter = new Counter();
        ballsCounter = new Counter();
        levelInformation = levelInfo;
        keyboardSensor = ks;
        animationRunner = ar;
        this.musicPlayer = musicPlayer;
        this.score = score;
        this.lives = lives;
    }

    /**
     * Initializes the game elements for the level.
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
        musicPlayer.setPaddleHit(levelInformation.getPaddleHitSound());
        musicPlayer.setFrameBlockHit(levelInformation.getFrameBlockHitSound());
        musicPlayer.setPitBlockHit(levelInformation.getPitBlockHitSound());
        musicPlayer.setGameBlockHit(levelInformation.getGameBlockHitSound(), levelInformation.isSingleGameBlockSound());
        musicPlayer.setBackgroundMusic(levelInformation.getBackgroundMusic());
    }

    private void initializeListeners() {
        blockRemoverListener = new BlockRemover(this, blocksCounter);
        scoreTrackingListener = new ScoreTracking(score);
        ballRemoverListener = new BallRemover(this, ballsCounter, lives);
        soundMakerListener = new SoundMaker(musicPlayer);
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
     * Runs the game loop for this level.
     */
    public void run() {
//        levelSounds.playBackgroundMusic();
        this.running = true;
        timer.timerInit();
        this.animationRunner.run(this);
    }

    /**
     * Starts background music for the level.
     */
    public void playBackgroundMusic() {
        musicPlayer.playBackgroundMusic(levelInformation.getBackgroundMusicVolume());
    }

    /**
     * Stops background music.
     */
    public void stopBackgroundMusic() {
        musicPlayer.stopBackgroundMusic();
    }

    /**
     * Returns the sprite list excluding the paddle and game blocks.
     *
     * @return the filtered sprite collection used for instruction screen
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
     * Returns all the sprites in the level.
     *
     * @return the sprite collection
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }


    /**
     * Restarts the level after the player loses a life.
     */
    public void restartAfterLiveLost() {
        timer.stopTimer();
        if (lives.getValue() == 0) {
            return;
        }
        levelInformation.getPaddle().setLocation(levelInformation.paddleInitialPoint());
        levelInformation.resetBalls();
        initializeBalls();
        this.animationRunner.run(new Countdown(2, this.sprites, animationRunner, keyboardSensor, musicPlayer));
        timer.restartTimer();
    }


    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes a collidable object from the environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Performs one frame of the game loop.
     *
     * @param d the surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(timer);
        if (this.keyboardSensor.isPressed("enter")) {
            timer.stopTimer();
            musicPlayer.pauseBackgroundMusic();
            this.animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new PauseScreen(this.keyboardSensor, this.sprites)));
            musicPlayer.unpauseBackgroundMusic();
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
     * Returns the level's timer.
     *
     * @return the timer instance
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Returns the number of balls remaining in the level.
     *
     * @return the number of balls
     */
    public int getNumOfBallsLeft() {
        return ballsCounter.getValue();
    }

    /**
     * Returns the number of blocks remaining in the level.
     *
     * @return the number of blocks
     */
    public int getNumOfBlocksLeft() {
        return blocksCounter.getValue();
    }

    /**
     * Indicates whether the level animation should stop.
     *
     * @return true if the level should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}