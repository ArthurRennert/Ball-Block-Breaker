package sprites.backgrounds;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import sprites.backgrounds.infrastructure.Background;

import java.awt.*;

/**
 *
 */
public class Terminator extends Background {
   private static final Color DARK_GREEN = new Color(0, 100, 0);
   private static final Color DARK_YELLOW = new Color(255, 204, 0);
   private static final Color VERY_DARK_GRAY = new Color(51, 51, 51);
   /**
    *
    */
   public Terminator() {}

   /**
    * @param d
    */
   @Override
   public void drawOn(DrawSurface d) {
      Image image = null;
      try {
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator.jpg"));
      } catch (Exception e) {
         e.printStackTrace();
      }
      d.drawImage(-80, (int) (ScreenSettings.FRAME_HEIGHT * 0.05), image);
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
