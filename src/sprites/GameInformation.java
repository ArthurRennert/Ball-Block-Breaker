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
 * The GameInformation class is a visual component that displays game statistics such as lives, score, time, and level name.
 * It is drawn as a colored bar at the top of the screen and implements the Sprite interface.
 */
public class GameInformation implements Sprite {
   private Rectangle rectangle;
   private Counter score;
   private Counter lives;
   private String levelName;
   private Timer timer;

   /**
    * Constructs a new GameInformation sprite.
    *
    * @param score      the score counter to display
    * @param lives      the lives counter to display
    * @param levelName  the name of the current level
    * @param timer      the game timer used to show elapsed time
    * @param p          the upper-left corner of the display bar
    * @param width      the width of the display bar
    * @param height     the height of the display bar
    */
   public GameInformation(Counter score, Counter lives, String levelName, Timer timer, Point p, int width, int height) {
      rectangle = new Rectangle(p, width, height, "GameInformation", new Color(255, 255, 153)); //old 255, 153, 0
      this.score = score;
      this.lives = lives;
      this.levelName = levelName;
      this.timer = timer;
   }

   /**
    * Draws the game information bar on the screen.
    * It includes lives, score, elapsed time, and level name.
    *
    * @param d the surface to draw on
    */
   @Override
   public void drawOn(DrawSurface d) {
      d.setColor(rectangle.getColor());
      d.fillRectangle((int) rectangle.getXUpperLeftCoordinate(),
              (int) rectangle.getYUpperLeftCoordinate(),
              (int) rectangle.getWidth(),
              (int) rectangle.getHeight());
      d.setColor(Color.BLACK);
      d.drawText((int) (rectangle.getXUpperLeftCoordinate() + rectangle.getWidth() / 80),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Lives: " + String.valueOf(lives.getValue()), 28); //lives
      d.drawText((int) (rectangle.getXUpperLeftCoordinate() + (rectangle.getWidth() / 1.8)),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Score: " + String.valueOf(score.getValue()), 28); //score
      d.drawText((int) (rectangle.getXUpperLeftCoordinate() + (rectangle.getWidth() / 4)),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Time: " + timer.showTimer(), 28); //timer
      d.drawText((int) (rectangle.getXUpperLeftCoordinate() + (rectangle.getWidth() * 0.79)),
              (int) (rectangle.getYUpperLeftCoordinate() + (rectangle.getHeight() / 1.22)), "Level Name: " + levelName, 28); //level name
   }

   /**
    * Updates the state of the game information over time.
    * (Currently unused, but required by the Sprite interface.)
    *
    * @param timer the game's timer instance
    */
   @Override
   public void timePassed(Timer timer) {

   }

   /**
    * Adds this GameInformation sprite to the given game.
    *
    * @param g the GameLevel to add this information bar to
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }
}
