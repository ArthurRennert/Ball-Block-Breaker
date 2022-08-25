import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class GameEnvironment {

   private List<Collidable> listOfCollidableObjects = new ArrayList<>();

   /**
    * @param frameWidth
    * @param frameHeight
    */
   public GameEnvironment(int frameWidth, int frameHeight) {
      addCollidable(new Block(new Point(0, 0), frameWidth, (int) (frameHeight * 0.05), "UpperScreen")); //upper screen block
//      addCollidable(new Block(new Point(250, 250), 5, 5)); //random block

      addCollidable(new Block(new Point(0, frameHeight * 0.95), frameWidth, (int) (frameHeight * 0.05), "LowerScreen")); //lower screen block
      addCollidable(new Block(new Point(0, frameHeight * 0.05), (int) (frameWidth * 0.05), (int) (frameHeight * 0.85), "LeftScreen")); //left screen block
      addCollidable(new Block(new Point(frameWidth * 0.95, frameHeight * 0.05), (int) (frameWidth * 0.05), (int) (frameHeight * 0.85), "RightScreen")); //right screen block
   }

   /**
    * Add the given collidable to the environment.
    * @param c
    */
   public void addCollidable(Collidable c) {
      listOfCollidableObjects.add(c);
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
               System.out.println(ConsoleColors.GREEN + "distance: " + distance + "\n\n" + ConsoleColors.RESET);
               resCollisionPoint = tempCollisionPoint;
               collObj = elem;
            }
         }
      }
      return new CollisionInfo(resCollisionPoint, collObj);
   }
}
