import java.awt.Color;
import java.util.List;

/**
 * Class block.
 */
public class Block implements Collidable {

   private static final double EPSILON = 0.5;

   private Rectangle rectangle;
   private int hitCount;


   /**
    * @param p
    * @param width
    * @param height
    * @param name
    */
   public Block(Point p, int width, int height, String name, int cnt, Color c) {
      rectangle = new Rectangle(p, width, height, name, c);
      hitCount = cnt;
   }


   @Override
   public Rectangle getCollisionRectangle() {
      return rectangle;
   }

   @Override
   public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
      List<Line> sidesList = getCollisionRectangle().getSidesList();
      Line l = null;

      for (Line elem : sidesList) {
         if(Line.isPointOnSegment(elem.getStartPoint(), collisionPoint, elem.getEndPoint())) {
            l = elem;
            break;
         }
      }

      if(l.isVerticalLine())
         return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
      return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
   }

   @Override
   public void updateHitCount() {
      hitCount--;
   }

   @Override
   public int getHitCount() {
      return hitCount;
   }

   @Override
   public String toString() {
      return ConsoleColors.RED_UNDERLINED + "Rectangle\n" + ConsoleColors.RED + rectangle;

   }
}
