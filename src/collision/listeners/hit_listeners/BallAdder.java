package collision.listeners.hit_listeners;

import collision.listeners.hit_listeners.infrastructure.HitListener;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import gui.shapes.Point;
import sprites.Ball;
import sprites.Block;
import utilities.Counter;

/**
 * A HitListener that adds a new ball to the game whenever a block is hit.
 *
 * This class creates a new ball at a fixed position and adds it to the game,
 * also updating the counter that tracks the number of balls.
 */
public class BallAdder implements HitListener {
   private GameLevel gameLevel;
   private Counter remainingBalls;

   /**
    * Creates a new BallAdder.
    *
    * @param g the game level where the new ball will be added
    * @param c the counter that keeps track of the number of balls
    */
   public BallAdder(GameLevel g, Counter c) {
      gameLevel = g;
      remainingBalls = c;
   }

   /**
    * Called when the specified block is hit.
    * A new ball is created and added to the game, and the counter is increased.
    *
    * @param beingHit the block that was hit
    * @param hitter the ball that hit the block
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
