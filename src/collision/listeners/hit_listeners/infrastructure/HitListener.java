package collision.listeners.hit_listeners.infrastructure;

/**
 * An interface for objects that should be notified when a block is hit.
 *
 * Classes that implement this interface can perform actions when a hit occurs,
 * such as updating scores, playing a sound, or removing the block.
 */
public interface HitListener {

   /**
    * This method is called whenever the beingHit block is hit by a ball.
    *
    * @param beingHit the block that was hit
    * @param hitter the ball that hit the block
    */
   void hitEvent(sprites.Block beingHit, sprites.Ball hitter);
}