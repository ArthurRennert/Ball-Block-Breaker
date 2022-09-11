package gui.levels;

import gui.Background;
import gui.ScreenSettings;
import gui.motion.Velocity;
import gui.shapes.Point;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RandomLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Ball> ballsList;
    private List<Velocity> velList;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Background background;
    private List<Block> blocks;
    private List<Block> frameBlocks;
    private List<Block> pitBlocks;
    private int numberOfBlocksToRemove;


    public RandomLevel() {
        numberOfBalls = 1;
        ballsList = initialBalls();
        velList = initialBallVelocities();
        blocks = initialBlocks();
        frameBlocks = initialFrameBlocks();
        pitBlocks = initialPitBlocks();
        paddleSpeed = 10;
        paddleWidth = 130;
        levelName = "Random level";
        background = new Background(ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT, ScreenSettings.DARK_BLUE);
        numberOfBlocksToRemove = 57;
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

    public List<Ball> getBallsList () {
        return ballsList;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> resList = new ArrayList<>();
        resList.add(Velocity.fromAngleAndSpeed(20, 10));
        return resList;
    }

    public List<Velocity> getVelocityList() {
        return velList;
    }

    @Override
    public String getName() {
        return levelName;
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
        Color[] colors = {new Color(Color.GRAY.getRGB()), new Color(Color.RED.getRGB()), new Color(Color.YELLOW.getRGB()), new Color(Color.BLUE.getRGB()), new Color(Color.PINK.getRGB()),
                new Color(Color.GREEN.getRGB())};
        int blocksWidth = 100;
        int blocksHeight = 40;
        double xStart = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
        double yStart = ScreenSettings.FRAME_HEIGHT * 0.30;
        for (int row = 1; row <= 6; row++) {
            for (int i = 0; i <= (12 - row); i++) {
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
    public List<Block> initialFrameBlocks() {
        List<Block> resList = new ArrayList<>();
        resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.04), (int) (ScreenSettings.FRAME_WIDTH * 0.999999), (int) (ScreenSettings.FRAME_HEIGHT * 0.05), "UpperScreen", ScreenSettings.MID_DARK_GRAY)); //upper screen block
        resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.09), (int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.91), "LeftScreen", ScreenSettings.MID_DARK_GRAY)); //left screen block
        resList.add(new Block(new Point(ScreenSettings.FRAME_WIDTH * 0.97, ScreenSettings.FRAME_HEIGHT * 0.09), (int) (ScreenSettings.FRAME_WIDTH * 0.02999999), (int) (ScreenSettings.FRAME_HEIGHT * 0.91), "RightScreen", ScreenSettings.MID_DARK_GRAY)); //right screen block
        return resList;
    }

    @Override
    public List<Block> initialPitBlocks() {
        List<Block> resList = new ArrayList<>();
        resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.995), ScreenSettings.FRAME_WIDTH, (int) (ScreenSettings.FRAME_HEIGHT * 0.005), "LowerScreen", Color.BLACK)); //lower screen block
        return resList;
    }

    public List<Block> getBlocksList() {
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
}
