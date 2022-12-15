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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

   private static final double EPSILON = 0.5;

   private Rectangle rectangle;
   private Counter hitsCounter;
   int value;
   private List<HitListener> hitListeners;


   /**
    * @param p
    * @param width
    * @param height
    * @param name
    * @param c
    */
   public Block(Point p, double width, double height, String name, Color c) {
      rectangle = new Rectangle(p, width, height, name, c);
      hitsCounter = new Counter();
      hitListeners = new ArrayList<>();
   }


   @Override
   public Rectangle getCollisionRectangle() {
      return rectangle;
   }

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
    * @param surface
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
    * @param g
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
      g.addCollidable(this);
   }


   /**
    * @param gameLevel
    */
   public void removeFromGame(GameLevel gameLevel) {
      gameLevel.removeSprite(this);
      gameLevel.removeCollidable(this);
   }

   /**
    *
    */
   @Override
   public void timePassed() {
//      if (rectangle.getColor().getRGB() == Color.GREEN.getRGB()) {
//         rectangle.setColor(Color.RED);
//      }
   }


   @Override
   public String toString() {
      return utilities.ConsoleColors.RED_UNDERLINED + "Rectangle\n" + utilities.ConsoleColors.RED + rectangle;

   }

   @Override
   public void addHitListener(HitListener hl) {
      hitListeners.add(hl);
   }

   @Override
   public void removeHitListener(HitListener hl) {
      hitListeners.remove(hl);
   }

   /**
    * @return
    */
   public List<HitListener> listOfHitListeners() {
      return hitListeners;
   }

   /**
    * @param hitter
    */
   private void notifyHit(Ball hitter) {
      // Make a copy of the hitListeners before iterating over them.
      List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
      // Notify all listeners about a hit event:
      for (HitListener hl : listeners) {
         hl.hitEvent(this, hitter);
      }
   }

   public void initializeHitsCounter(int counter) {
      hitsCounter.setValue(counter);
      value = counter * 5;
   }

   public void markHit() {
      hitsCounter.decrease(1);
   }

   public int hitsLeft() {
      return hitsCounter.getValue();
   }

   public int getValue() {
      return value;
   }
}
