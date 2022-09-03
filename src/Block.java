import java.util.Iterator;
import java.util.List;

/**
 * Class block.
 */
public class Block implements Collidable {

   private static final double EPSILON = 0.5;

   private Rectangle rectangle;
   private boolean isVertical;
   private int cntToDis;


   /**
    * @param p
    * @param width
    * @param height
    * @param name
    */
   public Block(Point p, int width, int height, String name, boolean isVert, int cnt) {
      rectangle = new Rectangle(p, width, height, name);
      isVertical = isVert;
      cntToDis = cnt;
   }


   @Override
   public Rectangle getCollisionRectangle() {
      return rectangle;
   }

   @Override
   public Velocity hit(Point currentLocation, Point collisionPoint, Velocity currentVelocity) {
//      List<Line> rectSidesList = getCollisionRectangle().getSidesList();
//
//      Iterator<Line> iterator = rectSidesList.iterator();
//      Line l = null;
//      while (iterator.hasNext()) {
//         l = iterator.next();
//         if (collisionPoint.getX() < l.getXOfStartPoint() || collisionPoint.getX() > l.getXOfEndPoint()
//                 || Math.abs(collisionPoint.getY() - l.getYOfEndPoint()) > EPSILON)
//            iterator.remove();
//      }
//
//      System.out.println("SIZE: " + rectSidesList.size());
//
//      l = rectSidesList.get(0);
//
//      System.out.println("Curr Loc: " + currentLocation + " Coll Loc: " + collisionPoint);
//      if(currentLocation.distance(collisionPoint) < 25) {
//         if (l.isHorizontalLine())


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

//      }
//      return null;
   }

   @Override
   public void decCntToDis() {
      cntToDis--;
   }

   @Override
   public int getCntToDis() {
      return cntToDis;
   }

   @Override
   public String toString() {
      return ConsoleColors.RED_UNDERLINED + "Rectangle\n" + ConsoleColors.RED + rectangle;

   }
}
