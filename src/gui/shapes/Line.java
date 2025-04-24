package gui.shapes;
import java.util.List;
import java.util.Random;

/**
 * The Line class represents a line segment between two points in a 2D space.
 * It includes geometric utilities such as slope calculation, intersection detection,
 * and point positioning.
 */
public class Line {

   private static final double EPSILON = 0.5;

   //instance variables
    private Point start;
    private Point end;
    private double slope;
    private double yIntercept;

   /**
    * Constructs a Line object given start and end points.
    *
    * @param start the starting point of the line
    * @param end the ending point of the line
    */
   public Line(Point start, Point end) {
      this.start = new Point(start);
      this.end = new Point(end);
      this.slope = this.slopeCalc();
      this.yIntercept = this.calcYIntercept();
   }

   /**
    * Constructs a Line object given the x and y coordinates of the two points.
    *
    * @param x1 x-coordinate of the start point
    * @param y1 y-coordinate of the start point
    * @param x2 x-coordinate of the end point
    * @param y2 y-coordinate of the end point
    */
   public Line(double x1, double y1, double x2, double y2) {
      this.start = new Point(x1, y1);
      this.end = new Point(x2, y2);
      this.slope = this.slopeCalc();
      this.yIntercept = this.calcYIntercept();
   }

   /**
    * @return - the result of the calculation of the slope of the line.
    */
   private double slopeCalc() {
      return ((end.getY() - start.getY()) / (end.getX() - start.getX()));
   }

   /**
    * Calculates the y-intercept of the line.
    *
    * @return the y-intercept
    */
   public double calcYIntercept() {
      return this.start.getY() - (this.slope * this.start.getX());
   }

   /**
    * Returns the slope of the line.
    *
    * @return slope of the line
    */
   public double getSlope() {
      return this.slope;
   }

   /**
    * Returns the length of the line.
    *
    * @return line length
    */
   public double length() {
      return this.start.distance(end);
   }

   /**
    * Calculates and returns the middle point of the line.
    *
    * @return midpoint of the line
    */
   public Point calcMiddle() {
      return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
   }

   /**
    * Returns the starting point of the line.
    *
    * @return start point
    */
   public Point getStartPoint() {
      return this.start;
   }

   /**
    * Returns the x-coordinate of the start point.
    *
    * @return x of start point
    */
   public double getXOfStartPoint() {
      return this.start.getX();
   }

   /**
    * Returns the y-coordinate of the start point.
    *
    * @return y of start point
    */
   public double getYOfStartPoint() {
      return this.start.getY();
   }

   /**
    * Returns the ending point of the line.
    *
    * @return end point
    */
   public Point getEndPoint() {
      return this.end;
   }

   /**
    * Returns the x-coordinate of the end point.
    *
    * @return x of end point
    */
   public double getXOfEndPoint() {
      return this.end.getX();
   }

   /**
    * Returns the y-coordinate of the end point.
    *
    * @return y of end point
    */
   public double getYOfEndPoint() {
      return this.end.getY();
   }

   /**
    * Returns true if this line intersects with another line.
    *
    * @param other the other line
    * @return true if the lines intersect, false otherwise
    */
   public boolean isIntersecting(Line other) {
      // Find the four orientations needed for general and special cases
      Point p1 = this.start, q1 = this.end, p2 = other.start, q2 = other.end;
      int o1 = Point.orientation(p1, q1, p2);
      int o2 = Point.orientation(p1, q1, q2);
      int o3 = Point.orientation(p2, q2, p1);
      int o4 = Point.orientation(p2, q2, q1);

      // General case
      if (o1 != o2 && o3 != o4) {
         return true;
      }

      // Special Cases
      // p1, q1 and p2 are collinear and p2 lies on segment p1q1
      if (o1 == 0 && isPointOnSegment(p1, p2, q1)) {
         return true;
      }

      // p1, q1 and q2 are collinear and q2 lies on segment p1q1
      if (o2 == 0 && isPointOnSegment(p1, q2, q1)) {
         return true;
      }

      // p2, q2 and p1 are collinear and p1 lies on segment p2q2
      if (o3 == 0 && isPointOnSegment(p2, p1, q2)) {
         return true;
      }

      // p2, q2 and q1 are collinear and q1 lies on segment p2q2
      if (o4 == 0 && isPointOnSegment(p2, q1, q2)) {
         return true;
      }

      return false; // Doesn't fall in any of the above cases
   }

   /**
    * Returns the closest intersection point with a rectangle to the start of the line.
    *
    * @param rect the rectangle
    * @return closest intersection point or null if none
    */
   public Point closestIntersectionToStartOfLine(Rectangle rect) {
      List<Point> intersectionPoints = rect.intersectionPoints(this);
      if (intersectionPoints.size() == 0) {
         return null;
      }
      double distance = intersectionPoints.get(0).distance(this.start), temp;
      Point resPoint = intersectionPoints.get(0);
      for (Point elem : intersectionPoints) {
         temp = elem.distance(this.start);
         if (distance > temp) {
            distance = temp;
            resPoint = elem;
         }
      }
      return resPoint;
   }

   /**
    * Returns the intersection point of this line with another line, if it exists.
    *
    * @param other the other line
    * @return the intersection point or null if no intersection
    */
   public Point intersectionWith(Line other) {
      Point a = this.start, b = this.end, c = other.start, d = other.end;

      // Line ab represented as a1x + b1y = c1
      double a1 = b.getY() - a.getY();
      double b1 = a.getX() - b.getX();
      double c1 = a1 * (a.getX()) + b1 * (a.getY());

      // Line cd represented as a2x + b2y = c2
      double a2 = d.getY() - c.getY();
      double b2 = c.getX() - d.getX();
      double c2 = a2 * (c.getX()) + b2 * (c.getY());

      double determinant = a1 * b2 - a2 * b1;

      if (determinant == 0) { // The lines are parallel
         if (isPointOnSegment(a, c, b)) {
            return new Point(c);
         } else if (isPointOnSegment(c, a, d)) {
            return new Point(a);
         }
      }

      // The lines are not parallel (determinant is not zero)
      double x = (b2 * c1 - b1 * c2) / determinant;
      double y = (a1 * c2 - a2 * c1) / determinant;
      return new Point(x, y);
   }

   /**
    * Checks if point q lies on the line segment pr.
    *
    * @param p start of segment
    * @param q point to check
    * @param r end of segment
    * @return true if q lies on segment pr
    */
   public static boolean isPointOnSegment(Point p, Point q, Point r) {
      if (q.getX() <= (Math.max(p.getX(), r.getX()) + EPSILON) && q.getX() >= Math.min(p.getX(), r.getX()) - EPSILON
              && q.getY() <= Math.max(p.getY(), r.getY()) + EPSILON && q.getY() >= Math.min(p.getY(), r.getY()) - EPSILON) {
         return true;
      }
      return false;
   }

   /**
    * Returns true if the line is vertical.
    *
    * @return true if vertical
    */
   public boolean isVerticalLine() {
      return this.start.getX() == this.end.getX();
   }

   /**
    * Returns true if the line is horizontal.
    *
    * @return true if horizontal
    */
   public boolean isHorizontalLine() {
      return this.start.getY() == this.end.getY();
   }

   /**
    * Returns a string representation of the line.
    *
    * @return string showing start and end points
    */
   @Override
   public String toString() {
      return "Start Line: " + "(" + this.getStartPoint().getX() + " , " + this.getStartPoint().getY() + ")\tEnd Line: "
              + "(" + this.getEndPoint().getX() + " , " + this.getEndPoint().getY() + ")\n";
   }

   /**
    * Compares this line to another for equality.
    *
    * @param other the other line
    * @return true if both lines have the same start and end points (in any order)
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
    * Generates a random line with coordinates between 1 and 350.
    *
    * @return a new random Line object
    */
   public static Line generateRandomLine() {
      Random rand = new Random();

      //random point's coordinates between 1 and 350
      Point p1 = new Point(rand.nextInt(350) + 1, rand.nextInt(350) + 1);
      Point p2 = new Point(rand.nextInt(350) + 1, rand.nextInt(350) + 1);

      return new Line(p1, p2);
   }
}