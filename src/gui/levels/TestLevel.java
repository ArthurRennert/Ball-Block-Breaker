package gui.levels;

import gui.levels.infrastructure.FrameBlocksTypes;
import gui.levels.infrastructure.LevelInformation;
import gui.levels.infrastructure.PitBlocksTypes;
import sprites.backgrounds.infrastructure.Background;
import gui.ScreenSettings;
import gui.motion.Velocity;
import gui.shapes.Point;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import music.Sound;
import utilities.SpecialColors;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Ball> ballsList;
    private List<Velocity> velList;
    private int paddleSpeed;
    private int paddleWidth;
    private int paddleHeight;
    private Point paddleInitialPoint;
    private Paddle paddle;
    private String levelName;
    private sprites.backgrounds.infrastructure.Background background;
    private List<Block> blocks;
    private List<Block> frameBlocks;
    private List<Block> pitBlocks;
    private int numberOfBlocksToRemove;
    private Sound paddleHit;
    private Sound frameBlockHit;
    private Sound pitBlockHit;
    private List<Sound> gameBlockHit;
    private boolean isSingleGameBlockSound;
    private Sound backgroundMusic;
    private float backgroundMusicVolume;


    public TestLevel() {
        numberOfBalls = 1;
        ballsList = initialBalls();
        velList = initialBallVelocities();
        blocks = initialBlocks();
        frameBlocks = initialFrameBlocks();
        pitBlocks = initialPitBlocks();
        paddleSpeed = 10;
        paddleWidth = 240;
        paddleHeight = (int) (ScreenSettings.FRAME_HEIGHT * 0.03);
        paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.95);
        paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
        levelName = "Random";
        background = new Background();
        numberOfBlocksToRemove = 1;
        backgroundMusicVolume = 1f;
        paddleHit = new Sound("/Clouds/Paddle-Hit.wav");
        frameBlockHit = new Sound("/Clouds/Frame-Block.wav");
        pitBlockHit = new Sound("/Clouds/Pit-Block.wav");
        gameBlockHit = new ArrayList<>();
        gameBlockHit.add(new Sound("/Clouds/Game-Block.wav"));
        isSingleGameBlockSound = true;
        backgroundMusic = new Sound("/Clouds/Background.wav");
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    public List<Ball> initialBalls() {
        List<Ball> resList = new ArrayList<>();
        resList.add(new Ball(new Point(ScreenSettings.FRAME_WIDTH / 5, ScreenSettings.FRAME_HEIGHT / 5), 10, Color.WHITE));
        return resList;
    }

    /**
     *
     */
    public void resetBalls() {
        numberOfBalls = 1;
        ballsList = initialBalls();
        velList = initialBallVelocities();
//        for (Ball elem : ballsList) {
//            elem.setPoint(new Point(ScreenSettings.FRAME_WIDTH / 5, ScreenSettings.FRAME_HEIGHT / 5));
//        }
    }

    public List<Ball> getBallsList () {
        return ballsList;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> resList = new ArrayList<>();
        resList.add(Velocity.fromAngleAndSpeed(20, 15));
        return resList;
    }

    public List<Velocity> getVelocityList() {
        return velList;
    }

    @Override
    public Sound getPaddleHitSound() {
        return paddleHit;
    }

    @Override
    public Sound getFrameBlockHitSound() {
        return frameBlockHit;
    }

    @Override
    public Sound getPitBlockHitSound() {
        return pitBlockHit;
    }

    @Override
    public List<Sound> getGameBlockHitSound() {
        return gameBlockHit;
    }

    @Override
    public Sound getBackgroundMusic() {
        return backgroundMusic;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public int paddleHeight() {
        return paddleHeight;
    }

    @Override
    public Point paddleInitialPoint() {
        return paddleInitialPoint;
    }

    @Override
    public Paddle getPaddle() {
        return paddle;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Background getBackground() {
        return background;
    }

    @Override
    public List<Block> initialBlocks() {
        List<Block> resList = new ArrayList<>();
        int blocksWidth = 500;
        int blocksHeight = 40;
        double xStart = ScreenSettings.FRAME_WIDTH * 0.60 - blocksWidth;
        double yStart = ScreenSettings.FRAME_HEIGHT * 0.30;

        resList.add(new Block(new Point(xStart, yStart),
                blocksWidth, blocksHeight, "gameblocks", new Color(Color.GRAY.getRGB())));
        return resList;
    }

    @Override
    public List<Block> initialFrameBlocks() {
        return FrameBlocksTypes.getRegularFrameBlocks(SpecialColors.MID_DARK_GRAY);
    }

    @Override
    public List<Block> initialPitBlocks() {
        return PitBlocksTypes.getRegularPitBlocks();
    }

    public List<Block> getGameBlocksList() {
        return blocks;
    }

    public List<Block> getFrameBlocksList() {
        return frameBlocks;
    }

    public List<Block> getPitBlocksList() {
        return pitBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }

    @Override
    public float getBackgroundMusicVolume() {
        return backgroundMusicVolume;
    }

    @Override
    public boolean isSingleGameBlockSound() {
        return isSingleGameBlockSound;
    }
}
