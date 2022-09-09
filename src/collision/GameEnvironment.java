package collision;

import gui.shapes.Line;
import gui.shapes.Point;
import sprites.Collidable;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 */
public class GameEnvironment {

   private List<Collidable> listOfCollidableObjects;

   /**
    * @param frameWidth
    * @param frameHeight
    */
   public GameEnvironment(int frameWidth, int frameHeight) {
      listOfCollidableObjects = new ArrayList<>();
   }

   /**
    * Add the given collidable to the environment.
    * @param c
    */
   public void addCollidable(Collidable c) {
      listOfCollidableObjects.add(c);
   }


   /**
    * @param c
    */
   public void removeCollidable(Collidable c) {
//      Iterator<Collidable> it = listOfCollidableObjects.iterator();
//      while (it.hasNext()) {
//         Collidable coll = it.next();
//         if (coll.equals(c)) {
//            it.remove();
//         }
//      }
      listOfCollidableObjects.remove(c);
   }

   /**
    * @return - the list of collidable objects.
    */
   public List<Collidable> getListOfCollidableObjects() {
      return listOfCollidableObjects;
   }


   /**
    * Assume an object moving from line.start() to line.end().
    * If this object will not collide with any of the collidables
    * in this collection, return null. Else, return the information
    * about the closest collision that is going to occur.
    * @param trajectory
    * @return - the information about the closest collision that is going to occur.
    *           If no collision going to occur, null is returned.
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
//               System.out.println(utilities.ConsoleColors.GREEN + "distance: "
//               + distance + "\n\n" + utilities.ConsoleColors.RESET);
               resCollisionPoint = tempCollisionPoint;
               collObj = elem;
            }
         }
      }
      return new CollisionInfo(resCollisionPoint, collObj);
   }
}
