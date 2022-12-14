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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DirectHit implements LevelInformation {
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


   /**
    *
    */
   public DirectHit() {
      numberOfBalls = 1;
      background = new sprites.backgrounds.DirectHit();
      paddleSpeed = 20;
      paddleWidth = 100;
      ballsList = initialBalls();
      velList = initialBallVelocities();
      blocks = initialBlocks();
      frameBlocks = initialFrameBlocks();
      pitBlocks = initialPitBlocks();
      paddleHeight = (int) (ScreenSettings.FRAME_HEIGHT * 0.03);
      levelName = "Direct Hit";
      numberOfBlocksToRemove = 1;
      paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.95);
      paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
   }

   @Override
   public int numberOfBalls() {
      return numberOfBalls;
   }


   @Override
   public List<Ball> initialBalls() {
      List<Ball> resList = new ArrayList<>();
      resList.add(new Ball(new Point(ScreenSettings.FRAME_WIDTH  / 2, ScreenSettings.FRAME_HEIGHT * 0.93), 10, Color.WHITE));
      return resList;
   }

   @Override
   public List<Velocity> initialBallVelocities() {
      List<Velocity> resList = new ArrayList<>();
      resList.add(Velocity.fromAngleAndSpeed(0, 10));
      return resList;
   }

   @Override
   public int paddleSpeed() {
      return 0;
   }

   @Override
   public int paddleWidth() {
      return paddleSpeed;
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
      Color[] colors = {new Color(Color.RED.getRGB())};
      int blocksWidth = 30;
      int blocksHeight = 30;
      resList.add(new Block(new Point(((ScreenSettings.FRAME_WIDTH  - blocksWidth) / 2), ScreenSettings.FRAME_HEIGHT * 0.35),
              blocksWidth, blocksHeight, "gameblocks", colors[0]));
      resList.get(resList.size() - 1).initializeHitsCounter(1);
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
   public List<Ball> getBallsList() {
      return ballsList;
   }

   @Override
   public void resetBalls() {
      numberOfBalls = 1;
      ballsList = initialBalls();
      velList = initialBallVelocities();
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

   @Override
   public List<Velocity> getVelocityList() {
      return velList;
   }
}
