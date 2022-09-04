import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Test {
   /**
    * @param args
    */
   public static void main(String[] args) {

      drawRectangles();

   }

   /**
    *
    */
   public static void drawRectangles() {

      GUI gui = new GUI("Random Lines", 400, 300);
      DrawSurface d = gui.getDrawSurface();
      Rectangle r = new Rectangle(new Point(60, 60), 100, 100, "TestRectangle", Color.GRAY);
      Line l = new Line(new Point(20, 20), new Point(180, 190));
      //Line l2 = new Line(new Point(20, 60), new Point(20, 180));
      d.setColor(Color.GREEN);
      d.fillCircle((int) l.getStartPoint().getX(), (int) l.getStartPoint().getY(), 6);

      d.setColor(Color.BLACK);
      d.drawLine((int) r.getXUpperLeftCoordinate(), (int) r.getYUpperLeftCoordinate(),
              (int) r.getXLowerLeftCoordinate(), (int) r.getYLowerLeftCoordinate());     //left side
      d.drawLine((int) r.getXUpperRightCoordinate(), (int) r.getYUpperRightCoordinate(),
              (int) r.getXLowerRightCoordinate(), (int) r.getYLowerRightCoordinate());   //right side
      d.drawLine((int) r.getXUpperLeftCoordinate(), (int) r.getYUpperLeftCoordinate(),
              (int) r.getXUpperRightCoordinate(), (int) r.getYUpperRightCoordinate());   //upper side
      d.drawLine((int) r.getXLowerLeftCoordinate(), (int) r.getYLowerLeftCoordinate(),
              (int) r.getXLowerRightCoordinate(), (int) r.getYLowerRightCoordinate());   //lower side

      d.setColor(Color.BLUE);
      d.drawLine((int) l.getXOfStartPoint(), (int) l.getYOfStartPoint(),
              (int) l.getXOfEndPoint(), (int) l.getYOfEndPoint());
      //d.drawLine((int) l2.getXOfStartPoint(), (int) l2.getYOfStartPoint(),
      //        (int) l2.getXOfEndPoint(), (int) l2.getYOfEndPoint());

      d.setColor(Color.RED);
//      Point intersectionPoint = l.closestIntersectionToStartOfLine(r);
//      if (intersectionPoint != null) {
//         d.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), 3);
//      }
//      System.out.println(l.closestIntersectionToStartOfLine(r));

      //System.out.println(l.isIntersecting(l2));
      //System.out.println(l.intersectionWith(l2));
      List<Point> listOfIntersectionPoints = r.intersectionPoints(l);

      for (Point elem : listOfIntersectionPoints) {
         d.fillCircle((int) elem.getX(), (int) elem.getY(), 3);
      }

      Point closestPoint = l.closestIntersectionToStartOfLine(r);
      d.setColor(Color.YELLOW);
      d.fillCircle((int) closestPoint.getX(), (int) closestPoint.getY(), 10);

//         for (int j = i - 1; j >= 0; j--) {
//            if (lines.get(i).isIntersecting(lines.get(j))) {
//               Point intersectionPoint = lines.get(i).intersectionWith(lines.get(j));
//               d.setColor(Color.RED);
//               d.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), 3);
//            }
//         }

      gui.show(d);
   }
}
