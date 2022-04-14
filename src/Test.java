import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
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
      Rectangle r = new Rectangle(new Point(60, 60), 100, 100);
      Line l = new Line(new Point(20, 20), new Point(200, 180));
      d.setColor(Color.GREEN);
      d.fillCircle((int) l.getStartPoint().getX(), (int) l.getStartPoint().getY(), 6);

      d.setColor(Color.BLACK);
      d.drawLine((int) r.getUpperLeft().getX(), (int) r.getUpperLeft().getY(),
              (int) r.getLowerLeft().getX(), (int) r.getLowerLeft().getY());     //left edge
      d.drawLine((int) r.getUpperRight().getX(), (int) r.getUpperRight().getY(),
              (int) r.getLowerRight().getX(), (int) r.getLowerRight().getY());   //right edge
      d.drawLine((int) r.getUpperLeft().getX(), (int) r.getUpperLeft().getY(),
              (int) r.getUpperRight().getX(), (int) r.getUpperRight().getY());   //upper edge
      d.drawLine((int) r.getLowerLeft().getX(), (int) r.getLowerLeft().getY(),
              (int) r.getLowerRight().getX(), (int) r.getLowerRight().getY());   //lower edge

      d.setColor(Color.BLUE);
      d.drawLine((int) l.getStartPoint().getX(), (int) l.getStartPoint().getY(),
              (int) l.getEndPoint().getX(), (int) l.getEndPoint().getY());

      d.setColor(Color.RED);
      Point intersectionPoint = l.closestIntersectionToStartOfLine(r);
      if (intersectionPoint != null) {
         d.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), 3);
      }
      System.out.println(l.closestIntersectionToStartOfLine(r));

//      List<Point> listOfIntersectionPoints = r.intersectionPoints(l);
//
//      for (Point elem : listOfIntersectionPoints) {
//         d.fillCircle((int) elem.getX(), (int) elem.getY(), 3);
//      }




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
