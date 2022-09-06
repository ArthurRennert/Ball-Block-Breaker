package sprites;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.GameEnvironment;
import gui.levels.GameLevel;
import gui.motion.Velocity;
import gui.shapes.Point;

import java.awt.Color;

/**
 *
 */
public class Ball implements Sprite {
   //instance variables
   private Point point;
   private final int radius;
   private final Color color;
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
    * @param other
    */
   public Ball(Ball other) {
      this.point = new Point(other.getPoint());
      this.radius = other.getRadius();
      this.color = new Color(other.getColor().getRGB());
      this.velocity = new Velocity(other.getVelocity());
   }


   /**
    * @param gameEnvironment
    */
   public void setGameEnvironment(GameEnvironment gameEnvironment) {
      ge = gameEnvironment;
   }


   /**
    * @return
    */
   public GameEnvironment getGameEnvironment() {
      return ge;
   }

   /**
    * @return the radius of the Ball object.
    */
   public int getRadius() {
      return radius;
   }

   /**
    * @return - the point object.
    */
   public Point getPoint() {
      return point;
   }

   /**
    * @param p
    */
   public void setPoint(Point p) {
      this.point = new Point(p);
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
    * @param g
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }

   /**
    * @param gameLevel
    */
   public void removeFromGame(GameLevel gameLevel) {
      gameLevel.removeSprite(this);
   }

   /**
    *
    */
   public void timePassed() {
//      if(stepsToNextCollision <= 1) {
      CollisionInfo collInfo =
              ge.getClosestCollision(new gui.shapes.Line(this.getPoint(),
                      new Point(this.getVelocity().getDx() * 600000, this.getVelocity().getDy() * 600000)));

      double distanceToCollision = this.getPoint().distance(collInfo.collisionPoint());
      int stepsToNextCollision = (int) (distanceToCollision / 10);

      if (stepsToNextCollision <= 1) {
//         System.out.println("hit");
         Velocity newVel = collInfo.collisionObject().hit(this, collInfo.collisionPoint(), this.getVelocity());
         this.setVelocity(newVel);
//         ge.updateCollision(collInfo.collisionObject());
      }
//      }
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
      }

      if (frameHeight < point.getY() + radius + velocity.getDy()
              || point.getY() < radius + fromHeight - velocity.getDy()) {
         velocity.setDy(-velocity.getDy());
      }
      point = this.getVelocity().applyToPoint(point);
   }
}