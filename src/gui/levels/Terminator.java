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

/**
 * A level inspired by the "Terminator" movie.
 *
 * Implements the LevelInformation interface and defines all game elements for this level,
 * including blocks, sounds, paddle settings, and background.
 */
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


   /**
    * Constructs the Terminator level with predefined configuration.
    * Sets paddle and ball parameters, background, blocks, and sound effects.
    */
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
      paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.92);
      paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
      paddleHit = new Sound("/Terminator/Paddle-Hit.wav");
      frameBlockHit = new Sound("/Terminator/Frame-Block.wav");
      pitBlockHit = new Sound("/Terminator/Pit-Block.wav");
      gameBlockHit = new ArrayList<>();
      initializeAllGameBlockHitSounds();
      isSingleGameBlockSound = false;
      backgroundMusic = new Sound("/Terminator/Background.wav");
      backgroundMusicVolume = 1f;
   }

   /**
    * Loads sound effects for each game block in the level.
    */
   private void initializeAllGameBlockHitSounds() {
      gameBlockHit.add(new Sound("/Terminator/Game-Block/Game-Block1.wav"));
      gameBlockHit.add(new Sound("/Terminator/Game-Block/Game-Block2.wav"));
      gameBlockHit.add(new Sound("/Terminator/Game-Block/Game-Block3.wav"));
      gameBlockHit.add(new Sound("/Terminator/Game-Block/Game-Block4.wav"));
      gameBlockHit.add(new Sound("/Terminator/Game-Block/Game-Block5.wav"));
      gameBlockHit.add(new Sound("/Terminator/Game-Block/Game-Block6.wav"));
      gameBlockHit.add(new Sound("/Terminator/Game-Block/Game-Block7.wav"));

   }

   /**
    * Returns the number of balls in the level.
    *
    * @return number of balls
    */
   @Override
   public int numberOfBalls() {
      return numberOfBalls;
   }

   /**
    * Returns a list of Ball objects at their initial positions.
    *
    * @return list of initial balls
    */
   public List<Ball> initialBalls() {
      List<Ball> resList = new ArrayList<>();
      for (int i = 0; i < numberOfBalls; i++) {
         resList.add(new Ball(new Point(ScreenSettings.FRAME_WIDTH / 2, ScreenSettings.FRAME_HEIGHT / 2.5), 10, Color.RED));
      }
      return resList;
   }

   /**
    * Resets the balls to their initial state.
    */
   public void resetBalls() {
      numberOfBalls = 1;
      ballsList = initialBalls();
      velList = initialBallVelocities();
   }

   /**
    * Returns the list of Ball objects in the level.
    *
    * @return list of balls
    */
   @Override
   public List<Ball> getBallsList () {
      return ballsList;
   }

   /**
    * Returns a list of initial velocities for the balls.
    *
    * @return list of velocities
    */
   @Override
   public List<Velocity> initialBallVelocities() {
      List<Velocity> resList = new ArrayList<>();
      resList.add(Velocity.fromAngleAndSpeed(0, 10));
      return resList;
   }

   /**
    * Returns the list of velocities for the balls.
    *
    * @return list of velocities
    */
   @Override
   public List<Velocity> getVelocityList() {
      return velList;
   }

   /**
    * Returns the sound that plays when the paddle is hit.
    *
    * @return paddle hit sound
    */
   @Override
   public Sound getPaddleHitSound() {
      return paddleHit;
   }

   /**
    * Returns the sound that plays when a frame block is hit.
    *
    * @return frame block hit sound
    */
   @Override
   public Sound getFrameBlockHitSound() {
      return frameBlockHit;
   }

   /**
    * Returns the sound that plays when a pit block is hit.
    *
    * @return pit block hit sound
    */
   @Override
   public Sound getPitBlockHitSound() {
      return pitBlockHit;
   }

   /**
    * Returns the list of sounds used when game blocks are hit.
    *
    * @return list of game block hit sounds
    */
   @Override
   public List<Sound> getGameBlockHitSound() {
      return gameBlockHit;
   }

   /**
    * Returns the background music of the level.
    *
    * @return background music sound
    */
   @Override
   public Sound getBackgroundMusic() {
      return backgroundMusic;
   }

   /**
    * Returns the speed of the paddle.
    *
    * @return paddle speed
    */
   @Override
   public int paddleSpeed() {
      return paddleSpeed;
   }

   /**
    * Returns the width of the paddle.
    *
    * @return paddle width
    */
   @Override
   public int paddleWidth() {
      return paddleWidth;
   }

   /**
    * Returns the height of the paddle.
    *
    * @return paddle height
    */
   @Override
   public int paddleHeight() {
      return paddleHeight;
   }

   /**
    * Returns the starting position of the paddle.
    *
    * @return initial paddle position
    */
   @Override
   public Point paddleInitialPoint() {
      return paddleInitialPoint;
   }

   /**
    * Returns the Paddle object used in this level.
    *
    * @return paddle
    */
   @Override
   public Paddle getPaddle() {
      return paddle;
   }

   /**
    * Returns the name of the level.
    *
    * @return level name
    */
   @Override
   public String levelName() {
      return levelName;
   }

   /**
    * Returns the background sprite for the level.
    *
    * @return background sprite
    */
   @Override
   public Background getBackground() {
      return background;
   }

   /**
    * Returns a list of blocks at the start of the level.
    *
    * @return list of game blocks
    */
   @Override
   public List<Block> initialBlocks() {
      List<Block> resList = new ArrayList<>();
      Color[] colors = {EERIE_BLACK, new Color(Color.RED.getRGB())};
      double blocksWidth = (ScreenSettings.FRAME_WIDTH * 0.97 - ScreenSettings.FRAME_WIDTH * 0.03) / 15;
      double blocksHeight = 30;
      double xRight = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
      double xLeft = ScreenSettings.FRAME_WIDTH * 0.03;
      double yTop = ScreenSettings.FRAME_HEIGHT * 0.09;
      double yBottom = ScreenSettings.FRAME_HEIGHT * 0.09 + (18 * blocksHeight);

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
         yTop = ScreenSettings.FRAME_HEIGHT * 0.09 + (row * blocksHeight);
         yBottom = ScreenSettings.FRAME_HEIGHT * 0.09 + ((18 - row) * blocksHeight);
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

   /**
    * Returns the number of blocks that must be removed to win the level.
    *
    * @return number of blocks to remove
    */
   @Override
   public int numberOfBlocksToRemove() {
      return numberOfBlocksToRemove;
   }

   /**
    * Returns the current list of game blocks in the level.
    *
    * @return list of game blocks
    */
   @Override
   public List<Block> getGameBlocksList() {
      return blocks;
   }

   /**
    * Returns the frame blocks (top, left, right) for the level.
    *
    * @return list of frame blocks
    */
   @Override
   public List<Block> initialFrameBlocks() {
      return  FrameBlocksTypes.getRegularFrameBlocks(SpecialColors.RAISIN_BLACK);
   }

   /**
    * Returns the pit blocks (bottom) for the level.
    *
    * @return list of pit blocks
    */
   @Override
   public List<Block> initialPitBlocks() {
      return PitBlocksTypes.getRegularPitBlocks();
   }

   /**
    * Returns the current frame blocks.
    *
    * @return list of frame blocks
    */
   @Override
   public List<Block> getFrameBlocksList() {
      return frameBlocks;
   }

   /**
    * Returns the current pit blocks.
    *
    * @return list of pit blocks
    */
   @Override
   public List<Block> getPitBlocksList() {
      return pitBlocks;
   }

   /**
    * Returns the volume of the background music.
    *
    * @return background music volume (between 0 and 1)
    */
   @Override
   public float getBackgroundMusicVolume() {
      return backgroundMusicVolume;
   }

   /**
    * Indicates whether a single sound is used for all game blocks.
    *
    * @return true if single sound is used, false otherwise
    */
   @Override
   public boolean isSingleGameBlockSound() {
      return isSingleGameBlockSound;
   }
}

