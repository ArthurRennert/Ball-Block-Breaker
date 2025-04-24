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
 * The Ball class represents a moving ball in the game.
 * It handles drawing itself, moving according to its velocity, detecting and responding to collisions,
 * and notifying listeners when it hits a block.
 */
public class Ball implements Sprite, HitNotifier {
   private Point point;
   private final int radius;
   private final Color color;
   private Velocity velocity;
   private GameEnvironment ge;
   private List<HitListener> hitListeners;

   /**
    * Constructs a ball with a given center point, radius, and color.
    *
    * @param center the center point of the ball
    * @param r      the radius of the ball
    * @param color  the color of the ball
    */
   public Ball(Point center, int r, Color color) {
      point = new Point(center);
      radius = r;
      this.color = new Color(color.getRGB());
      velocity = new Velocity(0, 0);
      hitListeners = new ArrayList<>();
   }

   /**
    * Constructs a ball using x and y coordinates for the center.
    *
    * @param x     the x-coordinate of the center
    * @param y     the y-coordinate of the center
    * @param r     the radius
    * @param color the color
    */
   public Ball(double x, double y, int r, Color color) {
      point = new Point(x, y);
      radius = r;
      this.color = new Color(color.getRGB());
      velocity = new Velocity(0, 0);
   }

   /**
    * Constructs a new ball as a deep copy of another ball.
    *
    * @param other the ball to copy
    */
   public Ball(Ball other) {
      this.point = new Point(other.getPoint());
      this.radius = other.getRadius();
      this.color = new Color(other.getColor().getRGB());
      this.velocity = new Velocity(other.getVelocity());
   }

   /**
    * Sets the game environment for collision detection.
    *
    * @param gameEnvironment the game environment
    */
   public void setGameEnvironment(GameEnvironment gameEnvironment) {
      ge = gameEnvironment;
   }

   /**
    * Unsets the game environment.
    */
   public void unsetGameEnvironment() {
      ge = null;
   }


   /**
    * Returns the current game environment.
    *
    * @return the GameEnvironment
    */
   public GameEnvironment getGameEnvironment() {
      return ge;
   }

   /**
    * Returns the radius of the ball.
    *
    * @return radius
    */
   public int getRadius() {
      return radius;
   }

   /**
    * Returns the center point of the ball.
    *
    * @return the center point
    */
   public Point getPoint() {
      return point;
   }

   /**
    * Sets the center point of the ball.
    *
    * @param p the new point
    */
   public void setPoint(Point p) {
      this.point = new Point(p);
   }

   /**
    * Returns the x-coordinate of the ball's center.
    *
    * @return x-coordinate
    */
   public int getX() {
      return (int) point.getX();
   }

   /**
    * Returns the y-coordinate of the ball's center.
    *
    * @return y-coordinate
    */
   public int getY() {
      return (int) point.getY();
   }

   /**
    * Returns the volume of the ball.
    *
    * @return volume (approximation)
    */
   public int getSize() {
      return (int) ((4 * Math.PI * Math.pow(radius, 3)) / 3);
   }

   /**
    * Returns the color of the ball.
    *
    * @return ball color
    */
   public Color getColor() {
      return this.color;
   }

   /**
    * Draws the ball on the given DrawSurface.
    *
    * @param surface the drawing surface
    */
   @Override
   public void drawOn(DrawSurface surface) {
      surface.setColor(this.color);
      surface.fillCircle((int) point.getX(), (int) point.getY(), radius);
      surface.setColor(Color.BLACK);
      surface.drawCircle((int) point.getX(), (int) point.getY(), radius);
   }

   /**
    * Sets the velocity of the ball.
    *
    * @param v the new velocity
    */
   public void setVelocity(Velocity v) {
      velocity = new Velocity(v);
   }

   /**
    * Sets the velocity components dx and dy.
    *
    * @param dx the delta x
    * @param dy the delta y
    */
   public void setVelocity(double dx, double dy) {
      velocity.setDx(dx);
      velocity.setDy(dy);
   }

   /**
    * Returns the current velocity of the ball.
    *
    * @return velocity
    */
   public Velocity getVelocity() {
      return velocity;
   }

   /**
    * Moves the ball one step, bouncing off the screen bounds.
    *
    * @param frameWidth  the width of the frame
    * @param frameHeight the height of the frame
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
    * Adds the ball to the given game level.
    *
    * @param g the game level
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }

   /**
    * Removes the ball from the given game level.
    *
    * @param gameLevel the game level
    */
   public void removeFromGame(GameLevel gameLevel) {
      gameLevel.removeSprite(this);
   }

   /**
    * Updates the ball's position and handles collision detection.
    *
    * @param timer the game timer
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

      int stepsToNextCollision = (int) (minDistance / 10);
      if (stepsToNextCollision <= 1) {
         this.notifyHit((Block) collisionInfo.collisionObject(), this);
         Velocity newVel = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.getVelocity());
         this.setVelocity(newVel);
      }
      point = this.getVelocity().applyToPoint(point);
   }

   /**
    * Notifies all registered hit listeners about a hit event.
    *
    * @param beingHit the block being hit
    * @param hitter   the ball that hit the block
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
    * Moves the ball one step within custom bounds.
    *
    * @param fromWidth   the left bound
    * @param fromHeight  the upper bound
    * @param frameWidth  the right bound
    * @param frameHeight the lower bound
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

   /**
    * Adds a hit listener to the ball.
    *
    * @param hl the hit listener
    */
   @Override
   public void addHitListener(HitListener hl) {
      hitListeners.add(hl);
   }

   /**
    * Removes a hit listener from the ball.
    *
    * @param hl the hit listener
    */
   @Override
   public void removeHitListener(HitListener hl) {
      hitListeners.remove(hl);
   }
}