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
    *
    */
   public Friends() {
      numberOfBalls = 3;
      numberOfBlocksToRemove = 300;
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
      paddleInitialPoint = new Point((ScreenSettings.FRAME_WIDTH - paddleWidth) / 2, ScreenSettings.FRAME_HEIGHT * 0.95);
      paddle = new Paddle(paddleInitialPoint, paddleWidth, paddleHeight, paddleSpeed);
      paddleHit = new Sound("/Clouds/Paddle-Hit.wav");
      frameBlockHit = new Sound("/Friends/Frame-Block.wav");
      pitBlockHit = new Sound("/Friends/Pit-Block.wav");
      gameBlockHit = new ArrayList<>();
      initializeAllGameBlockHitSounds();
      isSingleGameBlockSound = false;
      backgroundMusic = new Sound("/Friends/Background.wav");
      backgroundMusicVolume = 0.4f;
   }

   private void initializeAllGameBlockHitSounds() {
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block1.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block2.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block3.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block4.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block5.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block6.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block7.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block8.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block9.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block10.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block11.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block12.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block13.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block14.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block15.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block16.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block17.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block18.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block10.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block20.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block21.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block22.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block23.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block24.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block25.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block26.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block27.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block28.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block29.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block30.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block31.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block32.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block33.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block34.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block35.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block36.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block37.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block38.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block39.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block40.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block41.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block42.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block43.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block44.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block45.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block46.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block47.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block48.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block49.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block50.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block51.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block52.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block53.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block54.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block55.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block56.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block57.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block58.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block59.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block60.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block61.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block62.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block63.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block64.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block65.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block66.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block67.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block68.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block69.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block70.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block71.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block72.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block73.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block74.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block75.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block76.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block77.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block78.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block79.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block81.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block82.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block83.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block84.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block85.wav"));
      gameBlockHit.add(new Sound("/Friends/Game-Block/Game-Block86.wav"));
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
      for (int i = 0; i < numberOfBalls; i++) {
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
      Color[] colors = {new Color(Color.GRAY.getRGB()), new Color(Color.RED.getRGB()), new Color(Color.YELLOW.getRGB()), new Color(Color.GREEN.getRGB()),
              new Color(Color.WHITE.getRGB()), new Color(Color.PINK.getRGB()), new Color(Color.CYAN.getRGB())};
      double blocksWidth = (ScreenSettings.FRAME_WIDTH * 0.97 - ScreenSettings.FRAME_WIDTH * 0.03) / 15;
      double blocksHeight = 30;
      double xStart = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
      double yStart = ScreenSettings.FRAME_HEIGHT * 0.09;
      for (int row = 1; row <= 20; row++) {
         for (int col = 0; col < 15; col++) {
            resList.add(new Block(new Point(xStart, yStart),
                    blocksWidth, blocksHeight, "GameBlock", colors[(int) (Math.random() * colors.length)]));
            if (row == 1) {
               resList.get(resList.size() - 1).initializeHitsCounter(2);
            } else {
               resList.get(resList.size() - 1).initializeHitsCounter(1);
            }
            xStart -= blocksWidth;
         }
         xStart = ScreenSettings.FRAME_WIDTH * 0.97 - blocksWidth;
         yStart = ScreenSettings.FRAME_HEIGHT * 0.09 + (row  * blocksHeight);
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
