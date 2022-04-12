/**
 *
 */
public class Point {
   //instance variables
   private double x;
   private double y;


   /**
    * @param x
    * @param y
    */
   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }

   /**
    * @param other
    */
   public Point(Point other) {
      x = other.x;
      y = other.y;
   }

   /**
    * @param other
    * @return - the distance between the two points.
    */
   public double distance(Point other) {
      return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
   }

   /**
    * @param other
    * @return - true is the points are equal, false otherwise.
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
    * @return -  the x value of the point.
    */
   // Return the x and y values of this point
   public double getX() {
      return this.x;
   }

   /**
    * @return - the y value of the point.
    */
   public double getY() {
      return this.y;
   }

   /**
    * @return - a String representation of the Point object. The format: x: value , y: value
    */
   @Override
   public String toString() {
      return "x: " + this.x + " , y: " + this.y;
   }

}
