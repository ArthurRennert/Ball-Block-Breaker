package collision;

/**
 *
 */
public interface HitListener {

   /**
    * @param beingHit
    * @param hitter
    */
   // This method is called whenever the beingHit object is hit.
   // The hitter parameter is the Ball that's doing the hitting.
   void hitEvent(sprites.Block beingHit, sprites.Ball hitter);
}