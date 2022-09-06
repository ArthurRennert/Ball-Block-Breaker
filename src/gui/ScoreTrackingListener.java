package gui;

import collision.HitListener;
import sprites.Ball;
import sprites.Block;
import utilities.Counter;

/**
 *
 */
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;

   /**
    * @param scoreCounter
    */
   public ScoreTrackingListener(Counter scoreCounter) {
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