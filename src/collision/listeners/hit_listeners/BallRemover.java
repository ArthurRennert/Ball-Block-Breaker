package collision.listeners.hit_listeners;

import collision.listeners.hit_listeners.infrastructure.HitListener;
import gui.animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import utilities.Counter;

/**
 *
 */
public class BallRemover implements HitListener {
   private GameLevel gameLevel;
   private Counter remainingBalls;
   private Counter lives;


   /**
    * @param g
    * @param c
    * @param lives
    */
   public BallRemover(GameLevel g, Counter c, Counter lives) {
      gameLevel = g;
      remainingBalls = c;
      this.lives = lives;
   }
   /**
    * @param beingHit
    * @param hitter
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
