package gui.levels.infrastructure;

import gui.shapes.Point;
import sprites.backgrounds.infrastructure.Background;
import gui.motion.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import music.Sound;

import java.util.List;

/**
 * An interface that defines all necessary information required to construct and play a level.
 *
 * This includes the balls, paddle, blocks, sounds, background, and gameplay parameters.
 */
public interface LevelInformation {

   /**
    * Returns the number of balls in the level.
    *
    * @return the number of balls
    */
   int numberOfBalls();

   /**
    * Returns a list of the initial Ball objects.
    *
    * @return the list of balls
    */
   List<Ball> initialBalls();

   /**
    * Returns the initial velocities of the balls.
    * The size of this list should match numberOfBalls().
    *
    * @return the list of initial ball velocities
    */
   List<Velocity> initialBallVelocities();

   /**
    * Returns the speed of the paddle.
    *
    * @return the paddle speed
    */
   int paddleSpeed();

   /**
    * Returns the width of the paddle.
    *
    * @return the paddle width
    */
   int paddleWidth();

   /**
    * Returns the height of the paddle.
    *
    * @return the paddle height
    */
   int paddleHeight();

   /**
    * Returns the initial position of the paddle.
    *
    * @return the initial point of the paddle
    */
   Point paddleInitialPoint();

   /**
    * Returns the paddle object for this level.
    *
    * @return the paddle
    */
   Paddle getPaddle();


   /**
    * Returns the name of the level.
    * This name is displayed at the top of the screen.
    *
    * @return the level name
    */
   String levelName();

   /**
    * Returns the background of the level as a sprite.
    *
    * @return the background sprite
    */
   Background getBackground();

   /**
    * Returns the initial list of game blocks for this level.
    *
    * @return the list of blocks
    */
   List<Block> initialBlocks();

   /**
    * Returns the number of blocks that must be removed to clear the level.
    *
    * @return the number of blocks to remove
    */
   int numberOfBlocksToRemove();

   /**
    * Returns the list of all game blocks.
    *
    * @return the list of game blocks
    */
   List<Block> getGameBlocksList();

   /**
    * Returns the list of balls used in the level.
    *
    * @return the list of balls
    */
   List<Ball> getBallsList ();

   /**
    * Resets all balls in the level to their initial state.
    */
   void resetBalls();

   /**
    * Returns the initial list of frame blocks (borders).
    *
    * @return the list of frame blocks
    */
   List<Block> initialFrameBlocks();

   /**
    * Returns the initial list of pit blocks (bottom blockers).
    *
    * @return the list of pit blocks
    */
   List<Block> initialPitBlocks();

   /**
    * Returns the current list of frame blocks.
    *
    * @return the frame blocks
    */
   List<Block> getFrameBlocksList();

   /**
    * Returns the current list of pit blocks.
    *
    * @return the pit blocks
    */
   List<Block> getPitBlocksList();

   /**
    * Returns the list of velocities used in the level.
    *
    * @return the list of velocities
    */
   List<Velocity> getVelocityList();

   /**
    * Returns the sound that plays when the paddle is hit.
    *
    * @return the paddle hit sound
    */
   Sound getPaddleHitSound();

   /**
    * Returns the sound that plays when a frame block is hit.
    *
    * @return the frame block hit sound
    */
   Sound getFrameBlockHitSound();

   /**
    * Returns the sound that plays when a pit block is hit.
    *
    * @return the pit block hit sound
    */
   Sound getPitBlockHitSound();

   /**
    * Returns a list of sounds used when game blocks are hit.
    *
    * @return the list of game block hit sounds
    */
   List<Sound> getGameBlockHitSound();

   /**
    * Indicates whether a single sound should be used for all game blocks.
    *
    * @return true if all game blocks use the same sound, false otherwise
    */
   boolean isSingleGameBlockSound();

   /**
    * Returns the background music for the level.
    *
    * @return the background music sound
    */
   Sound getBackgroundMusic();

   /**
    * Returns the volume of the background music.
    *
    * @return the music volume (from 0 to 1)
    */
   float getBackgroundMusicVolume();
}
