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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Clouds implements LevelInformation {
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
    private Sound gameBlockHit;
    private Sound backgroundMusic;


    /**
     *
     */
    public Clouds() {
        numberOfBalls = 3;
        numberOfBlocksToRemove = 105;
        background = new sprites.backgrounds.Clouds();
        paddleSpeed = 15;
        paddleWidth = 130;
        ballsList = initialBalls();
        velList = initialBallVelocities();
        blocks = initialBlocks();
        frameBlocks = initialFrameBlocks();
        pitBlocks = initialPitBlocks();
        paddleHeight = (int) (ScreenSettings.FRAME_HEIGHT * 0.03);
        levelName = "Clouds";
        paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.95);
        paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
        paddleHit = new Sound("/Clouds/Paddle-Hit.wav");
        frameBlockHit = new Sound("/Clouds/Frame-Block.wav");
        pitBlockHit = new Sound("/Clouds/Pit-Block.wav");
        gameBlockHit = new Sound("/Clouds/Game-Block.wav");
        backgroundMusic = new Sound("/Clouds/Background.wav");
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
        numberOfBalls = 3;
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
            resList.add(Velocity.fromAngleAndSpeed(135 + (i * 45), 9));
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
    public Sound getGameBlockHitSound() {
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
        Color[] colors = {new Color(Color.GRAY.getRGB()), new Color(Color.RED.getRGB()), new Color(Color.YELLOW.getRGB()), new Color(Color.GREEN.getRGB()),
                new Color(Color.WHITE.getRGB()), new Color(Color.PINK.getRGB()), new Color(Color.CYAN.getRGB())};
        double blocksWidth = (ScreenSettings.FRAME_WIDTH * 0.97 - ScreenSettings.FRAME_WIDTH * 0.03) / (numberOfBlocksToRemove / 7);
        double blocksHeight = 30;
        double xStart = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
        double yStart = ScreenSettings.FRAME_HEIGHT * 0.30;
        for (int row = 1; row <= 7; row++) {
            for (int col = 0; col < 15; col++) {
                resList.add(new Block(new Point(xStart, yStart),
                        blocksWidth, blocksHeight, "GameBlock", colors[row - 1]));
                if(row == 1) {
                    resList.get(resList.size() - 1).initializeHitsCounter(2);
                } else {
                    resList.get(resList.size() - 1).initializeHitsCounter(1);
                }
                xStart -= blocksWidth;
            }
            xStart = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
            yStart = ScreenSettings.FRAME_HEIGHT * 0.30 + (row  * blocksHeight);
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
        return FrameBlocksTypes.getRegularFrameBlocks();
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
}
