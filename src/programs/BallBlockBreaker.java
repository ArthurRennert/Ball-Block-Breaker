package programs;

import biuoop.KeyboardSensor;
import gui.animations.infrastructure.AnimationRunner;
import gui.ScreenSettings;
import gui.levels.*;
import gui.levels.infrastructure.GameFlow;
import gui.levels.infrastructure.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that runs the game.
 */
public class BallBlockBreaker {

   /**
    * @param args
    */
   public static void main(String[] args) {
      AnimationRunner animationRunner = new AnimationRunner(60, "Ball Block Breaker", ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
      KeyboardSensor keyboardSensor = animationRunner.getGUI().getKeyboardSensor();
      GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
      List<LevelInformation> levels = new ArrayList<>();
//      levels.add(new DirectHit());
//      levels.add(new SunnyDay());
//      levels.add(new Aden());
//      levels.add(new Clouds());
      levels.add(new Terminator());
//      levels.add(new Friends());
      gameFlow.runLevels(levels);
   }
}


//      long startTime = System.nanoTime();
//      long endTime = System.nanoTime();
//      System.out.println("Total time: " + (endTime - startTime) / 1000000);