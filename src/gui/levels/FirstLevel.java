package gui.levels;

import biuoop.DrawSurface;
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

/**
 *
 */
public class FirstLevel implements LevelInformation {
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
   public FirstLevel(DrawSurface d) {
      numberOfBalls = 1;
      ballsList = initialBalls();
      velList = initialBallVelocities();
      blocks = initialBlocks();
      frameBlocks = initialFrameBlocks();
      pitBlocks = initialPitBlocks();
      paddleSpeed = 20;
      paddleWidth = 240;
      paddleHeight = (int) (ScreenSettings.FRAME_HEIGHT * 0.03);
      levelName = "First level";
      background = new Background();
      numberOfBlocksToRemove = 57;
      paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.95);
      paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
      backgroundMusicVolume = 1f;
      isSingleGameBlockSound = true;
      paddleHit = new Sound("/Clouds/Paddle-Hit.wav");
      frameBlockHit = new Sound("/Clouds/Frame-Block.wav");
      pitBlockHit = new Sound("/Clouds/Pit-Block.wav");
      gameBlockHit = new ArrayList<>();
      gameBlockHit.add(new Sound("/Clouds/Game-Block.wav"));
      backgroundMusic = new Sound("/Clouds/Background.wav");
   }

   @Override
   public int numberOfBalls() {
      return numberOfBalls;
   }


   /**
    * @return
    */
   public List<Ball> initialBalls() {
      List<Ball> resList = new ArrayList<>();
      resList.add(new Ball(new Point(ScreenSettings.FRAME_WIDTH / 5, ScreenSettings.FRAME_HEIGHT / 5), 10, Color.WHITE));
      return resList;
   }

   public void resetBalls() {
      numberOfBalls = 1;
      ballsList = initialBalls();
      velList = initialBallVelocities();
   }

   /**
    * @return
    */
   public List<Ball> getBallsList () {
      return ballsList;
   }

   @Override
   public List<Velocity> initialBallVelocities() {
      List<Velocity> resList = new ArrayList<>();
      resList.add(Velocity.fromAngleAndSpeed(20, 10));
      return resList;
   }

   /**
    * @return
    */
   public List<Velocity> getVelocityList() {
      return velList;
   }

   @Override
   public Sound getPaddleHitSound() {
      return null;
   }

   @Override
   public Sound getFrameBlockHitSound() {
      return null;
   }

   @Override
   public Sound getPitBlockHitSound() {
      return null;
   }

   @Override
   public List<Sound> getGameBlockHitSound() {
      return null;
   }

   @Override
   public Sound getBackgroundMusic() {
      return null;
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
      return FrameBlocksTypes.getRegularFrameBlocks(SpecialColors.MID_DARK_GRAY);
   }

   @Override
   public List<Block> initialPitBlocks() {
      return PitBlocksTypes.getRegularPitBlocks();
   }

   /**
    * @return
    */
   public List<Block> getGameBlocksList() {
      return blocks;
   }

   /**
    * @return
    */
   public List<Block> getFrameBlocksList() {
      return frameBlocks;
   }

   /**
    * @return
    */
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
