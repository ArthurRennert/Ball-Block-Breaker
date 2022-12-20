package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import sprites.infrastructure.SpriteCollection;

import java.awt.Color;

/**
 *
 */
public class PauseScreen implements Animation {
   private static final Color DARK_YELLOW = new Color(255, 204, 0);

   private KeyboardSensor keyboard;
   private boolean running;
   private SpriteCollection gameScreen;


   /**
    * @param k
    * @param gameScreen
    */
   public PauseScreen(KeyboardSensor k, SpriteCollection gameScreen) {
      this.keyboard = k;
      this.running = true;
      this.gameScreen = gameScreen;
   }

   /**
    * @param d
    */
   public void doOneFrame(DrawSurface d) {
      gameScreen.drawAllOn(d);
      d.setColor(DARK_YELLOW);
      d.drawText((int) (d.getWidth() / 3.5), d.getHeight() / 13, "Game paused. Press the SPACE key to continue.", 30);
//      running = false;
   }

   /**
    * @return
    */
   public boolean shouldStop() {
      return !this.running;
   }
}