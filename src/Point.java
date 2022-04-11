public class Point
{
   //instance variables
   private double _x;
   private double _y;

   //constructor
   public Point(double x, double y)
   {
      _x = x;
      _y = y;
   }

   public Point(Point other)
   {
      _x = other._x;
      _y = other._y;
   }

   // distance -- return the distance of this point to the other point
   public double distance(Point other)
   {
      return Math.sqrt(((this._x - other._x) * (this._x - other._x)) + ((this._y - other._y) * (this._y - other._y)));
   }

   // equals -- return true is the points are equal, false otherwise
   public boolean equals(Point other)
   {
      if(other == null)
         return false;

      if(this._x == other._x && this._y == other._y)
         return true;
      return false;
   }

   // Return the x and y values of this point
   public double getX()
   {
      return this._x;
   }
   public double getY()
   {
      return this._y;
   }

   @Override
   public String toString()
   {
      return "x: " + this._x + " , y: " + this._y;
   }

}
