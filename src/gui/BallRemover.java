package gui;

import collision.HitListener;
import gui.levels.GameLevel;
import sprites.Ball;
import sprites.Block;
import utilities.Counter;

/**
 *
 */
public class BallRemover implements HitListener {
   private GameLevel gameLevel;
   private Counter remainingBalls;

   /**
    * @param g
    * @param c
    */
   public BallRemover(GameLevel g, Counter c) {
      gameLevel = g;
      remainingBalls = c;
   }
   /**
    * @param beingHit
    * @param hitter
    */
   @Override
   public void hitEvent(Block beingHit, Ball hitter) {
      hitter.removeFromGame(gameLevel);
      remainingBalls.decrease(1);
   }
}
