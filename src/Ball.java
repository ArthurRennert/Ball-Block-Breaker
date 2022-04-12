import biuoop.DrawSurface;

import java.awt.Color;

/**
 *
 */
public class Ball {
   //instance variables
   private Point point;
   private int radius;
   private Color color;
   private Velocity velocity;


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
}