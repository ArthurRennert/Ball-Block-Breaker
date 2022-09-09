package gui.levels;

import gui.Background;
import gui.motion.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

public interface LevelInformation {
   int numberOfBalls();

   List<Ball> initialBalls();
   // The initial velocity of each ball
   // Note that initialBallVelocities().size() == numberOfBalls()
   List<Velocity> initialBallVelocities();
   int paddleSpeed();
   int paddleWidth();
   // the level name will be displayed at the top of the screen.
   String levelName();
   // Returns a sprite with the background of the level
   Background getBackground();
   // The Blocks that make up this level, each block contains
   // its size, color and location.
   List<Block> initialBlocks();
   // Number of blocks that should be removed
   // before the level is considered to be "cleared".
   // This number should be <= blocks.size();
   int numberOfBlocksToRemove();
   List<Block> getBlocksList();
   List<Ball> getBallsList ();
   List<Block> initialFrameBlocks();
   List<Block> initialPitBlocks();
   List<Block> getFrameBlocksList();
   List<Block> getPitBlocksList();
   List<Velocity> getVelocityList();
}
