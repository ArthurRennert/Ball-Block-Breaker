package gui.shapes;

/**
 * The Point class represents a point in 2D space with x and y coordinates.
 * It provides utility methods for distance calculation, equality checking,
 * and geometric orientation.
 */
public class Point {

   //instance variables
   private double x;
   private double y;

   /**
    * Constructs a point given x and y coordinates.
    *
    * @param x the x-coordinate
    * @param y the y-coordinate
    */
   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }

   /**
    * Copy constructor for Point.
    *
    * @param other the point to copy
    */
   public Point(Point other) {
      x = other.x;
      y = other.y;
   }

   /**
    * Calculates the Euclidean distance between this point and another.
    *
    * @param other the other point
    * @return the distance between the two points
    */
   public double distance(Point other) {
      return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
   }

   /**
    * Checks whether this point is equal to another.
    *
    * @param other the other point
    * @return true if the points have the same coordinates, false otherwise
    */
   public boolean equals(Point other) {
      if (other == null) {
         return false;
      }

      if (this.x == other.x && this.y == other.y) {
         return true;
      }
      return false;
   }

   /**
    * Returns the x-coordinate of the point.
    *
    * @return the x value
    */
   public double getX() {
      return this.x;
   }

   /**
    * Returns the y-coordinate of the point.
    *
    * @return the y value
    */
   public double getY() {
      return this.y;
   }

   /**
    * Sets the x-coordinate of the point.
    *
    * @param x the new x value
    */
   public void setX(double x) {
      this.x = x;
   }

   /**
    * Sets the y-coordinate of the point.
    *
    * @param x the new y value
    */
   public void setY(double x) {
      this.y = y;
   }

   /**
    * Determines the orientation of an ordered triplet (p, q, r).
    *
    * @param p the first point
    * @param q the second point
    * @param r the third point
    * @return 0 if collinear, 1 if clockwise, 2 if counterclockwise
    */
   public static int orientation(Point p, Point q, Point r) {
      double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

      if (val == 0) {
         return 0; // collinear
      }

      return (val > 0) ? 1 : 2; // clock or counterclock wise
   }

   /**
    * Returns a string representation of the point in the format:
    * "x: [value] , y: [value]".
    *
    * @return string representation of the point
    */
   @Override
   public String toString() {
      return "x: " + this.x + " , y: " + this.y;
   }
}
