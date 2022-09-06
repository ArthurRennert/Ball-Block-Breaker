package gui;

import gui.levels.GameLevel;
import utilities.Counter;
import sprites.Block;
import sprites.Ball;

// a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements collision.HitListener {
   private GameLevel gameLevel;
   private Counter remainingBlocks;

   /**
    * @param g
    * @param removedBlocks
    */
   public BlockRemover(GameLevel g, Counter removedBlocks) {
      gameLevel = g;
      remainingBlocks = removedBlocks;
   }

   /**
    * @param beingHit
    * @param hitter
    */
   // Blocks that are hit should be removed
   // from the gameLevel. Remember to remove this listener from the block
   // that is being removed from the gameLevel.
   public void hitEvent(Block beingHit, Ball hitter) {
      beingHit.removeHitListener(this);
      beingHit.removeFromGame(gameLevel);
      remainingBlocks.decrease(1);
   }
}
