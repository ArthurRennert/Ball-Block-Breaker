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

public class Terminator implements LevelInformation {
   private static final Color EERIE_BLACK = new Color(27, 27, 27);
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


   public Terminator() {
      numberOfBalls = 1;
      numberOfBlocksToRemove = 144;
      background = new sprites.backgrounds.Terminator();
      paddleSpeed = 8;
      paddleWidth = 140;
      ballsList = initialBalls();
      velList = initialBallVelocities();
      blocks = initialBlocks();
      frameBlocks = initialFrameBlocks();
      pitBlocks = initialPitBlocks();
      paddleHeight = (int) (ScreenSettings.FRAME_HEIGHT * 0.03);
      levelName = "Terminator";
      paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.95);
      paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
      paddleHit = new Sound("/Terminator/Paddle-Hit.wav");
      frameBlockHit = new Sound("/Terminator/Frame-Block.wav");
      pitBlockHit = new Sound("/Terminator/Pit-Block.wav");
      gameBlockHit = new ArrayList<>();
      gameBlockHit.add(new Sound("/Terminator/Game-Block.wav"));
      isSingleGameBlockSound = true;
      backgroundMusic = new Sound("/Terminator/Background.wav");
      backgroundMusicVolume = 1f;
   }

   @Override
   public int numberOfBalls() {
      return numberOfBalls;
   }


   public List<Ball> initialBalls() {
      List<Ball> resList = new ArrayList<>();
      for (int i = 0; i < numberOfBalls; i++) {
         resList.add(new Ball(new Point(ScreenSettings.FRAME_WIDTH / 2, ScreenSettings.FRAME_HEIGHT / 3), 10, Color.RED));
      }
      return resList;
   }

   public void resetBalls() {
      numberOfBalls = 1;
      ballsList = initialBalls();
      velList = initialBallVelocities();
   }

   public List<Ball> getBallsList () {
      return ballsList;
   }

   @Override
   public List<Velocity> initialBallVelocities() {
      List<Velocity> resList = new ArrayList<>();
      resList.add(Velocity.fromAngleAndSpeed(0, 10));
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
      Color[] colors = {EERIE_BLACK, new Color(Color.RED.getRGB())};
      double blocksWidth = (ScreenSettings.FRAME_WIDTH * 0.97 - ScreenSettings.FRAME_WIDTH * 0.03) / 15;
      double blocksHeight = 30;
      double xRight = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
      double xLeft = ScreenSettings.FRAME_WIDTH * 0.03;
      double yTop = ScreenSettings.FRAME_HEIGHT * 0.09;
      double yBottom = ScreenSettings.FRAME_HEIGHT * 0.09 + (14 * blocksHeight);

      for (int row = 1; row <= 8; row++) {
         for (int col = 0; col < (9 - row); col++) {
            resList.add(new Block(new Point(xRight, yTop),
                    blocksWidth, blocksHeight, "GameBlock", colors[row % 2]));
            resList.add(new Block(new Point(xRight, yBottom),
                    blocksWidth, blocksHeight, "GameBlock", colors[row % 2]));
            resList.add(new Block(new Point(xLeft, yTop),
                    blocksWidth, blocksHeight, "GameBlock", colors[row % 2]));
            resList.add(new Block(new Point(xLeft, yBottom),
                    blocksWidth, blocksHeight, "GameBlock", colors[row % 2]));
            xRight -= blocksWidth;
            xLeft += blocksWidth;
         }
         xRight = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
         xLeft = ScreenSettings.FRAME_WIDTH * 0.03;
         yTop = ScreenSettings.FRAME_HEIGHT * 0.09 + (row  * blocksHeight);
         yBottom = ScreenSettings.FRAME_HEIGHT * 0.09 + ((14 - row) * blocksHeight);
      }

      for (int i = 0; i < numberOfBlocksToRemove; i++) {
         if (i % 2 == 1) {
            resList.get(i).initializeHitsCounter(2);
         } else {
            resList.get(i).initializeHitsCounter(1);
         }
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
      return  FrameBlocksTypes.getRegularFrameBlocks(SpecialColors.RAISIN_BLACK);
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

