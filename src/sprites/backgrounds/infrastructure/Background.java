package sprites.backgrounds.infrastructure;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import sprites.infrastructure.Sprite;
import utilities.Timer;

import java.awt.*;


/**
 * The Background class represents the base visual background of a level.
 * It implements the Sprite interface and is responsible for drawing a solid-colored
 * rectangle that serves as the main playing area background.
 */
public class Background implements Sprite {
   private Color color;

   /**
    * Constructs a default Background without setting a color.
    * A color should be set manually before drawing.
    */
   public Background() {}

   /**
    * Constructs a Background with the specified color.
    *
    * @param c the color of the background
    */
   public Background(Color c) {
      color = c;
   }


   /**
    * Draws the background on the given DrawSurface.
    * It fills a large rectangle that represents the playing area.
    *
    * @param d the drawing surface
    */
   @Override
   public void drawOn(DrawSurface d) {
      d.setColor(color);
      d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.09),
              (int) (ScreenSettings.FRAME_WIDTH * 0.94), (int) (ScreenSettings.FRAME_HEIGHT * 0.945));
   }

   /**
    * Called when time has passed in the game.
    * This background is static, so no action is taken.
    *
    * @param timer the game timer
    */
   @Override
   public void timePassed(Timer timer) {

   }

   /**
    * Adds this background sprite to the game level's sprite collection.
    *
    * @param g the GameLevel to which the background should be added
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }
}
