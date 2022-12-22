package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import sprites.infrastructure.SpriteCollection;

import java.awt.*;

/**
 *
 */
public class Instructions implements Animation {

   private KeyboardSensor keyboard;
   private boolean running;
   private SpriteCollection gameScreen;


   /**
    * @param k
    * @param gameScreen
    */
   public Instructions(KeyboardSensor k, SpriteCollection gameScreen) {
      this.keyboard = k;
      this.running = true;
      this.gameScreen = gameScreen;
   }

   /**
    * @param d
    */
   public void doOneFrame(DrawSurface d) {
      gameScreen.drawAllOn(d);
      d.setColor(Color.YELLOW);
      d.drawText((int) (d.getWidth() / 3), (int) (d.getHeight() / 5.2), "Welcome to Ball Block Breaker game!", 30);
      d.drawText((int) (d.getWidth() / 2.3), (int) (d.getHeight() / 3.9), "Just so you know:", 30);
      d.drawText((int) (d.getWidth() / 4), (int) (d.getHeight() / 3.1), "To move the Paddle left, use the left arrow key or the 'A' key.", 30);
      d.drawText((int) (d.getWidth() / 4.2), (int) (d.getHeight() / 2.6), "To move the Paddle right, use the right arrow key or the 'D' key.", 30);
      d.drawText((int) (d.getWidth() / 3.4), (int) (d.getHeight() / 2.25), "You can see the game stats in the top of the screen.", 30);
      d.drawText((int) (d.getWidth() / 4), (int) (d.getHeight() / 1.95), "You can press the ENTER key anytime to pause the game.", 30);
      d.drawText((int) (d.getWidth() / 3.6), (int) (d.getHeight() / 1.7), "If you are ready for the first level, press the SPACE key.", 30);
      d.setColor(Color.GREEN);
      d.drawText((int) (d.getWidth() / 2.4), (int) (d.getHeight() / 1.3), "GOOD LUCK!", 50);
//      running = false;
   }

   /**
    * @return
    */
   public boolean shouldStop() {
      return !this.running;
   }
}
