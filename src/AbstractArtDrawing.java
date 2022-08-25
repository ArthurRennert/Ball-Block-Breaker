import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;


/**
 *
 */
public class AbstractArtDrawing {
   private static ArrayList<Line> lines = new ArrayList<>();

   /**
    *
    */
   public void drawRandomLines() {
      GUI gui = new GUI("Random Lines", 400, 400);
      DrawSurface d = gui.getDrawSurface();
      for (int i = 0; i < 10; ++i) {
         lines.add(Line.generateRandomLine());
         d.setColor(Color.BLACK);
         d.drawLine((int) lines.get(i).getXOfStartPoint(), (int) lines.get(i).getYOfStartPoint(),
                 (int) lines.get(i).getXOfEndPoint(), (int) lines.get(i).getYOfEndPoint());
         Point middle = lines.get(i).middle();
         d.setColor(Color.BLUE);
         d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);

         for (int j = i - 1; j >= 0; j--) {
            if (lines.get(i).isIntersecting(lines.get(j))) {
               Point intersectionPoint = lines.get(i).intersectionWith(lines.get(j));
               d.setColor(Color.RED);
               d.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), 3);
            }
         }
      }
      gui.show(d);
   }

   /**
    * @param args
    */
   public static void main(String[] args) {
      AbstractArtDrawing example = new AbstractArtDrawing();
      example.drawRandomLines();
   }
}
