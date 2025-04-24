package gui.motion;

import gui.shapes.Point;

/**
 * The Velocity class represents a 2D vector that defines the change in position
 * on the X and Y axes.
 */
public class Velocity {
   private double dx, dy;

   /**
    * Constructs a Velocity object with the specified change in x and y.
    *
    * @param dx change in x-axis
    * @param dy change in y-axis
    */
   public Velocity(double dx, double dy) {
      this.dx = dx;
      this.dy = dy;
   }

   /**
    * Copy constructor for Velocity.
    *
    * @param v the velocity to copy
    */
   public Velocity(Velocity v) {
      dx = v.dx;
      dy = v.dy;
   }

   /**
    * Creates a new Velocity object from an angle and speed.
    *
    * @param angle the angle in degrees
    * @param speed the speed (magnitude)
    * @return a new Velocity object representing the velocity as (dx, dy)
    */
   public static Velocity fromAngleAndSpeed(double angle, double speed) {
      double angleInRadians = degreesToRadians(angle);

      double dx = speed * Math.sin(angleInRadians);
      double dy = speed * Math.cos(angleInRadians);

      return new Velocity(dx, dy);
   }

   /**
    * Converts degrees to radians.
    *
    * @param degrees the angle in degrees
    * @return the angle in radians
    */
   private static double degreesToRadians(double degrees) {
      return degrees * (Math.PI / 180);
   }


   /**
    * Returns the speed (magnitude) of the velocity.
    *
    * @return speed as an integer
    */
   public int getSpeed() {
      return (int) (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
   }

   /**
    * Applies the velocity to a given point.
    *
    * @param p the original point
    * @return a new Point with velocity applied (x + dx, y + dy)
    */
   public Point applyToPoint(Point p) {
      return new Point(p.getX() + dx, p.getY() + dy);
   }

   /**
    * Returns the dx component of the velocity.
    *
    * @return delta x
    */
   public double getDx() {
      return dx;
   }

   /**
    * Returns the dy component of the velocity.
    *
    * @return delta y
    */
   public double getDy() {
      return dy;
   }

   /**
    * Sets the dx component of the velocity.
    *
    * @param dx the new dx value
    */
   public void setDx(double dx) {
      this.dx = dx;
   }

   /**
    * Sets the dy component of the velocity.
    *
    * @param dy the new dy value
    */
   public void setDy(double dy) {
      this.dy = dy;
   }

   /**
    * Returns a string representation of the velocity.
    *
    * @return a string in the format "dx: [value] dy: [value]"
    */
   @Override
   public String toString() {
      return "dx: " + dx + " dy: " + dy;
   }
}