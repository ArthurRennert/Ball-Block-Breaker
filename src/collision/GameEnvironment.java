package collision;

import gui.shapes.Line;
import gui.shapes.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * The GameEnvironment class holds a collection of collidable objects.
 *
 * It is responsible for managing collisions by checking the trajectory of a moving object
 * and determining whether and where it will collide with one of the collidable objects.
 */
public class GameEnvironment {

   private List<Collidable> listOfCollidableObjects;

   /**
    * Creates a new GameEnvironment.
    *
    * @param frameWidth the width of the game frame (not used directly here)
    * @param frameHeight the height of the game frame (not used directly here)
    */
   public GameEnvironment(int frameWidth, int frameHeight) {
      listOfCollidableObjects = new ArrayList<>();
   }

   /**
    * Adds the given collidable to the environment.
    *
    * @param c the collidable object to add
    */
   public void addCollidable(Collidable c) {
      listOfCollidableObjects.add(c);
   }


   /**
    * Removes the given collidable from the environment.
    *
    * @param c the collidable object to remove
    */
   public void removeCollidable(Collidable c) {
      listOfCollidableObjects.remove(c);
   }

   /**
    * Returns the list of collidable objects in the environment.
    *
    * @return the list of collidable objects
    */
   public List<Collidable> getListOfCollidableObjects() {
      return listOfCollidableObjects;
   }


   /**
    * Determines the closest collision that will occur if an object moves along the given trajectory.
    *
    * If no collision is detected, returns null. Otherwise, returns a CollisionInfo object
    * describing the closest collision.
    *
    * @param trajectory the path along which the object is moving
    * @return information about the closest collision, or null if no collision occurs
    */
   public CollisionInfo getClosestCollision(Line trajectory) {
      double distance = Integer.MAX_VALUE, temp;
      Point resCollisionPoint = null;
      Collidable collObj = null;
      for (Collidable elem : listOfCollidableObjects) {
         Point tempCollisionPoint = trajectory.closestIntersectionToStartOfLine(elem.getCollisionRectangle());
         if (tempCollisionPoint != null) {
            temp = trajectory.getStartPoint().distance(tempCollisionPoint);
            if (temp < distance) {
               distance = temp;
               resCollisionPoint = tempCollisionPoint;
               collObj = elem;
            }
         }
      }
      return new CollisionInfo(resCollisionPoint, collObj);
   }
}
