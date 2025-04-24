package collision.listeners.hit_listeners;

import gui.animations.GameLevel;
import collision.listeners.hit_listeners.infrastructure.HitListener;
import utilities.Counter;
import sprites.Block;
import sprites.Ball;

/**
 * A HitListener that is responsible for removing blocks from the game when they are hit.
 *
 * Also updates the counter that tracks the number of remaining blocks in the level.
 */
public class BlockRemover implements HitListener {
   private GameLevel gameLevel;
   private Counter numOfBlocksToRemove;

   /**
    * Creates a new BlockRemover.
    *
    * @param g the game level from which blocks will be removed
    * @param numOfBlocksToRemove the counter tracking how many blocks remain in the level
    */
   public BlockRemover(GameLevel g, Counter numOfBlocksToRemove) {
      gameLevel = g;
      this.numOfBlocksToRemove = numOfBlocksToRemove;
   }

   /**
    * Called when the specified block is hit.
    * If the block has no remaining hits, it is removed from the game,
    * this listener is removed from it, and the counter is decreased.
    *
    * @param beingHit the block that was hit
    * @param hitter the ball that hit the block
    */
   public void hitEvent(Block beingHit, Ball hitter) {
      beingHit.markHit();
      if (beingHit.hitsLeft() == 0) {
         beingHit.removeHitListener(this);
         beingHit.removeFromGame(gameLevel);
         numOfBlocksToRemove.decrease(1);
      }
   }
}
