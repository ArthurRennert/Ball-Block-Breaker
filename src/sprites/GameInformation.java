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
 *
 */
public class GameInformation implements Sprite {

   private Rectangle rectangle;
   private Counter score;
   private Counter lives;
   private String levelName;
   private Timer timer;


   /**
    * @param score
    * @param lives
    * @param levelName
    * @param timer
    * @param p
    * @param width
    * @param height
    */
   public GameInformation(Counter score, Counter lives, String levelName, Timer timer, Point p, int width, int height) {
      rectangle = new Rectangle(p, width, height, "GameInformation", new Color(255, 255, 153)); //old 255, 153, 0
      this.score = score;
      this.lives = lives;
      this.levelName = levelName;
      this.timer = timer;
   }

   @Override
   public void drawOn(DrawSurface d) {
      d.setColor(rectangle.getColor());
      d.fillRectangle((int) rectangle.getXUpperLeftCoordinate(),
              (int) rectangle.getYUpperLeftCoordinate(),
              (int) rectangle.getWidth(),
              (int) rectangle.getHeight());
      d.setColor(Color.BLACK);
      d.drawText((int) (rectangle.getXUpperLeftCoordinate() + (rectangle.getWidth() / 2.3)),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Score: " + String.valueOf(score.getValue()), 28); //score
      d.drawText((int) (rectangle.getXUpperLeftCoordinate()),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Lives: " + String.valueOf(lives.getValue()), 28); //lives
      d.drawText((int) (rectangle.getXUpperLeftCoordinate() + (rectangle.getWidth() / 5)),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Time: " + timer.showTimer(), 28); //timer
      d.drawText((int) (rectangle.getXUpperLeftCoordinate() + (rectangle.getWidth() * 0.78)),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Level Name: " + levelName, 28); //level name
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
