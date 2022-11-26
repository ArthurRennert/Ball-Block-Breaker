package collision.listeners.hit_listeners;

import collision.listeners.hit_listeners.infrastructure.HitListener;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import gui.shapes.Point;
import sprites.Ball;
import sprites.Block;
import utilities.Counter;

/**
 *
 */
public class BallAdder implements HitListener {
   private GameLevel gameLevel;
   private Counter remainingBalls;

   /**
    * @param g
    * @param c
    */
   public BallAdder(GameLevel g, Counter c) {
      gameLevel = g;
      remainingBalls = c;
   }

   /**
    * @param beingHit
    * @param hitter
    */
   @Override
   public void hitEvent(Block beingHit, Ball hitter) {
      Ball b = new Ball(hitter);
      b.setPoint(new Point(ScreenSettings.FRAME_WIDTH / 4, ScreenSettings.FRAME_HEIGHT / 4));
      b.setGameEnvironment(hitter.getGameEnvironment());
      b.addToGame(gameLevel);
      remainingBalls.increase(1);
   }
}
