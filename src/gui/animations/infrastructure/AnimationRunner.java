package gui.animations.infrastructure;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 *
 */
public class AnimationRunner {
   private GUI gui;
   private int framesPerSecond;

   /**
    * @param fps
    * @param gameName
    * @param frameWidth
    * @param frameHeight
    */
   public AnimationRunner(int fps, String gameName, int frameWidth, int frameHeight) {
      gui = new GUI(gameName, frameWidth, frameHeight);
      framesPerSecond = fps;
   }

   /**
    * @return
    */
   public GUI getGUI() {
      return gui;
   }

   /**
    * @param animation
    */
   // ...
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