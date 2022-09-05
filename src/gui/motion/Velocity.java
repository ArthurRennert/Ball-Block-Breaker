package gui.motion;

import gui.shapes.Point;

/**
 *
 */
// gui.motion.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
   //instance variables
   private double dx, dy;

   /**
    * @param dx
    * @param dy
    */
   // constructor
   public Velocity(double dx, double dy) {
      this.dx = dx;
      this.dy = dy;
   }

   /**
    * @param v
    */
   public Velocity(Velocity v) {
      dx = v.dx;
      dy = v.dy;
   }

   /**
    * @param angle
    * @param speed
    * @return - a new gui.motion.Velocity object representing the velocity in speed and angle.
    */
   public static Velocity fromAngleAndSpeed(double angle, double speed) {
      double angleInRadians = degreesToRadians(angle);

      double dx = speed * Math.sin(angleInRadians);
      double dy = speed * Math.cos(angleInRadians);

      return new Velocity(dx, dy);
   }

   /**
    * @param degrees
    * @return - the radians' representation of degrees param.
    */
   private static double degreesToRadians(double degrees) {
      return degrees * (Math.PI / 180);
   }


   public int getSpeed() {
      return (int) (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
   }

   /**
    * @param p
    * @return - a new Point object with its gui.motion.Velocity added to it.
    */
   // Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
   public Point applyToPoint(Point p) {
      return new Point(p.getX() + dx, p.getY() + dy);
   }

   /**
    * @return - the delta x value.
    */
   public double getDx() {
      return dx;
   }

   /**
    * @return - the delta y value.
    */
   public double getDy() {
      return dy;
   }

   /**
    * @param dx
    */
   public void setDx(double dx) {
      this.dx = dx;
   }

   /**
    * @param dy
    */
   public void setDy(double dy) {
      this.dy = dy;
   }

   /**
    * @return
    */
   @Override
   public String toString() {
      return "dx: " + dx + " dy: " + dy;
   }
}