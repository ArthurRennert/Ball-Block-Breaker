package sprites.backgrounds;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import sprites.backgrounds.infrastructure.Background;

import java.awt.*;

/**
 *
 */
public class Friends extends Background {
   private static final Color LIGHT_BLUE = new Color(51, 153, 255);
   private static final Color SILVER = new Color(192, 192, 192);
   private static final Color LIGHT_GRAY = new Color(211, 211, 211);
   private static final Color DARK_GRAY = new Color(169, 169, 169);

   /**
    *
    */
   public Friends() {}

   /**
    * @param d
    */
   @Override
   public void drawOn(DrawSurface d) {
      Image image = null;
      try {
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Friends.jpg"));
      } catch (Exception e) {
         e.printStackTrace();
      }
      d.drawImage(-25, (int) (ScreenSettings.FRAME_HEIGHT * 0.08), image);
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
