import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


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
      addCollidable(new Block(new Point(0, 0), frameWidth, (int) (frameHeight * 0.05), "UpperScreen", Integer.MAX_VALUE, Color.GRAY)); //upper screen block
      addCollidable(new Block(new Point(150, 150), (int) (frameWidth * 0.15), (int) (frameHeight * 0.05), "random", 3, Color.DARK_GRAY)); //random block
      addCollidable(new Block(new Point(350, 350), (int) (frameWidth * 0.20), (int) (frameHeight * 0.08), "random", 2, Color.CYAN)); //random block
      addCollidable(new Block(new Point(550, 550), (int) (frameWidth * 0.25), (int) (frameHeight * 0.11), "random", 1, Color.RED)); //random block





      addCollidable(new Block(new Point(0, frameHeight * 0.95), frameWidth, (int) (frameHeight * 0.05), "LowerScreen", Integer.MAX_VALUE, Color.GRAY)); //lower screen block
      addCollidable(new Block(new Point(0, frameHeight * 0.05), (int) (frameWidth * 0.05), (int) (frameHeight * 0.90), "LeftScreen", Integer.MAX_VALUE, Color.GRAY)); //left screen block
      addCollidable(new Block(new Point(frameWidth * 0.95, frameHeight * 0.05), (int) (frameWidth * 0.05), (int) (frameHeight * 0.90), "RightScreen", Integer.MAX_VALUE, Color.GRAY)); //right screen block
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

   public void updateCollision(Collidable coll) {
      int ind = listOfCollidableObjects.indexOf(coll);
      listOfCollidableObjects.get(ind).updateHitCount();
      int countToDis = listOfCollidableObjects.get(ind).getHitCount();
      if(countToDis == 0) {
         listOfCollidableObjects.remove(ind);
      }
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
//      stepsToNextColl = (int) ((trajectory.getStartPoint().distance(resCollisionPoint)
//                        / trajectory.getStartPoint().distance(new Point(ball.getX()
//                           + ball.getVelocity().getDx(), ball.getY() + ball.getVelocity().getDy()))) - 1);
//      System.out.println("stepsToNextColl: " + stepsToNextColl);
      return new CollisionInfo(resCollisionPoint, collObj);
   }
}
