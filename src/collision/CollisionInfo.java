package collision;

import gui.shapes.Point;
import utilities.ConsoleColors;

/**
 *
 */
public class CollisionInfo {

   private Point collisionPoint;
   private Collidable collisionObject;

   /**
    * @param coP
    * @param collObj
    */
   public CollisionInfo(Point coP, Collidable collObj) {
      collisionPoint = coP;
      collisionObject = collObj;
   }

   /**
    * @return - the point at which the collision occurs.
    */
   public Point collisionPoint() {
      return collisionPoint;
   }

   /**
    * @return - the collidable object involved in the collision.
    */
   public Collidable collisionObject() {
      return collisionObject;
   }

   /**
    * @return - a String representing the collision information.
    */
   @Override
   public String toString() {
      return ConsoleColors.BLUE_UNDERLINED + "\t\tCollision Point \t\t\n" + ConsoleColors.BLUE + collisionPoint
              + ConsoleColors.RED_UNDERLINED + "\n\n\n\t\t\t\t\tCollision Object\t\t\t\t\t\t\t\n"
              + ConsoleColors.RED + collisionObject
              + "\n\n" + ConsoleColors.RESET;
   }
}
