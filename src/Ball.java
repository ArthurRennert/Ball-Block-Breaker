import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

/**
 *
 */
public class Ball {
   //instance variables
   private Point point;
   private int radius;
   private Color color;
   private Velocity velocity;
   private GameEnvironment ge;


   /**
    * @param center
    * @param r
    * @param color
    */
   public Ball(Point center, int r, Color color) {
      point = new Point(center);
      radius = r;
      this.color = new Color(color.getRGB());
      velocity = new Velocity(0, 0);
   }

   /**
    * @param x
    * @param y
    * @param r
    * @param color
    */
   public Ball(double x, double y, int r, Color color) {
      point = new Point(x, y);
      radius = r;
      this.color = new Color(color.getRGB());
      velocity = new Velocity(0, 0);
   }


   /**
    * @param gameEnvironment
    */
   public void setGameEnvironment(GameEnvironment gameEnvironment) {
      ge = gameEnvironment;
   }

   /**
    * @return the radius of the Ball object.
    */
   public int getRadius() {
      return radius;
   }


   /**
    * @return - the x coordinate of the center of the ball.
    */
   public int getX() {
      return (int) point.getX();
   }

   /**
    * @return - the y coordinate of the center of the ball.
    */
   public int getY() {
      return (int) point.getY();
   }

   /**
    * @return - the volume of the ball.
    */
   public int getSize() {
      return (int) ((4 * Math.PI * Math.pow(radius, 3)) / 3);
   }

   /**
    * @return - the color of the ball.
    */
   public Color getColor() {
      return this.color;
   }


   /**
    * @param surface
    */
   public void drawOn(DrawSurface surface) {
      surface.setColor(this.color);
      surface.fillCircle((int) point.getX(), (int) point.getY(), radius);
   }

   /**
    * @param v
    */
   public void setVelocity(Velocity v) {
      velocity = new Velocity(v);
   }

   /**
    * @param dx
    * @param dy
    */
   public void setVelocity(double dx, double dy) {
      velocity.setDx(dx);
      velocity.setDy(dy);
   }

   /**
    * @return - the velocity of the ball.
    */
   public Velocity getVelocity() {
      return velocity;
   }


   /**
    * @param frameWidth
    * @param frameHeight
    */
   public void moveOneStep(int frameWidth, int frameHeight) {
      if (frameHeight <= point.getX() + radius || point.getX() <= radius) {
         velocity.setDx(-velocity.getDx());
      }

      if (frameWidth <= point.getY() + radius || point.getY() <= radius) {
         velocity.setDy(-velocity.getDy());
      }
      point = this.getVelocity().applyToPoint(point);
   }

   /**
    *
    */
   public void moveOneStep(DrawSurface d) {
      List<Collidable> collidableList = ge.getListOfCollidableObjects();
//      System.out.println(collidableList);

      CollisionInfo collInfo = ge.getClosestCollision(new Line(point,
              new Point(this.getVelocity().getDx() * 600000, this.getVelocity().getDy() * 600000)));

      System.out.println(collInfo);

      Velocity newVel = collInfo.collisionObject().hit(point, collInfo.collisionPoint(), this.getVelocity());

//      ge.setNextCollObj(collInfo.collisionObject());


      d.drawLine((int) point.getX(), (int) point.getY(), (int) this.getVelocity().getDx() * 600000,
              (int) this.getVelocity().getDy() * 600000);

      Line l = new Line((int) point.getX(), (int) point.getY(), (int) this.getVelocity().getDx() * 600000,
              (int) this.getVelocity().getDy() * 600000);


      Line ll = collidableList.get(1).getCollisionRectangle().getBottomSide();
      Line ll2 = collidableList.get(1).getCollisionRectangle().getUpperSide();
      Line ll3 = collidableList.get(1).getCollisionRectangle().getRightSide();
      Line ll4 = collidableList.get(4).getCollisionRectangle().getLeftSide();
      Line ll5 = collidableList.get(3).getCollisionRectangle().getRightSide();
      Line ll6 = collidableList.get(2).getCollisionRectangle().getUpperSide();

      d.setColor(Color.BLUE);
      d.drawLine((int) ll.getStartPoint().getX(), (int) ll.getStartPoint().getY(), (int) ll.getEndPoint().getX(), (int) ll.getEndPoint().getY());
      d.drawLine((int) ll2.getStartPoint().getX(), (int) ll2.getStartPoint().getY(), (int) ll2.getEndPoint().getX(), (int) ll2.getEndPoint().getY());
      d.drawLine((int) ll3.getStartPoint().getX(), (int) ll3.getStartPoint().getY(), (int) ll3.getEndPoint().getX(), (int) ll3.getEndPoint().getY());
      d.drawLine((int) ll4.getStartPoint().getX(), (int) ll4.getStartPoint().getY(), (int) ll4.getEndPoint().getX(), (int) ll4.getEndPoint().getY());
      d.drawLine((int) ll5.getStartPoint().getX(), (int) ll5.getStartPoint().getY(), (int) ll5.getEndPoint().getX(), (int) ll5.getEndPoint().getY());
      d.drawLine((int) ll6.getStartPoint().getX(), (int) ll6.getStartPoint().getY(), (int) ll6.getEndPoint().getX(), (int) ll6.getEndPoint().getY());

      System.out.println(collidableList.get(1).getCollisionRectangle().getUpperSide().getEndPoint().getX());

      d.setColor(Color.GRAY);

//      Point collPo = null;
//      collPo = l.closestIntersectionToStartOfLine(collidableList.get(0).getCollisionRectangle());
//      if (collPo == null)
//         collPo = l.closestIntersectionToStartOfLine(collidableList.get(1).getCollisionRectangle());
//      if (collPo == null)
//         collPo = l.closestIntersectionToStartOfLine(collidableList.get(2).getCollisionRectangle());
//      if (collPo == null)
//         collPo = l.closestIntersectionToStartOfLine(collidableList.get(3).getCollisionRectangle());


//      System.out.println("Collision point: " + collPo);
//      System.out.println("Current location: " + point);
//      System.out.println("DX, DY: " + velocity.getDx() + ", " + velocity.getDy() + "\n");
//      d.fillCircle((int) collPo.getX(), (int) collPo.getY(), 8);
//      System.out.println("Upper: " +  l.closestIntersectionToStartOfLine(collidableList.get(0).getCollisionRectangle()));
//      System.out.println("Lower: " + l.closestIntersectionToStartOfLine(collidableList.get(1).getCollisionRectangle()));
//      System.out.println("Left: " + l.closestIntersectionToStartOfLine(collidableList.get(2).getCollisionRectangle()));
//      System.out.println("Right: " + l.closestIntersectionToStartOfLine(collidableList.get(3).getCollisionRectangle()));


//         if (point.getX() > collPo.getX() && (point.getX() + velocity.getDx()) < collPo.getX()
//                 || point.getX() < collPo.getX() && (point.getX() + velocity.getDx()) > collPo.getX()) {
//            velocity.setDx(-velocity.getDx());
//            System.out.println("velocity Dx changed");
//         }
//      if (Math.abs(collPo.getX() - (point.getX() + velocity.getDx())) <= getRadius()) {
//         velocity.setDx(-velocity.getDx());
//      }

//         if (point.getY() > collPo.getY() && (point.getY() + velocity.getDy()) < collPo.getY()
//                 || point.getY() < collPo.getY() && (point.getY() + velocity.getDy()) > collPo.getY()) {
//            velocity.setDy(-velocity.getDy());
//            System.out.println("velocity Dy changed");
//         }


//      if (Math.abs(collPo.getY() - (point.getY() + velocity.getDy())) <= getRadius()) {
//         velocity.setDy(-velocity.getDy());
//      }

//      for (Collidable elem : collidableList) {
//         double minX = Math.min(elem.getCollisionRectangle().getRightSide().getStartPoint().getX(),
//                 elem.getCollisionRectangle().getLeftSide().getStartPoint().getX());
//         double maxX = Math.max(elem.getCollisionRectangle().getRightSide().getStartPoint().getX(),
//                 elem.getCollisionRectangle().getLeftSide().getStartPoint().getX());
//         double minY = Math.min(elem.getCollisionRectangle().getUpperSide().getStartPoint().getY(),
//                 elem.getCollisionRectangle().getBottomSide().getStartPoint().getY());
//         double maxY = Math.max(elem.getCollisionRectangle().getUpperSide().getStartPoint().getY(),
//                 elem.getCollisionRectangle().getBottomSide().getStartPoint().getY());
//
//         if (minX <= point.getX() + velocity.getDx()
//                 && point.getX() + velocity.getDx() <= maxX) {
//            velocity.setDx(-velocity.getDx());
//         }
//
//         if (minY <= point.getY() + velocity.getDy()
//                 && point.getY() + velocity.getDy() <= maxY
//                 && minX <= point.getX() && point.getX() <= maxX) {
//            velocity.setDy(-velocity.getDy());
//         }
//      }
      if(newVel != null && point.distance(collInfo.collisionPoint()) < 12) {
         this.setVelocity(newVel);
      }
      point = this.getVelocity().applyToPoint(point);
   }

   /**
    * @param fromWidth
    * @param fromHeight
    * @param frameWidth
    * @param frameHeight
    */
   public void moveOneStep(int fromWidth, int fromHeight, int frameWidth, int frameHeight) {
      if (frameWidth <= point.getX() + radius + velocity.getDx()
              || point.getX() <= radius + fromWidth - velocity.getDx()) {
         velocity.setDx(-velocity.getDx());
//         System.out.println(point.getX());
      }

      if (frameHeight < point.getY() + radius + velocity.getDy()
              || point.getY() < radius + fromHeight - velocity.getDy()) {
         velocity.setDy(-velocity.getDy());
//         System.out.println(point.getY());
      }
      point = this.getVelocity().applyToPoint(point);
   }
}