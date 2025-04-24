package gui.animations.infrastructure;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The AnimationRunner is responsible for running animations.
 *
 * It handles the frame rate and rendering loop, calling each animation's
 * doOneFrame method repeatedly until the animation signals it should stop.
 */
public class AnimationRunner {
   private GUI gui;
   private int framesPerSecond;

   /**
    * Creates a new AnimationRunner.
    *
    * @param fps the number of frames per second
    * @param gameName the title of the game window
    * @param frameWidth the width of the window
    * @param frameHeight the height of the window
    */
   public AnimationRunner(int fps, String gameName, int frameWidth, int frameHeight) {
      gui = new GUI(gameName, frameWidth, frameHeight);
      framesPerSecond = fps;
   }

   /**
    * Returns the GUI used by this runner.
    *
    * @return the GUI object
    */
   public GUI getGUI() {
      return gui;
   }

   /**
    * Runs the given animation.
    *
    * Calls the animation's doOneFrame method in a loop, and regulates
    * the timing to maintain the desired frame rate. Stops when shouldStop returns true.
    *
    * @param animation the animation to run
    */
   public void run(Animation animation) {
      int millisecondsPerFrame = 1000 / framesPerSecond;
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      while (!animation.shouldStop()) {
         long startTime = System.currentTimeMillis(); // timing
         DrawSurface d = gui.getDrawSurface();

         animation.doOneFrame(d);

         gui.show(d);
         long usedTime = System.currentTimeMillis() - startTime;
         long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
         if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
         }
      }
   }
}