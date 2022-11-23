package gui.shapes;

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
    * This method finds orientation of ordered triplet (p, q, r).
    * @param p - point one.
    * @param q - point two.
    * @param r - point three.
    * @return - 0 --> p, q and r are collinear.
    *           1 --> Clockwise.
    *           2 --> Counterclockwise.
    */
   public static int orientation(Point p, Point q, Point r) {
      double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

      if (val == 0) {
         return 0; // collinear
      }

      return (val > 0) ? 1 : 2; // clock or counterclock wise
   }


   /**
    * @return - a String representation of the Point object. The format: x: value , y: value
    */
   @Override
   public String toString() {
      return "x: " + this.x + " , y: " + this.y;
   }

}
