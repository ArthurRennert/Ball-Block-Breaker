package sprites;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.GameEnvironment;
import collision.listeners.hit_listeners.infrastructure.HitListener;
import collision.listeners.hit_notifiers.HitNotifier;
import gui.animations.GameLevel;
import gui.motion.Velocity;
import gui.shapes.Point;
import sprites.infrastructure.Sprite;
import utilities.Timer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Ball implements Sprite, HitNotifier {
   //instance variables
   private Point point;
   private final int radius;
   private final Color color;
   private Velocity velocity;
   private GameEnvironment ge;
   private List<HitListener> hitListeners;



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
      hitListeners = new ArrayList<>();
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
    *
    */
   public void unsetGameEnvironment() {
      ge = null;
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
      surface.setColor(Color.BLACK);
      surface.drawCircle((int) point.getX(), (int) point.getY(), radius);
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
      if (frameWidth <= point.getX() + radius || point.getX() <= radius) {
         velocity.setDx(-velocity.getDx());
      }

      if (frameHeight <= point.getY() + radius || point.getY() <= radius) {
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
   @Override
   public void timePassed(Timer timer) {
//      if(stepsToNextCollision <= 1) {
      CollisionInfo collInfoCenterBall =
              ge.getClosestCollision(new gui.shapes.Line(this.getPoint(),
                      new Point(this.getVelocity().getDx() * 600000, this.getVelocity().getDy() * 600000)));

      Point leftFromCenter = new Point(this.getPoint().getX() - (this.getRadius()), this.getPoint().getY());
      CollisionInfo collInfoLeftBall =
              ge.getClosestCollision(new gui.shapes.Line(leftFromCenter,
                      new Point(this.getVelocity().getDx() * 600000, this.getVelocity().getDy() * 600000)));

      Point rightFromCenter = new Point(this.getPoint().getX() + (this.getRadius()), this.getPoint().getY());
      CollisionInfo collInfoRightBall =
              ge.getClosestCollision(new gui.shapes.Line(rightFromCenter,
                      new Point(this.getVelocity().getDx() * 600000, this.getVelocity().getDy() * 600000)));


      double distanceToCollisionFromCenterBall = this.getPoint().distance(collInfoCenterBall.collisionPoint());
      double distanceToCollisionFromLeftBall = this.getPoint().distance(collInfoLeftBall.collisionPoint());
      double distanceToCollisionFromRightBall = this.getPoint().distance(collInfoRightBall.collisionPoint());


      CollisionInfo collisionInfo = collInfoCenterBall;
      double minDistance = distanceToCollisionFromCenterBall;
      if (distanceToCollisionFromLeftBall < distanceToCollisionFromCenterBall && distanceToCollisionFromLeftBall < distanceToCollisionFromRightBall) {
         collisionInfo = collInfoLeftBall;
         minDistance = distanceToCollisionFromLeftBall;
      } else if (distanceToCollisionFromRightBall < distanceToCollisionFromCenterBall && distanceToCollisionFromRightBall < distanceToCollisionFromLeftBall) {
         collisionInfo = collInfoRightBall;
         minDistance = distanceToCollisionFromRightBall;
      }

//      if (collisionInfo == collInfoCenterBall) {
//         System.out.println("center\n");
//      } else if (collisionInfo == collInfoRightBall) {
//         System.out.println("right\n");
//      } else {
//         System.out.println("left\n");
//      }

      int stepsToNextCollision = (int) (minDistance / 10);
      if (stepsToNextCollision <= 1) {
         this.notifyHit((Block) collisionInfo.collisionObject(), this);
//         System.out.println("hit");
         Velocity newVel = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.getVelocity());
         this.setVelocity(newVel);
//         ge.updateCollision(collInfo.collisionObject());
      }
//      }
      point = this.getVelocity().applyToPoint(point);
   }


   /**
    * @param beingHit
    * @param hitter
    */
   public void notifyHit(Block beingHit, Ball hitter) {
      // Make a copy of the hitListeners before iterating over them.
      List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
      // Notify all listeners about a hit event:
      for (HitListener hl : listeners) {
         hl.hitEvent(beingHit, this);
      }
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

   @Override
   public void addHitListener(HitListener hl) {
      hitListeners.add(hl);
   }

   @Override
   public void removeHitListener(HitListener hl) {
      hitListeners.remove(hl);
   }
}