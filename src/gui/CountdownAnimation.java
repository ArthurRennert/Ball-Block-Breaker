package gui;

import biuoop.DrawSurface;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 *
 */
public class CountdownAnimation implements Animation {
   private static final Color DARK_YELLOW = new Color(255, 204, 0);

   private int numOfSeconds;
   private int countFrom;
   private SpriteCollection gameScreen;
   private boolean stop;
   private utilities.Timer timer;

   /**
    * @param numOfSeconds
    * @param countFrom
    * @param gameScreen
    */
   public CountdownAnimation(int numOfSeconds, int countFrom, SpriteCollection gameScreen) {
      this.numOfSeconds = numOfSeconds;
      this.countFrom = countFrom;
      this.gameScreen = gameScreen;
      this.stop = false;
      timer = new utilities.Timer(0, 0, (numOfSeconds + countFrom));
      timer.countdownTimerInit();
   }

   /**
    * @param d
    */
   @Override
   public void doOneFrame(DrawSurface d) {
      gameScreen.drawAllOn(d);
      d.setColor(DARK_YELLOW);
      if (timer.getTimeInSeconds() > countFrom) {
         return;
      } else if (timer.getTimeInSeconds() > 0 && timer.getTimeInSeconds() <= countFrom) {
         d.drawText((int) (d.getWidth() / 2.5), d.getHeight() / 5, "Game starts in " + timer.getTimeInSeconds(), 40);

      } else if (timer.getTimeInSeconds() == 0) {
         d.drawText((int) (d.getWidth() / 2), d.getHeight() / 5, "Go!", 40);
      } else if (timer.getTimeInSeconds() == -1) {
         this.stop = true;
      }
   }

   /**
    * @return
    */
   @Override
   public boolean shouldStop() {
      return this.stop;
   }
}
