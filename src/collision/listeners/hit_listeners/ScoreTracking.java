package collision.listeners.hit_listeners;

import collision.listeners.hit_listeners.infrastructure.HitListener;
import sprites.Ball;
import sprites.Block;
import utilities.Counter;

/**
 *
 */
public class ScoreTracking implements HitListener {
   private Counter currentScore;

   /**
    * @param scoreCounter
    */
   public ScoreTracking(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }

   /**
    * @param beingHit
    * @param hitter
    */
   public void hitEvent(Block beingHit, Ball hitter) {
      currentScore.increase(5);
   }
}