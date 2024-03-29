package gui.levels;

import gui.ScreenSettings;
import gui.levels.infrastructure.FrameBlocksTypes;
import gui.levels.infrastructure.LevelInformation;
import gui.levels.infrastructure.PitBlocksTypes;
import gui.motion.Velocity;
import gui.shapes.Point;
import sprites.backgrounds.infrastructure.Background;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import music.Sound;
import utilities.SpecialColors;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SunnyDay implements LevelInformation {
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


    /**
     *
     */
    public SunnyDay() {
        numberOfBalls = 9;
        numberOfBlocksToRemove = 15;
        background = new sprites.backgrounds.SunnyDay();
        paddleSpeed = 3;
        paddleWidth = 1100;
        ballsList = initialBalls();
        velList = initialBallVelocities();
        blocks = initialBlocks();
        frameBlocks = initialFrameBlocks();
        pitBlocks = initialPitBlocks();
        paddleHeight = (int) (ScreenSettings.FRAME_HEIGHT * 0.03);
        levelName = "Sunny Day";
        paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.95);
        paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
        paddleHit = new Sound("/Sunny-Day/Paddle-Hit.wav");
        frameBlockHit = new Sound("/Sunny-Day/Frame-Block.wav");
        pitBlockHit = new Sound("/Sunny-Day/Pit-Block.wav");
        gameBlockHit = new ArrayList<>();
        gameBlockHit.add(new Sound("/Sunny-Day/Game-Block.wav"));
        isSingleGameBlockSound = true;
        backgroundMusic = new Sound("/Sunny-Day/Background.wav");
        backgroundMusicVolume = 1f;
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }


    @Override
    public List<Ball> initialBalls() {
        List<Ball> resList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            resList.add(new Ball(new Point(ScreenSettings.FRAME_WIDTH / 2, ScreenSettings.FRAME_HEIGHT * 0.93), 10, Color.WHITE));
        }
        return resList;
    }

    @Override
    public void resetBalls() {
        numberOfBalls = 9;
        ballsList = initialBalls();
        velList = initialBallVelocities();
    }

    @Override
    public List<Ball> getBallsList() {
        return ballsList;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> resList = new ArrayList<>();
        for(int i = 0; i < numberOfBalls; i++) {
            resList.add(Velocity.fromAngleAndSpeed(116 + (i * 16), 9));
        }
        return resList;
    }

    @Override
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
        Color[] colors = {new Color(Color.CYAN.getRGB()), new Color(Color.CYAN.getRGB()), new Color(Color.PINK.getRGB()), new Color(Color.PINK.getRGB()),
                new Color(Color.BLUE.getRGB()), new Color(Color.BLUE.getRGB()), new Color(Color.GREEN.getRGB()),
                new Color(Color.GREEN.getRGB()), new Color(Color.GREEN.getRGB()), new Color(Color.YELLOW.getRGB()), new Color(Color.YELLOW.getRGB()),
                new Color(Color.ORANGE.getRGB()), new Color(Color.ORANGE.getRGB()), new Color(Color.RED.getRGB()), new Color(Color.RED.getRGB())};
        double blocksWidth = (ScreenSettings.FRAME_WIDTH * 0.97 - ScreenSettings.FRAME_WIDTH * 0.03) / numberOfBlocksToRemove;
        double blocksHeight = 30;
        double xStart = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
        double y = ScreenSettings.FRAME_HEIGHT * 0.45;
        for (int i = 0; i < numberOfBlocksToRemove; i++) {
            resList.add(new Block(new Point(xStart, y),
                    blocksWidth, blocksHeight, "GameBlock", colors[i]));
            resList.get(resList.size() - 1).initializeHitsCounter(1);
                xStart -= blocksWidth;
        }
        return resList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }

    @Override
    public List<Block> getGameBlocksList() {
        return blocks;
    }

    @Override
    public List<Block> initialFrameBlocks() {
        return FrameBlocksTypes.getRegularFrameBlocks(SpecialColors.MID_DARK_GRAY);
    }

    @Override
    public List<Block> initialPitBlocks() {
        return PitBlocksTypes.getRegularPitBlocks();
    }

    @Override
    public List<Block> getFrameBlocksList() {
        return frameBlocks;
    }

    @Override
    public List<Block> getPitBlocksList() {
        return pitBlocks;
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
