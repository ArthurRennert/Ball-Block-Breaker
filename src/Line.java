public class Line
{

   //instance variables
    private Point _start;
    private Point _end;
    private double _slope;
    private double _yIntercept;
//    private double _xIntercept;


   // constructors
   public Line(Point start, Point end)
   {
      this._start = new Point(start);
      this._end = new Point(end);
      this._slope = this.slopeCalc();
      this._yIntercept = this.get_yIntercept();
   }
   public Line(double x1, double y1, double x2, double y2)
   {
      this._start = new Point(x1, y1);
      this._end = new Point(x2, y2);
      this._slope = this.slopeCalc();
      this._yIntercept = this.get_yIntercept();
   }

   private double slopeCalc()
   {
      return ((_end.getY() - _start.getY()) / (_end.getX() - _start.getX()));
   }

//   private double get_xIntercept()
//   {
//
//   }

   //change public to private
   public double get_yIntercept()
   {
      return this._start.getY() - (this._slope * this._start.getX());
   }

   //consider deleting or changing public to private
   public double getSlope()
   {
      return this._slope;
   }

   // Return the length of the line
   public double length()
   {
      return this._start.distance(_end);
   }

   // Returns the middle point of the line
   public Point middle()
   {
      return new Point((this._start.getX() + this._end.getX()) / 2, (this._start.getY() + this._end.getY()) / 2);
   }

   // Returns the start point of the line
   public Point start()
   {
      return this._start;
   }

   // Returns the end point of the line
   public Point end()
   {
      return this._end;
   }


   // Returns true if the lines intersect, false otherwise
   public boolean isIntersecting(Line other)
   {
      //the (potential) intersection point variables
      double x, y;

      //parallel lines
      if(this._slope == other._slope)
         return false;

      Point intersectionPoint = this.intersectionWith(other);

      // check if the intersectionPoint is on both lines segment
      // to be in line segment the intersectionPoint must meet the following:
      // 1. intersectionPoint's x and y coordinates must be between the line's corresponding coordinates
      // 2. intersectionPoint's x and y coordinates must satisfy the line formulas m = (y-y1) / (x - x1) and m = (y2 - y1) / (x2 - x1)

      if((this._start.getX() - this._end.getX()) * (intersectionPoint.getY() - this._start.getY()) ==
              (this._start.getY() - this._end.getY()) * (intersectionPoint.getX() - this._start.getX())
               && (intersectionPoint.getX() >= Math.min(this._start.getX(), this._end.getX()) && intersectionPoint.getX() <= Math.max(this._start.getX(), this._end.getX()))
               && (intersectionPoint.getY() >= Math.min(this._start.getY(), this._end.getY()) && intersectionPoint.getY() <= Math.max(this._start.getY(), this._end.getY()))
               //second line
              && (other._start.getX() - other._end.getX()) * (intersectionPoint.getY() - other._start.getY()) ==
              (other._start.getY() - other._end.getY()) * (intersectionPoint.getX() - other._start.getX())
              && intersectionPoint.getX() >= Math.min(other._start.getX(), other._end.getX()) && intersectionPoint.getX() <= Math.max(other._start.getX(), other._end.getX())
              && intersectionPoint.getY() >= Math.min(other._start.getY(), other._end.getY()) && intersectionPoint.getY() <= Math.max(other._start.getY(), other._end.getY()))
         return true;
      return false;
   }

   // Returns the intersection point if the lines intersect,
   // and null otherwise.
   public Point intersectionWith(Line other)
   {
      double x, y;

      if(this._slope == other._slope)
         return null;


      //vertical or horizontal lines
      if(this._start.getX() == this._end.getX()  //this line is vertical line
         && other._start.getY() == other._end.getY())  //other line is horizontal line
      {
         x = this._start.getX();
         y = other._start.getY();
      }
      else if(this._start.getX() == this._end.getX())  //this line is vertical line (other line is not horizontal)
      {
         x = this._start.getX();
         y = other._slope * this._start.getX() + other._yIntercept;
      }
      else if(other._start.getX() == other._end.getX()  //other line is vertical line
         && this._start.getY() == this._end.getY())    //this line is horizontal line
      {
         x = other._start.getX();
         y = this._start.getY();
      }
      else if(other._start.getX() == other._end.getX())  //other line is vertical line (this line is not horizontal)
      {
         x = other._start.getX();
         y = this._slope * other._start.getX() + this._yIntercept;
      }

      // line1 equation: y1 = m1x + b1
      // line2 equation: y2 = m2x + b2
      // y1 = y2
      // m1x + b1 = m2x + b2
      // x(m1 - m2) = b2 - b1
      // x = (b2 - b1) / (m1 - m2)
      // y1 = m1x + b1 (we found x, and we have m1)
      // b1 and b2 are the y interceptors
      // m1 and m2 are the slopes
      else  //else if and only if both this and other lines are not vertical or horizontal lines
      {
         x = ((other._yIntercept - this._yIntercept) / (this._slope - other._slope));
         y = this._slope * x + this._yIntercept;
      }

      return new Point(x, y);
   }

   // equals -- return true if the lines are equal, false otherwise
   public boolean equals(Line other)
   {
      if(other == null)
         return false;
      else if((this._start.equals(other._start) && this._end.equals(other._end)) || (this._start.equals(other._end) && (this._end.equals(other._start))))
         return true;
      return false;
   }

}