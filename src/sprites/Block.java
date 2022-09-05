package sprites;

import biuoop.DrawSurface;
import gui.motion.Velocity;
import gui.shapes.Line;
import gui.shapes.Point;
import gui.shapes.Rectangle;

import java.awt.Color;
import java.util.List;

/**
 * Class block.
 */
public class Block implements Collidable, Sprite {

   private static final double EPSILON = 0.5;

   private Rectangle rectangle;
   private int hitCount;
   private boolean disappearable;


   /**
    * @param p
    * @param width
    * @param height
    * @param name
    */
   public Block(Point p, int width, int height, String name, int cnt, Color c, boolean dis) {
      rectangle = new Rectangle(p, width, height, name, c);
      hitCount = cnt;
      disappearable = dis;
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

   /**
    * @param surface
    */
   public void drawOn(DrawSurface surface) {
      surface.setColor(rectangle.getColor());
      surface.fillRectangle((int) rectangle.getXUpperLeftCoordinate(),
              (int) rectangle.getYUpperLeftCoordinate(),
              (int) rectangle.getWidth(),
              (int) rectangle.getHeight());
      surface.setColor(Color.BLACK);
      surface.drawLine((int) rectangle.getXUpperLeftCoordinate(), (int) rectangle.getYUpperLeftCoordinate(), (int) rectangle.getXUpperRightCoordinate(), (int) rectangle.getYUpperRightCoordinate());
      surface.drawLine((int) rectangle.getXUpperLeftCoordinate(), (int) rectangle.getYUpperLeftCoordinate(), (int) rectangle.getXLowerLeftCoordinate(), (int) rectangle.getYLowerLeftCoordinate());
      surface.drawLine((int) rectangle.getXLowerLeftCoordinate(), (int) rectangle.getYLowerLeftCoordinate(), (int) rectangle.getXLowerRightCoordinate(), (int) rectangle.getYLowerRightCoordinate());
      surface.drawLine((int) rectangle.getXUpperRightCoordinate(), (int) rectangle.getYUpperRightCoordinate(), (int) rectangle.getXLowerRightCoordinate(), (int) rectangle.getYLowerRightCoordinate());
   }

   /**
    * @return
    */
   public boolean isDisappearable() {
      return disappearable;
   }

   /**
    * @param g
    */
   public void addToGame(gui.Game g) {
      g.addSprite(this);
      g.addCollidable(this);
   }

   /**
    *
    */
   public void timePassed() {

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
      return utilities.ConsoleColors.RED_UNDERLINED + "Rectangle\n" + utilities.ConsoleColors.RED + rectangle;

   }
}
