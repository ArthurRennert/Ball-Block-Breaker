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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TheTower implements LevelInformation {
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


    public TheTower() {
        numberOfBalls = 2;
        numberOfBlocksToRemove = 40;
        background = new sprites.backgrounds.TheTower();
        paddleSpeed = 15;
        paddleWidth = 130;
        ballsList = initialBalls();
        velList = initialBallVelocities();
        blocks = initialBlocks();
        frameBlocks = initialFrameBlocks();
        pitBlocks = initialPitBlocks();
        paddleHeight = (int) (ScreenSettings.FRAME_HEIGHT * 0.03);
        levelName = "The Tower";
        paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.95);
        paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }


    public List<Ball> initialBalls() {
        List<Ball> resList = new ArrayList<>();
        for(int i = 0; i < numberOfBalls; i++) {
            resList.add(new Ball(new Point(ScreenSettings.FRAME_WIDTH / 2, ScreenSettings.FRAME_HEIGHT * 0.93), 10, Color.WHITE));
        }
        return resList;
    }

    public void resetBalls() {
        numberOfBalls = 2;
        ballsList = initialBalls();
        velList = initialBallVelocities();
    }

    public List<Ball> getBallsList () {
        return ballsList;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> resList = new ArrayList<>();
        resList.add(Velocity.fromAngleAndSpeed(130, 10));
        resList.add(Velocity.fromAngleAndSpeed(230, 10));
        return resList;
    }

    public List<Velocity> getVelocityList() {
        return velList;
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
        Color[] colors = {new Color(Color.GRAY.getRGB()), new Color(Color.RED.getRGB()), new Color(Color.YELLOW.getRGB()), new Color(Color.BLUE.getRGB()),
                new Color(Color.WHITE.getRGB())};
        int blocksWidth = 100;
        int blocksHeight = 40;
        double xStart = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
        double yStart = ScreenSettings.FRAME_HEIGHT * 0.30;
        for (int row = 1; row <= 5; row++) {
            for (int i = 0; i <= (10 - row); i++) {
                resList.add(new Block(new Point(xStart, yStart),
                        blocksWidth, blocksHeight, "gameblocks", colors[row - 1]));
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

    public List<Block> getFrameBlocksList() {
        return frameBlocks;
    }

    public List<Block> getPitBlocksList() {
        return pitBlocks;
    }
}
