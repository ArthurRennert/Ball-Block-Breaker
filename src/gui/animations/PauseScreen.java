package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import sprites.infrastructure.SpriteCollection;

import java.awt.Color;

/**
 * An animation that displays a pause screen during the game.
 *
 * Instructs the player to press the SPACE key to continue.
 */
public class PauseScreen implements Animation {
   private static final Color DARK_YELLOW = new Color(255, 204, 0);

   private KeyboardSensor keyboard;
   private boolean running;
   private SpriteCollection gameScreen;

   /**
    * Creates a new PauseScreen.
    *
    * @param k the keyboard sensor used to detect key presses
    * @param gameScreen the background screen to display behind the pause message
    */
   public PauseScreen(KeyboardSensor k, SpriteCollection gameScreen) {
      this.keyboard = k;
      this.running = true;
      this.gameScreen = gameScreen;
   }

   /**
    * Draws one frame of the pause screen.
    *
    * Displays a message prompting the user to press SPACE to continue.
    *
    * @param d the surface to draw on
    */
   public void doOneFrame(DrawSurface d) {
      gameScreen.drawAllOn(d);
      d.setColor(DARK_YELLOW);
      d.drawText((int) (d.getWidth() / 3.5), d.getHeight() / 13, "Game paused. Press the SPACE key to continue.", 30);
   }

   /**
    * Indicates whether the animation should stop.
    *
    * @return true if the animation should stop, false otherwise
    */
   public boolean shouldStop() {
      return !this.running;
   }
}