package gui;

import biuoop.DrawSurface;
import gui.levels.GameLevel;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import sprites.Sprite;
import utilities.Counter;

import java.awt.Color;

/**
 *
 */
public class ScoreIndicator implements Sprite {

   private Rectangle rectangle;
   private Counter score;


   /**
    * @param c
    * @param p
    * @param width
    * @param height
    */
   public ScoreIndicator(Counter c, Point p, int width, int height) {
      rectangle = new Rectangle(p, width, height, "ScoreIndicator", new Color(255, 153, 0));
      score = c;
   }

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

   @Override
   public void timePassed() {

   }

   /**
    * @param g
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }
}
