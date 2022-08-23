import java.util.ArrayList;
import java.util.List;
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
    * @return - the x's coordinate of the starting Point object of the line.
    */
   public double getXOfStartPoint() {
      return this.start.getX();
   }

   /**
    * @return - the y's coordinate of the starting Point object of the line.
    */

   public double getYOfStartPoint() {
      return this.start.getY();
   }

   /**
    * @return - the ending Point object of the line.
    */
   // Returns the end point of the line
   public Point getEndPoint() {
      return this.end;
   }

   /**
    * @return - the x's coordinate of the ending Point object of the line.
    */
   public double getXOfEndPoint() {
      return this.end.getX();
   }

   /**
    * @return - the y's coordinate of the ending Point object of the line.
    */
   public double getYOfEndPoint() {
      return this.end.getY();
   }

   /**
    * @param other
    * @return - true if the lines intersect, false otherwise.
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
    * @param rect
    * @return - If this line does not intersect with the rectangle, null is returned,
    *           otherwise, the closest intersection point to the start of the line is returned.
    */
   public Point closestIntersectionToStartOfLine(Rectangle rect) {
      List<Line> rectEdges = new ArrayList<>();
      rectEdges.add(rect.getLeftSide());
      rectEdges.add(rect.getRightSide());
      rectEdges.add(rect.getUpperSide());
      rectEdges.add(rect.getBottomSide());

//      System.out.println(rectEdges);

      List<Point> intersectionPoints = new ArrayList<>();
      Point p = null;

      for (Line elem : rectEdges) {
         p = elem.intersectionWith(this);
         if (p != null) {
            intersectionPoints.add(p);
//            System.out.println(p);
            p = null;
         }
      }
      if (intersectionPoints.size() == 0) {
         return null;
      }
      double distance = Double.MAX_VALUE, temp = 0;
      Point resPoint = null;
      for (Point elem : intersectionPoints) {
         temp = elem.distance(this.start);
//         System.out.println(temp);
         if (distance > temp) {
            distance = temp;
            resPoint = elem;
         }
      }
      return resPoint;
   }

   /**
    * @param other
    * @return - a new Point object representing the intersection point of the lines, null otherwise.
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

      //      System.out.println("x: " + x);
      //      System.out.println("y: " + y);
      //      System.out.println("this line: " + this);
      //      System.out.println("other line: " + other);
   }

   /**
    * Given three collinear points p, q, r, the function checks if point q lies on line segment 'pr'.
    * @param p - point p.
    * @param q - point q.
    * @param r - point r.
    * @return - true if point q lies on line segment 'pr', otherwise false is returned.
    */
   private static boolean isPointOnSegment(Point p, Point q, Point r) {
      if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
              && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
         return true;
      }
      return false;
   }

   /**
    * @return - true if this line is vertical, otherwise false is returned.
    */
   private boolean isVerticalLine() {
      return this.start.getX() == this.end.getX();
   }

   /**
    * @return - true if this line is horizontal, otherwise false is returned.
    */
   private boolean isHorizontalLine() {
      return this.start.getY() == this.end.getY();
   }

   @Override
   public String toString() {
      return "Start Line: " + "(" + this.getStartPoint().getX() + " , " + this.getStartPoint().getY() + ")\nEnd Line: "
              + "(" + this.getEndPoint().getX() + " , " + this.getEndPoint().getY() + ")\n";
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