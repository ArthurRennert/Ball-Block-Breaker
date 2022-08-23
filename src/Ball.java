import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 *
 */
public class Ball {
   //instance variables
   private Point point;
   private GameEnvironment ge;


   /**
    * @param center
    * @param r
    * @param color
    */
   public Ball(Point center, int r, Color color) {
      point = new Point(center);
      radius = r;
      this.color = new Color(color.getRGB());
      velocity = new Velocity(0, 0);
   }

   /**
    * @param x
    * @param y
    * @param r
    * @param color
    */
   public Ball(double x, double y, int r, Color color) {
      point = new Point(x, y);
      radius = r;
      this.color = new Color(color.getRGB());
      velocity = new Velocity(0, 0);
   }


   /**
    * @param gameEnvironment
    */
   public void setGameEnvironment(GameEnvironment gameEnvironment) {
      ge = gameEnvironment;
   }

   /**
    * @return the radius of the Ball object.
    */
   public int getRadius() {
      return radius;
   }

   private int radius;
   private Color color;
   private Velocity velocity;

   /**
    * @return - the x coordinate of the center of the ball.
    */
   public int getX() {
      return (int) point.getX();
   }

   /**
    * @return - the y coordinate of the center of the ball.
    */
   public int getY() {
      return (int) point.getY();
   }

   /**
    * @return - the volume of the ball.
    */
   public int getSize() {
      return (int) ((4 * Math.PI * Math.pow(radius, 3)) / 3);
   }

   /**
    * @return - the color of the ball.
    */
   public Color getColor() {
      return this.color;
   }


   /**
    * @param surface
    */
   public void drawOn(DrawSurface surface) {
      surface.setColor(this.color);
      surface.fillCircle((int) point.getX(), (int) point.getY(), radius);
   }

   /**
    * @param v
    */
   public void setVelocity(Velocity v) {
      velocity = new Velocity(v);
   }

   /**
    * @param dx
    * @param dy
    */
   public void setVelocity(double dx, double dy) {
      velocity.setDx(dx);
      velocity.setDy(dy);
   }

   /**
    * @return - the velocity of the ball.
    */
   public Velocity getVelocity() {
      return velocity;
   }

   /**
    * @param frameWidth
    * @param frameHeight
    */
   public void moveOneStep(int frameWidth, int frameHeight) {
      if (frameHeight <= point.getX() + radius || point.getX() <= radius) {
         velocity.setDx(-velocity.getDx());
      }

      if (frameWidth <= point.getY() + radius || point.getY() <= radius) {
         velocity.setDy(-velocity.getDy());
      }
      point = this.getVelocity().applyToPoint(point);
   }

   /**
    *
    */
   public void moveOneStep() {
      List<Collidable> collidableList = ge.getListOfCollidableObjects();
//      System.out.println(collidableList);

      for (Collidable elem : collidableList) {
         double minX = Math.min(elem.getCollisionRectangle().getRightSide().getStartPoint().getX(),
                 elem.getCollisionRectangle().getLeftSide().getStartPoint().getX());
         double maxX = Math.max(elem.getCollisionRectangle().getRightSide().getStartPoint().getX(),
                 elem.getCollisionRectangle().getLeftSide().getStartPoint().getX());
         double minY = Math.min(elem.getCollisionRectangle().getUpperSide().getStartPoint().getY(),
                 elem.getCollisionRectangle().getBottomSide().getStartPoint().getY());
         double maxY = Math.max(elem.getCollisionRectangle().getUpperSide().getStartPoint().getY(),
                 elem.getCollisionRectangle().getBottomSide().getStartPoint().getY());

         if (minX <= point.getX() + velocity.getDx()
                 && point.getX() + velocity.getDx() <= maxX) {
            velocity.setDx(-velocity.getDx());
         }

         if (minY <= point.getY() + velocity.getDy()
                 && point.getY() + velocity.getDy() <= maxY
                 && minX <= point.getX() && point.getX() <= maxX) {
            velocity.setDy(-velocity.getDy());
         }
      }
      point = this.getVelocity().applyToPoint(point);
   }

   /**
    * @param fromWidth
    * @param fromHeight
    * @param frameWidth
    * @param frameHeight
    */
   public void moveOneStep(int fromWidth, int fromHeight, int frameWidth, int frameHeight) {
      if (frameWidth <= point.getX() + radius + velocity.getDx()
              || point.getX() <= radius + fromWidth - velocity.getDx()) {
         velocity.setDx(-velocity.getDx());
//         System.out.println(point.getX());
      }

      if (frameHeight < point.getY() + radius + velocity.getDy()
              || point.getY() < radius + fromHeight - velocity.getDy()) {
         velocity.setDy(-velocity.getDy());
//         System.out.println(point.getY());
      }
      point = this.getVelocity().applyToPoint(point);
   }
}