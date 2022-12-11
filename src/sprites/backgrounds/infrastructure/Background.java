package sprites.backgrounds.infrastructure;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import sprites.infrastructure.Sprite;

import java.awt.*;


/**
 *
 */
public class Background implements Sprite {
   private Color color;

   /**
    *
    */
   public Background() {}

   /**
    *
    */
   public Background(Color c) {
      color = c;
   }


   /**
    * @param d
    */
   @Override
   public void drawOn(DrawSurface d) {
      d.setColor(color);
      d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.09),
              (int) (ScreenSettings.FRAME_WIDTH * 0.94), (int) (ScreenSettings.FRAME_HEIGHT * 0.945));
   }

   /**
    *
    */
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
