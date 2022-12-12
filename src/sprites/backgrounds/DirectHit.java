package sprites.backgrounds;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import sprites.backgrounds.infrastructure.Background;

import java.awt.Color;

/**
 *
 */
public class DirectHit extends Background {

   /**
    *
    */
   public DirectHit() {}

   /**
    * @param d
    */
   @Override
   public void drawOn(DrawSurface d) {
      d.setColor(Color.BLACK);
      d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.09),
              (int) (ScreenSettings.FRAME_WIDTH * 0.94), (int) (ScreenSettings.FRAME_HEIGHT * 0.945));
      d.setColor(Color.BLUE);
      d.drawCircle(ScreenSettings.FRAME_WIDTH / 2, (int) (ScreenSettings.FRAME_HEIGHT / 2.7), 50);
      d.drawCircle(ScreenSettings.FRAME_WIDTH / 2, (int) (ScreenSettings.FRAME_HEIGHT / 2.7), 100);
      d.drawCircle(ScreenSettings.FRAME_WIDTH / 2, (int) (ScreenSettings.FRAME_HEIGHT / 2.7), 150);
      d.drawLine((int) (ScreenSettings.FRAME_WIDTH / 1.93), (int) (ScreenSettings.FRAME_HEIGHT / 2.72), (int) (ScreenSettings.FRAME_WIDTH / 1.58), (int) (ScreenSettings.FRAME_HEIGHT / 2.72));
      d.drawLine((int) (ScreenSettings.FRAME_WIDTH / 2.715), (int) (ScreenSettings.FRAME_HEIGHT / 2.72), (int) (ScreenSettings.FRAME_WIDTH / 2.08), (int) (ScreenSettings.FRAME_HEIGHT / 2.72));
      d.drawLine((int) (ScreenSettings.FRAME_WIDTH / 2), (int) (ScreenSettings.FRAME_HEIGHT / 1.58), (int) (ScreenSettings.FRAME_WIDTH / 2), (int) (ScreenSettings.FRAME_HEIGHT / 2.463));
      d.drawLine((int) (ScreenSettings.FRAME_WIDTH / 2), (int) (ScreenSettings.FRAME_HEIGHT / 2.98), (int) (ScreenSettings.FRAME_WIDTH / 2), (int) (ScreenSettings.FRAME_HEIGHT / 9));
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
