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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A level definition inspired by the TV show "Friends".
 *
 * This class implements all required data for a playable level, including
 * ball and paddle settings, blocks layout, sound effects, background, and frame/pit blocks.
 */
public class Friends implements LevelInformation {
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
    * Constructs the Friends level with predefined game elements.
    */
   public Friends() {
      numberOfBalls = 1;
      numberOfBlocksToRemove = 206;
      background = new sprites.backgrounds.Friends();
      paddleSpeed = 8;
      paddleWidth = 130;
      ballsList = initialBalls();
      velList = initialBallVelocities();
      blocks = initialBlocks();
      frameBlocks = initialFrameBlocks();
      pitBlocks = initialPitBlocks();
      paddleHeight = (int) (ScreenSettings.FRAME_HEIGHT * 0.03);
      levelName = "Friends";
      paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.92);
      paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
      paddleHit = new Sound("/Clouds/Paddle-Hit.wav");
      frameBlockHit = new Sound("/Friends/Frame-Block.wav");
      pitBlockHit = new Sound("/Friends/Pit-Block.wav");
      gameBlockHit = new ArrayList<>();
      initializeAllGameBlockHitSounds();
      isSingleGameBlockSound = false;
      backgroundMusic = new Sound("/Friends/Background.wav");
      backgroundMusicVolume = 0.2f;
   }

   /**
    * Loads sound effects for each game block in the level.
    */
   private void initializeAllGameBlockHitSounds() {
      for (int i = 1; i <= 150; i++) {
         gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block" + i + ".wav"));
      }
   }

   /**
    * Returns the number of balls in this level.
    *
    * @return the number of balls
    */
   @Override
   public int numberOfBalls() {
      return numberOfBalls;
   }

   /**
    * Returns a list of the Ball objects that appear at the start of the level.
    *
    * @return a list of initial balls
    */
   @Override
   public List<Ball> initialBalls() {
      List<Ball> resList = new ArrayList<>();
      for (int i = 0; i < numberOfBalls; i++) {
         resList.add(new Ball(new Point(ScreenSettings.FRAME_WIDTH / 2, ScreenSettings.FRAME_HEIGHT * 0.90), 10, Color.WHITE));
      }
      return resList;
   }

   /**
    * Resets the balls to their initial state.
    */
   @Override
   public void resetBalls() {
      numberOfBalls = 1;
      ballsList = initialBalls();
      velList = initialBallVelocities();
   }

   /**
    * Returns the current list of balls in the level.
    *
    * @return a list of balls
    */
   @Override
   public List<Ball> getBallsList() {
      return ballsList;
   }

   /**
    * Returns the initial velocity for each ball.
    *
    * @return list of velocities
    */
   @Override
   public List<Velocity> initialBallVelocities() {
      List<Velocity> resList = new ArrayList<>();
      resList.add(Velocity.fromAngleAndSpeed(180, 9));
      return resList;
   }

   /**
    * Returns the velocity list of the balls.
    *
    * @return list of velocities
    */
   @Override
   public List<Velocity> getVelocityList() {
      return velList;
   }

   /**
    * Returns the sound effect played when the paddle is hit.
    *
    * @return paddle hit sound
    */
   @Override
   public Sound getPaddleHitSound() {
      return paddleHit;
   }

   /**
    * Returns the sound effect played when a frame block is hit.
    *
    * @return frame block hit sound
    */
   @Override
   public Sound getFrameBlockHitSound() {
      return frameBlockHit;
   }

   /**
    * Returns the sound effect played when a pit block is hit.
    *
    * @return pit block hit sound
    */
   @Override
   public Sound getPitBlockHitSound() {
      return pitBlockHit;
   }

   /**
    * Returns the list of sounds used for game block hits.
    *
    * @return list of game block hit sounds
    */
   @Override
   public List<Sound> getGameBlockHitSound() {
      return gameBlockHit;
   }

   /**
    * Returns the background music for this level.
    *
    * @return background music sound
    */
   @Override
   public Sound getBackgroundMusic() {
      return backgroundMusic;
   }

   /**
    * Returns the paddle's speed.
    *
    * @return paddle speed
    */
   @Override
   public int paddleSpeed() {
      return paddleSpeed;
   }

   /**
    * Returns the paddle's width.
    *
    * @return paddle width
    */
   @Override
   public int paddleWidth() {
      return paddleWidth;
   }

   /**
    * Returns the paddle's height.
    *
    * @return paddle height
    */
   @Override
   public int paddleHeight() {
      return paddleHeight;
   }

   /**
    * Returns the initial location of the paddle.
    *
    * @return initial point of the paddle
    */
   @Override
   public Point paddleInitialPoint() {
      return paddleInitialPoint;
   }

   /**
    * Returns the Paddle object for the level.
    *
    * @return paddle
    */
   @Override
   public Paddle getPaddle() {
      return paddle;
   }

   /**
    * Returns the level's name.
    *
    * @return level name
    */
   @Override
   public String levelName() {
      return levelName;
   }

   /**
    * Returns the background sprite of the level.
    *
    * @return background sprite
    */
   @Override
   public Background getBackground() {
      return background;
   }

   /**
    * Returns the list of game blocks in their initial state.
    *
    * @return list of game blocks
    */
   @Override
   public List<Block> initialBlocks() {
      List<Block> resList = new ArrayList<>();
      Color[] colors = {new Color(Color.GRAY.getRGB()), new Color(Color.RED.getRGB()), new Color(Color.YELLOW.getRGB()), new Color(Color.GREEN.getRGB()),
              new Color(Color.WHITE.getRGB()), new Color(Color.PINK.getRGB()), new Color(Color.CYAN.getRGB())};
      double blocksWidth = (ScreenSettings.FRAME_WIDTH * 0.97 - ScreenSettings.FRAME_WIDTH * 0.03) / 15;
      double blocksHeight = 30;
      double xRight = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
      double yTop = ScreenSettings.FRAME_HEIGHT * 0.09;
      for (int row = 1; row <= 10; row++) {
         for (int col = 0; col < 15; col++) {
            resList.add(new Block(new Point(xRight, yTop),
                    blocksWidth, blocksHeight, "GameBlock", colors[(int) (Math.random() * colors.length)]));
            xRight -= blocksWidth;
         }
         xRight = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
         yTop = ScreenSettings.FRAME_HEIGHT * 0.09 + (row * blocksHeight);
      }
      double xLeft = ScreenSettings.FRAME_WIDTH * 0.03;
      for (int row = 1; row <= 7; row++) {
         for (int col = 0; col < (8 - row); col++) {
            resList.add(new Block(new Point(xRight, yTop),
                    blocksWidth, blocksHeight, "GameBlock", colors[(int) (Math.random() * colors.length)]));
            resList.add(new Block(new Point(xLeft, yTop),
                    blocksWidth, blocksHeight, "GameBlock", colors[(int) (Math.random() * colors.length)]));
            xRight -= blocksWidth;
            xLeft += blocksWidth;
         }
         xRight = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
         xLeft = ScreenSettings.FRAME_WIDTH * 0.03;
         yTop = ScreenSettings.FRAME_HEIGHT * 0.09 + ((10 + row)  * blocksHeight);
      }
      for (int i = 0; i < resList.size(); i++) {
         resList.get(i).initializeHitsCounter(1);
      }
      return resList;
   }

   /**
    * Returns the number of blocks that must be removed to clear the level.
    *
    * @return number of blocks to remove
    */
   @Override
   public int numberOfBlocksToRemove() {
      return numberOfBlocksToRemove;
   }

   /**
    * Returns the current list of game blocks.
    *
    * @return list of game blocks
    */
   @Override
   public List<Block> getGameBlocksList() {
      return blocks;
   }

   /**
    * Returns the list of frame blocks used in this level.
    *
    * @return list of frame blocks
    */
   @Override
   public List<Block> initialFrameBlocks() {
      return FrameBlocksTypes.getRegularFrameBlocks(SpecialColors.MID_DARK_GRAY);
   }

   /**
    * Returns the list of pit blocks used in this level.
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
    * Returns the background music for this level.
    *
    * @return background music sound
    */
   @Override
   public float getBackgroundMusicVolume() {
      return backgroundMusicVolume;
   }

   /**
    * Returns whether all game blocks share the same sound or not.
    *
    * @return true if all game blocks use the same sound, false otherwise
    */
   @Override
   public boolean isSingleGameBlockSound() {
      return isSingleGameBlockSound;
   }
}
