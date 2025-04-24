package sprites;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.listeners.hit_listeners.infrastructure.HitListener;
import collision.listeners.hit_notifiers.HitNotifier;
import gui.animations.GameLevel;
import gui.motion.Velocity;
import gui.shapes.Line;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import sprites.infrastructure.Sprite;
import utilities.Counter;
import utilities.Timer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a rectangular block in the game.
 * It can be drawn, detect collisions, and notify listeners when hit.
 * Implements Collidable, Sprite, and HitNotifier.
 */
public class Block implements Collidable, Sprite, HitNotifier {

   private static final double EPSILON = 0.5;

   private Rectangle rectangle;
   public Counter hitsCounter;
   private int value;
   private List<HitListener> hitListeners;


   /**
    * Constructs a new block with given location, size, name, and color.
    *
    * @param p      the upper-left point of the block
    * @param width  the width of the block
    * @param height the height of the block
    * @param name   the name of the block
    * @param c      the color of the block
    */
   public Block(Point p, double width, double height, String name, Color c) {
      rectangle = new Rectangle(p, width, height, name, c);
      hitsCounter = new Counter();
      hitListeners = new ArrayList<>();
   }

   /**
    * Returns the rectangle representing the block's collision shape.
    *
    * @return the collision rectangle
    */
   @Override
   public Rectangle getCollisionRectangle() {
      return rectangle;
   }

   /**
    * Handles a collision with a ball and returns the new velocity after the collision.
    * Also notifies listeners of the hit.
    *
    * @param hitter           the ball that hit the block
    * @param collisionPoint   the collision point
    * @param currentVelocity  the ball's current velocity
    * @return the updated velocity after collision
    */
   @Override
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
      List<Line> sidesList = getCollisionRectangle().getSidesList();
      Line l = null;

      for (Line elem : sidesList) {
         if (Line.isPointOnSegment(elem.getStartPoint(), collisionPoint, elem.getEndPoint())) {
            l = elem;
            break;
         }
      }
      this.notifyHit(hitter);
      if (l.isVerticalLine()) {
         return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
      }
      return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
   }

   /**
    * Draws the block on the given surface.
    *
    * @param surface the DrawSurface to draw on
    */
   public void drawOn(DrawSurface surface) {
      surface.setColor(rectangle.getColor());
      surface.fillRectangle((int) rectangle.getXUpperLeftCoordinate(),
              (int) rectangle.getYUpperLeftCoordinate(),
              (int) rectangle.getWidth(),
              (int) rectangle.getHeight());
      surface.setColor(Color.BLACK);
      surface.drawLine((int) rectangle.getXUpperLeftCoordinate(), (int) rectangle.getYUpperLeftCoordinate(), (int) rectangle.getXUpperRightCoordinate(), (int) rectangle.getYUpperRightCoordinate());
      surface.drawLine((int) rectangle.getXUpperLeftCoordinate(), (int) rectangle.getYUpperLeftCoordinate(), (int) rectangle.getXLowerLeftCoordinate(), (int) rectangle.getYLowerLeftCoordinate());
      surface.drawLine((int) rectangle.getXLowerLeftCoordinate(), (int) rectangle.getYLowerLeftCoordinate(), (int) rectangle.getXLowerRightCoordinate(), (int) rectangle.getYLowerRightCoordinate());
      surface.drawLine((int) rectangle.getXUpperRightCoordinate(), (int) rectangle.getYUpperRightCoordinate(), (int) rectangle.getXLowerRightCoordinate(), (int) rectangle.getYLowerRightCoordinate());
   }

   /**
    * Adds the block to the game (as both a sprite and a collidable).
    *
    * @param g the GameLevel to add to
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
      g.addCollidable(this);
   }

   /**
    * Removes the block from the game.
    *
    * @param gameLevel the GameLevel to remove from
    */
   public void removeFromGame(GameLevel gameLevel) {
      gameLevel.removeSprite(this);
      gameLevel.removeCollidable(this);
   }

   /**
    * Updates the block's state based on time (currently unused).
    *
    * @param timer the timer
    */
   @Override
   public void timePassed(Timer timer) {
   }

   /**
    * Returns a string representation of the block's rectangle.
    *
    * @return a formatted string of the rectangle
    */
   @Override
   public String toString() {
      return utilities.ConsoleColors.RED_UNDERLINED + "Rectangle\n" + utilities.ConsoleColors.RED + rectangle;

   }

   /**
    * Adds a HitListener to be notified when the block is hit.
    *
    * @param hl the HitListener to add
    */
   @Override
   public void addHitListener(HitListener hl) {
      hitListeners.add(hl);
   }

   /**
    * Removes a HitListener from the block.
    *
    * @param hl the HitListener to remove
    */
   @Override
   public void removeHitListener(HitListener hl) {
      hitListeners.remove(hl);
   }

   /**
    * Returns the list of hit listeners registered to this block.
    *
    * @return list of HitListener objects
    */
   public List<HitListener> listOfHitListeners() {
      return hitListeners;
   }

   /**
    * Notifies all listeners that the block was hit by a ball.
    *
    * @param hitter the ball that hit the block
    */
   public void notifyHit(Ball hitter) {
      // Make a copy of the hitListeners before iterating over them.
      List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
      // Notify all listeners about a hit event:
      for (HitListener hl : listeners) {
         hl.hitEvent(this, hitter);
      }
   }

   /**
    * Initializes the number of hits required to destroy the block and sets its score value.
    *
    * @param counter number of hits
    */
   public void initializeHitsCounter(int counter) {
      hitsCounter.setValue(counter);
      value = counter * 5;
   }

   /**
    * Decreases the hit counter by one.
    */
   public void markHit() {
      hitsCounter.decrease(1);
   }

   /**
    * Returns the number of hits left before the block is destroyed.
    *
    * @return remaining hits
    */
   public int hitsLeft() {
      return hitsCounter.getValue();
   }

   /**
    * Returns the point value of the block.
    *
    * @return score value
    */
   public int getValue() {
      return value;
   }

   /**
    * Returns the name of the block.
    *
    * @return block name
    */
   public String getName() {
      return rectangle.getName();
   }
}
