package sprites;

import biuoop.DrawSurface;
import gui.motion.Velocity;
import gui.shapes.Point;
import gui.shapes.Rectangle;


/**
 *
 */
public interface Collidable {

   /**
    * @return - the "collision shape" of the object.
    */
   Rectangle getCollisionRectangle();

   /**
    * Notify the object that we collided with it at collisionPoint with a given velocity.
    * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
    * @param collisionPoint
    * @param currentVelocity
    * @return - the new velocity expected after the hit (based on the force the object inflicted on us)
    */
   Velocity hit(Point collisionPoint, Velocity currentVelocity);

   /**
    *
    */
   void updateHitCount();

   /**
    * @return
    */
   int getHitCount();

   /**
    * @param surface
    */
   public void drawOn(DrawSurface surface);

   /**
    * @return
    */
   public boolean isDisappearable();
}