package gui;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 *
 */
public class PauseScreen implements Animation {
   private static final Color DARK_YELLOW = new Color(255, 204, 0);

   private KeyboardSensor keyboard;
   private boolean stop;
   private int frameWidth;
   private int frameHeight;
   private SpriteCollection gameScreen;


   /**
    * @param k
    * @param frameWidth
    * @param frameHeight
    */
   public PauseScreen(KeyboardSensor k, int frameWidth, int frameHeight, SpriteCollection gameScreen) {
      this.keyboard = k;
      this.stop = false;
      this.frameWidth = frameWidth;
      this.frameHeight = frameHeight;
      this.gameScreen = gameScreen;
   }

   /**
    * @param d
    */
   public void doOneFrame(DrawSurface d) {
//      d.setColor(new Color(51, 204, 255));
//      d.fillRectangle(0, 0, frameWidth, frameHeight);
      gameScreen.drawAllOn(d);
      d.setColor(DARK_YELLOW);
      d.drawText((int) (d.getWidth() / 3.5), d.getHeight() / 5, "Game paused. Press the ENTER key to continue", 40);
      if (this.keyboard.isPressed(KeyboardSensor.ENTER_KEY)) {
         this.stop = true;
      }
   }

   /**
    * @return
    */
   public boolean shouldStop() {
      return this.stop;
   }
}