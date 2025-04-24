package sprites;

import biuoop.DrawSurface;
import gui.animations.GameLevel;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import sprites.infrastructure.Sprite;
import utilities.Counter;
import utilities.Timer;

import java.awt.Color;

/**
 * The ScoreIndicator class displays the current score at the top of the screen.
 * It is drawn as a rectangle containing the score value.
 */
public class ScoreIndicator implements Sprite {
   private Rectangle rectangle;
   private Counter score;

   /**
    * Constructs a new ScoreIndicator.
    *
    * @param c      the counter that tracks the current score
    * @param p      the top-left position of the score rectangle
    * @param width  the width of the rectangle
    * @param height the height of the rectangle
    */
   public ScoreIndicator(Counter c, Point p, int width, int height) {
      rectangle = new Rectangle(p, width, height, "ScoreIndicator", new Color(255, 255, 153)); //old 255, 153, 0
      score = c;
   }

   /**
    * Draws the score indicator rectangle and displays the score value.
    *
    * @param d the DrawSurface to draw on
    */
   @Override
   public void drawOn(DrawSurface d) {
      d.setColor(rectangle.getColor());
      d.fillRectangle((int) rectangle.getXUpperLeftCoordinate(),
              (int) rectangle.getYUpperLeftCoordinate(),
              (int) rectangle.getWidth(),
              (int) rectangle.getHeight());
      d.setColor(Color.BLACK);
      d.drawText((int) (rectangle.getXUpperLeftCoordinate() + (rectangle.getWidth() / 2)),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Score: " + String.valueOf(score.getValue()), 28);
   }

   /**
    * Notifies the sprite that time has passed.
    * (Unused in this class, but required by the interface.)
    *
    * @param timer the game timer
    */
   @Override
   public void timePassed(Timer timer) {}

   /**
    * Adds the score indicator to the game so that it will be displayed.
    *
    * @param g the GameLevel to add the score indicator to
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }
}
