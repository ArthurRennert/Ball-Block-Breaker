import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Rectangle {

   private Point point;
   private double width, height;


   /**
    * @param upperLeft
    * @param width
    * @param height
    */
   public Rectangle(Point upperLeft, double width, double height) {
      point = new Point(upperLeft);
      this.width = width;
      this.height = height;
   }

   /**
    * @param line
    * @return - Return a (possibly empty) List of intersection points with the specified line.
    */
   public java.util.List<Point> intersectionPoints(Line line) {
      List<Point> retList = new ArrayList<Point>();
      List<Line> rectEdgesList = getSidesList();
      Point temp = null;

      for (Line elem : rectEdgesList) {
         if (elem.isIntersecting(line)) {
            temp = elem.intersectionWith(line);
            retList.add(temp);
         }
      }
      return retList;
   }

   /**
    * @return - A list of the 4 sides combining the rectangle.
    */
   public List<Line> getSidesList() {
      List<Line> rectEdgesList = new ArrayList<>();

      rectEdgesList.add(new Line(getUpperLeft(), getUpperRight()));   //the upper edge
      rectEdgesList.add(new Line(getUpperLeft(), getLowerLeft()));    //the left edge
      rectEdgesList.add(new Line(getLowerLeft(), getLowerRight()));   //the bottom edge
      rectEdgesList.add(new Line(getUpperRight(), getLowerRight()));  //the right edge

      return rectEdgesList;
   }

   /**
    * @return - Return the width of the rectangle.
    */
   public double getWidth() {
      return width;
   }

   /**
    * @return Return the height of the rectangle.
    */
   public double getHeight() {
      return height;
   }

   /**
    * @return - Returns the upper-left point of the rectangle.
    */
   public Point getUpperLeft() {
      return point;
   }

   /**
    * @return - Returns the x's upper-left coordinate of the rectangle.
    */
   public double getXUpperLeftCoordinate() {
      return point.getX();
   }

   /**
    * @return - Returns the y's upper-left coordinate of the rectangle.
    */
   public double getYUpperLeftCoordinate() {
      return point.getY();
   }

   /**
    * @return - Returns the upper-right point of the rectangle.
    */
   public Point getUpperRight() {
      return new Point(point.getX() + width, point.getY());
   }

   /**
    * @return - Returns the x's upper-right coordinate of the rectangle.
    */
   public double getXUpperRightCoordinate() {
      return point.getX() + width;
   }

   /**
    * @return - Returns the y's upper-right coordinate of the rectangle.
    */
   public double getYUpperRightCoordinate() {
      return point.getY();
   }

   /**
    * @return - Returns the lower-left point of the rectangle.
    */
   public Point getLowerLeft() {
      return new Point(point.getX(), point.getY() + height);
   }

   /**
    * @return - Returns the x's lower-left coordinate of the rectangle.
    */
   public double getXLowerLeftCoordinate() {
      return point.getX();
   }

   /**
    * @return - Returns the y's lower-left coordinate of the rectangle.
    */
   public double getYLowerLeftCoordinate() {
      return point.getY() + height;
   }

   /**
    * @return - Returns the lower-right point of the rectangle.
    */
   public Point getLowerRight() {
      return new Point(point.getX() + width, point.getY() + height);
   }

   /**
    * @return - Returns the x's lower-right coordinate of the rectangle.
    */
   public double getXLowerRightCoordinate() {
      return point.getX() + width;
   }

   /**
    * @return - Returns the y's lower-right coordinate of the rectangle.
    */
   public double getYLowerRightCoordinate() {
      return point.getY() + height;
   }

   /**
    * @return - Returns the left-edge of the rectangle.
    */
   public Line getLeftSide() {
      return new Line(getUpperLeft(), getLowerLeft());
   }

   /**
    * @return - Returns the upper-edge of the rectangle.
    */
   public Line getUpperSide() {
      return new Line(getUpperLeft(), getUpperRight());
   }

   /**
    * @return - Returns the right-edge of the rectangle.
    */
   public Line getRightSide() {
      return new Line(getUpperRight(), getLowerRight());
   }

   /**
    * @return - Returns the bottom-edge of the rectangle.
    */
   public Line getBottomSide() {
      return new Line(getLowerLeft(), getLowerRight());
   }

}