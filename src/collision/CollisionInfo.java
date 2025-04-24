package collision;

import gui.shapes.Point;
import utilities.ConsoleColors;

/**
 * A class that holds information about a collision.
 *
 * Contains the point at which the collision occurred and the collidable object that was hit.
 */
public class CollisionInfo {

   private Point collisionPoint;
   private Collidable collisionObject;

   /**
    * Creates a new CollisionInfo object.
    *
    * @param coP the point at which the collision occurred
    * @param collObj the object that was hit
    */
   public CollisionInfo(Point coP, Collidable collObj) {
      collisionPoint = coP;
      collisionObject = collObj;
   }

   /**
    * Returns the point at which the collision occurred.
    *
    * @return the collision point
    */
   public Point collisionPoint() {
      return collisionPoint;
   }

   /**
    * Returns the collidable object involved in the collision.
    *
    * @return the collidable object that was hit
    */
   public Collidable collisionObject() {
      return collisionObject;
   }

   /**
    * Returns a string representation of the collision information.
    *
    * @return a formatted string containing the collision point and object
    */
   @Override
   public String toString() {
      return ConsoleColors.BLUE_UNDERLINED + "\t\tCollision Point \t\t\n" + ConsoleColors.BLUE + collisionPoint
              + ConsoleColors.RED_UNDERLINED + "\n\n\n\t\t\t\t\tCollision Object\t\t\t\t\t\t\t\n"
              + ConsoleColors.RED + collisionObject
              + "\n\n" + ConsoleColors.RESET;
   }
}
