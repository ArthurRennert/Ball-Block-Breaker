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
      addCollidable(new Block(new Point(0, 0), frameWidth, (int) (frameHeight * 0.05))); //upper screen block
      addCollidable(new Block(new Point(0, frameHeight * 0.95), frameWidth, (int) (frameHeight * 0.05))); //lower screen block
      addCollidable(new Block(new Point(0, frameHeight * 0.05), (int) (frameWidth * 0.05), (int) (frameHeight * 0.85))); //left screen block
      addCollidable(new Block(new Point(frameWidth * 0.95, frameHeight * 0.05), (int) (frameWidth * 0.05), (int) (frameHeight * 0.85))); //right screen block
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
      return null;
   }
}
