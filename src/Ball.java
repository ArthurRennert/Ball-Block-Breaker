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
      ge.decStepsToNextColl();
      System.out.println("steps: " + ge.getStepsToNextColl());
      if (ge.getStepsToNextColl() == 1) {
         List<Collidable> collidableList = ge.getListOfCollidableObjects();
//      System.out.println(collidableList);

         System.out.println("\n\n\nYES\n\n\n");
         CollisionInfo collInfo = ge.getClosestCollision(new Line(point,
                 new Point(this.getVelocity().getDx() * 600000, this.getVelocity().getDy() * 600000)), this);

         System.out.println(collInfo);
         double distUnit = point.distance(new Point(point.getX() + this.getVelocity().getDx(), point.getY() + this.getVelocity().getDy()));
         double totalDist = point.distance(collInfo.collisionPoint());
         int numOfUnits = (int) ((totalDist / distUnit) + 1);
         System.out.println("distUnit: " + distUnit + "\ntotalDist: " + totalDist + "\nnumOfUnits: " + ((int) ((totalDist / distUnit) - 1)));


         Velocity newVel = null;
         if (numOfUnits == 1) {
            newVel = collInfo.collisionObject().hit(point, collInfo.collisionPoint(), this.getVelocity());
            int ind = collidableList.indexOf(collInfo.collisionObject());
            collidableList.get(ind).decCntToDis();
            if (collidableList.get(ind).getCntToDis() == 0)
               collidableList.remove(collInfo.collisionObject());
         }


         if (newVel != null && point.distance(collInfo.collisionPoint()) < getRadius()) {
            this.setVelocity(newVel);
         }
         ge.resetStepsToNextColl();
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