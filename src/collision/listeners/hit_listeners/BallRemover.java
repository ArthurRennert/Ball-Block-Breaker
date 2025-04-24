package collision.listeners.hit_listeners;

import collision.listeners.hit_listeners.infrastructure.HitListener;
import gui.animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import utilities.Counter;

/**
 * A HitListener that removes balls from the game when they hit a specific block.
 *
 * If no balls remain after the removal, one life is deducted and the game level is restarted.
 */
public class BallRemover implements HitListener {
   private GameLevel gameLevel;
   private Counter remainingBalls;
   private Counter lives;


   /**
    * Creates a new BallRemover.
    *
    * @param g the game level from which balls will be removed
    * @param c the counter tracking the remaining balls
    * @param lives the counter tracking the remaining player lives
    */
   public BallRemover(GameLevel g, Counter c, Counter lives) {
      gameLevel = g;
      remainingBalls = c;
      this.lives = lives;
   }

   /**
    * Called when the specified block is hit.
    * Removes the ball from the game and updates the counters.
    * If there are no balls left, a life is lost and the level is restarted.
    *
    * @param beingHit the block that was hit
    * @param hitter the ball that hit the block
    */
   @Override
   public void hitEvent(Block beingHit, Ball hitter) {
//      SoundMaker.playMusic(SoundMaker.pitBlockHit.getSound());
      hitter.removeFromGame(gameLevel);
      hitter.unsetGameEnvironment();
      remainingBalls.decrease(1);
      if (remainingBalls.getValue() == 0) {
         lives.decrease(1);
         gameLevel.restartAfterLiveLost();
      }
   }
}
