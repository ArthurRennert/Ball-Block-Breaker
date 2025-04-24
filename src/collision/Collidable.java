package collision;

import biuoop.DrawSurface;
import gui.motion.Velocity;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import sprites.Ball;


/**
 * An interface for objects that can be collided with.
 *
 * Collidable objects define their collision shape and behavior upon impact,
 * as well as how they should be drawn on the screen.
 */
public interface Collidable {

   /**
    * Returns the collision shape of the object.
    *
    * @return the rectangle representing the shape of the collidable object
    */
   Rectangle getCollisionRectangle();

   /**
    * Notifies the object that a collision occurred at the given point with the given velocity.
    * Returns the new velocity after the collision.
    *
    * @param hitter the ball that hit the object
    * @param collisionPoint the point where the collision occurred
    * @param currentVelocity the velocity of the ball before the collision
    * @return the new velocity after the collision
    */
   Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

   /**
    * Draws the object on the given surface.
    *
    * @param surface the surface on which to draw the object
    */
   void drawOn(DrawSurface surface);
}
