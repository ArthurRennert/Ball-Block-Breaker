package gui.shapes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle in 2D space.
 * It is defined by its upper-left point, width, height, color, and name.
 * The class provides utility methods for accessing corners, sides, and computing intersections.
 */
public class Rectangle {

   private Point point;
   private double width, height;
   private String name;
   private Color color;

   /**
    * Constructs a rectangle with a given upper-left point, width, height, name, and color.
    *
    * @param upperLeft the upper-left point
    * @param width the rectangle width
    * @param height the rectangle height
    * @param name the name of the rectangle
    * @param c the color of the rectangle
    */
   public Rectangle(Point upperLeft, double width, double height, String name, Color c) {
      point = new Point(upperLeft);
      this.width = width;
      this.height = height;
      this.name = name;
      color = c;
   }

   /**
    * Updates the upper-left point of the rectangle.
    *
    * @param p the new upper-left point
    */
   public void setUpperLeftPoint(Point p) {
      point = new Point(p);
   }

   /**
    * Returns a list of intersection points between this rectangle and a given line.
    *
    * @param line the line to check for intersection
    * @return list of intersection points (may be empty)
    */
   public java.util.List<Point> intersectionPoints(Line line) {
      List<Point> retList = new ArrayList<Point>();
      List<Line> rectSidesList = getSidesList();
      Point temp = null;

      for (Line elem : rectSidesList) {
         if (elem.isIntersecting(line)) {
            temp = elem.intersectionWith(line);
            retList.add(temp);
         }
      }
      return retList;
   }

   /**
    * Returns the current color of the rectangle.
    *
    * @return rectangle color
    */
   public Color getColor() {
      return color;
   }

   /**
    * Sets a new color for the rectangle.
    *
    * @param c the new color
    */
   public void setColor(Color c) {
      color = c;
   }

   /**
    * Returns a list containing the four sides of the rectangle as Line objects.
    *
    * @return list of rectangle sides
    */
   public List<Line> getSidesList() {
      List<Line> rectSidesList = new ArrayList<>();

      rectSidesList.add(new Line(getUpperLeft(), getUpperRight()));   //the upper edge
      rectSidesList.add(new Line(getUpperLeft(), getLowerLeft()));    //the left edge
      rectSidesList.add(new Line(getLowerLeft(), getLowerRight()));   //the bottom edge
      rectSidesList.add(new Line(getUpperRight(), getLowerRight()));  //the right edge

      return rectSidesList;
   }

   /**
    * Returns the width of the rectangle.
    *
    * @return width
    */
   public double getWidth() {
      return width;
   }

   /**
    * Returns the height of the rectangle.
    *
    * @return height
    */
   public double getHeight() {
      return height;
   }

   /**
    * Returns the name of the rectangle.
    *
    * @return name
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the upper-left point of the rectangle.
    *
    * @return upper-left point
    */
   public Point getUpperLeft() {
      return point;
   }

   /**
    * Returns the x-coordinate of the upper-left point.
    *
    * @return x of upper-left point
    */
   public double getXUpperLeftCoordinate() {
      return point.getX();
   }

   /**
    * Returns the y-coordinate of the upper-left point.
    *
    * @return y of upper-left point
    */
   public double getYUpperLeftCoordinate() {
      return point.getY();
   }

   /**
    * Returns the upper-right point of the rectangle.
    *
    * @return upper-right point
    */
   public Point getUpperRight() {
      return new Point(point.getX() + width, point.getY());
   }

   /**
    * Returns the x-coordinate of the upper-right point.
    *
    * @return x of upper-right point
    */
   public double getXUpperRightCoordinate() {
      return point.getX() + width;
   }

   /**
    * Returns the y-coordinate of the upper-right point.
    *
    * @return y of upper-right point
    */
   public double getYUpperRightCoordinate() {
      return point.getY();
   }

   /**
    * Returns the lower-left point of the rectangle.
    *
    * @return lower-left point
    */
   public Point getLowerLeft() {
      return new Point(point.getX(), point.getY() + height);
   }

   /**
    * Returns the x-coordinate of the lower-left point.
    *
    * @return x of lower-left point
    */
   public double getXLowerLeftCoordinate() {
      return point.getX();
   }

   /**
    * Returns the y-coordinate of the lower-left point.
    *
    * @return y of lower-left point
    */
   public double getYLowerLeftCoordinate() {
      return point.getY() + height;
   }

   /**
    * Returns the lower-right point of the rectangle.
    *
    * @return lower-right point
    */
   public Point getLowerRight() {
      return new Point(point.getX() + width, point.getY() + height);
   }

   /**
    * Returns the x-coordinate of the lower-right point.
    *
    * @return x of lower-right point
    */
   public double getXLowerRightCoordinate() {
      return point.getX() + width;
   }

   /**
    * Returns the y-coordinate of the lower-right point.
    *
    * @return y of lower-right point
    */
   public double getYLowerRightCoordinate() {
      return point.getY() + height;
   }

   /**
    * Returns the left side of the rectangle as a Line.
    *
    * @return left side
    */
   public Line getLeftSide() {
      return new Line(getUpperLeft(), getLowerLeft());
   }

   /**
    * Returns the upper side of the rectangle as a Line.
    *
    * @return upper side
    */
   public Line getUpperSide() {
      return new Line(getUpperLeft(), getUpperRight());
   }

   /**
    * Returns the right side of the rectangle as a Line.
    *
    * @return right side
    */
   public Line getRightSide() {
      return new Line(getUpperRight(), getLowerRight());
   }

   /**
    * Returns the bottom side of the rectangle as a Line.
    *
    * @return bottom side
    */
   public Line getBottomSide() {
      return new Line(getLowerLeft(), getLowerRight());
   }

   /**
    * Returns a string representation of the rectangle including name and side information.
    *
    * @return string describing the rectangle
    */
   @Override
   public String toString() {
      return "Name: " + name + "\nUpper side: " + getUpperSide() + "\nBottom side: " + getBottomSide() + "\nLeft side: "
              + getLeftSide() + "\nRight side: " + getRightSide();
   }
}