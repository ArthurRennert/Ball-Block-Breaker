import java.util.Random;

/**
 *
 */
public class Line {

   //instance variables
    private Point start;
    private Point end;
    private double slope;
    private double yIntercept;
//    private double _xIntercept;

   //class variables
   private static final double EPSILON = 0.0001;


   /**
    * @param start
    * @param end
    */
   public Line(Point start, Point end) {
      this.start = new Point(start);
      this.end = new Point(end);
      this.slope = this.slopeCalc();
      this.yIntercept = this.getYIntercept();
   }

   /**
    * @param x1
    * @param y1
    * @param x2
    * @param y2
    */
   public Line(double x1, double y1, double x2, double y2) {
      this.start = new Point(x1, y1);
      this.end = new Point(x2, y2);
      this.slope = this.slopeCalc();
      this.yIntercept = this.getYIntercept();
   }

   /**
    * @return - the result of the calculation of the slope of the line.
    */
   private double slopeCalc() {
      return ((end.getY() - start.getY()) / (end.getX() - start.getX()));
   }

//   private double get_xIntercept()
//   {
//
//   }

   /**
    * @return - the Y Intercept of the line.
    */
   //change public to private
   public double getYIntercept() {
      return this.start.getY() - (this.slope * this.start.getX());
   }

   /**
    * @return - the slope of the line.
    */
   public double getSlope() {
      return this.slope;
   }

   /**
    * @return - the length of the line.
    */
   public double length() {
      return this.start.distance(end);
   }

   /**
    * @return - a new Point object representing the middle point of the line.
    */
   // Returns the middle point of the line
   public Point middle() {
      return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
   }

   /**
    * @return - the starting Point object of the line.
    */
   // Returns the start point of the line
   public Point getStartPoint() {
      return this.start;
   }

   /**
    * @return - the ending Point object of the line.
    */
   // Returns the end point of the line
   public Point getEndPoint() {
      return this.end;
   }


   /**
    * @param other
    * @return - true if the lines intersect, false otherwise.
    */
   public boolean isIntersecting(Line other) {
      //the (potential) intersection point variables
      double x, y;

      //parallel lines
      if (this.slope == other.slope) {
         return false;
      }

      Point intersectionPoint = this.intersectionWith(other);

      // check if the intersectionPoint is on both lines segment
      // to be in line segment the intersectionPoint must meet the following:
      // 1. intersectionPoint's x and y coordinates must be between the line's corresponding coordinates
      // 2. intersectionPoint's x and y coordinates must satisfy the line formulas:
      // m = (y-y1) / (x - x1) and m = (y2 - y1) / (x2 - x1)

      if ((Double.isInfinite(this.slope)
              || (Math.abs(intersectionPoint.getY() - (this.slope * intersectionPoint.getX() + this.yIntercept))
              <= EPSILON))
              && (intersectionPoint.getX() >= Math.min(this.start.getX(), this.end.getX())
              && intersectionPoint.getX() <= Math.max(this.start.getX(), this.end.getX()))
              && (intersectionPoint.getY() >= Math.min(this.start.getY(), this.end.getY())
              && intersectionPoint.getY() <= Math.max(this.start.getY(), this.end.getY()))
              //second line
              && ((Double.isInfinite(other.slope)
              || (Math.abs(intersectionPoint.getY() - (other.slope * intersectionPoint.getX() + other.yIntercept))
              <= EPSILON)))
              && intersectionPoint.getX() >= Math.min(other.start.getX(), other.end.getX())
              && intersectionPoint.getX() <= Math.max(other.start.getX(), other.end.getX())
              && intersectionPoint.getY() >= Math.min(other.start.getY(), other.end.getY())
              && intersectionPoint.getY() <= Math.max(other.start.getY(), other.end.getY())) {
         return true;
      }
      return false;
   }

   /**
    * @param other
    * @return - a new Point object representing the intersection point of the lines, null otherwise.
    */
   public Point intersectionWith(Line other) {
      double x, y;

      if (this.slope == other.slope) {
         return null;
      }


      //vertical or horizontal lines
      if (this.start.getX() == this.end.getX()  //this line is vertical line
         && other.start.getY() == other.end.getY()) {  //other line is horizontal line
         x = this.start.getX();
         y = other.start.getY();
      } else if (this.start.getX() == this.end.getX()) { //this line is vertical line (other line is not horizontal)
         x = this.start.getX();
         y = other.slope * this.start.getX() + other.yIntercept;
      } else if (other.start.getX() == other.end.getX()  //other line is vertical line
         && this.start.getY() == this.end.getY()) {   //this line is horizontal line
         x = other.start.getX();
         y = this.start.getY();
      } else if (other.start.getX() == other.end.getX()) { //other line is vertical line (this line is not horizontal)
         x = other.start.getX();
         y = this.slope * other.start.getX() + this.yIntercept;
      } else {
      // line1 equation: y1 = m1x + b1
      // line2 equation: y2 = m2x + b2
      // y1 = y2
      // m1x + b1 = m2x + b2
      // x(m1 - m2) = b2 - b1
      // x = (b2 - b1) / (m1 - m2)
      // y1 = m1x + b1 (we found x, and we have m1)
      // b1 and b2 are the y interceptors
      // m1 and m2 are the slopes
        //else if and only if both this and other lines are not vertical or horizontal lines

         x = ((other.yIntercept - this.yIntercept) / (this.slope - other.slope));
         y = this.slope * x + this.yIntercept;
      }
      return new Point(x, y);
   }

   /**
    * @param other
    * @return - return true if the lines are equal, false otherwise.
    */
   public boolean equals(Line other) {
      if (other == null) {
         return false;
      } else if ((this.start.equals(other.start) && this.end.equals(other.end))
              || (this.start.equals(other.end) && (this.end.equals(other.start)))) {
         return true;
      }
      return false;
   }

   /**
    * @return - a random line.
    */
   public static Line generateRandomLine() {
      Random rand = new Random();

      //random point's coordinates between 1 and 350
      Point p1 = new Point(rand.nextInt(350) + 1, rand.nextInt(350) + 1);
      Point p2 = new Point(rand.nextInt(350) + 1, rand.nextInt(350) + 1);

      return new Line(p1, p2);
   }

}