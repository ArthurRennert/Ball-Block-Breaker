package collision.listeners.hit_listeners;

import collision.listeners.hit_listeners.infrastructure.HitListener;
import sprites.Ball;
import sprites.Block;
import utilities.Counter;

/**
 * A HitListener that updates the score when a block is destroyed.
 *
 * Points are added only if the block has no hits left after the collision.
 */
public class ScoreTracking implements HitListener {
   private Counter currentScore;

   /**
    * Creates a new ScoreTracking listener.
    *
    * @param scoreCounter the counter that tracks the current score
    */
   public ScoreTracking(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }

   /**
    * Called when the specified block is hit.
    * If the block has no remaining hits, its value is added to the score.
    *
    * @param beingHit the block that was hit
    * @param hitter the ball that hit the block
    */
   public void hitEvent(Block beingHit, Ball hitter) {
      if(beingHit.hitsLeft() == 0) {
         currentScore.increase(beingHit.getValue());
      }
   }
}