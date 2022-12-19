package collision.listeners.hit_listeners;

import gui.animations.GameLevel;
import collision.listeners.hit_listeners.infrastructure.HitListener;
import utilities.Counter;
import sprites.Block;
import sprites.Ball;

/**
 *
 */
// a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
   private GameLevel gameLevel;
   private Counter numOfBlocksToRemove;

   /**
    * @param g
    * @param numOfBlocksToRemove
    */
   public BlockRemover(GameLevel g, Counter numOfBlocksToRemove) {
      gameLevel = g;
      this.numOfBlocksToRemove = numOfBlocksToRemove;
   }

   /**
    * @param beingHit
    * @param hitter
    */
   // Blocks that are hit should be removed
   // from the gameLevel. Remember to remove this listener from the block
   // that is being removed from the gameLevel.
   public void hitEvent(Block beingHit, Ball hitter) {
      beingHit.markHit();
      if (beingHit.hitsLeft() == 0) {
         beingHit.removeHitListener(this);
         beingHit.removeFromGame(gameLevel);
         numOfBlocksToRemove.decrease(1);
      }
   }
}
