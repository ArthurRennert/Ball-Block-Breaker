package programs;

import biuoop.KeyboardSensor;
import gui.Animation;
import gui.AnimationRunner;
import gui.GameWon;
import gui.ScreenSettings;
import gui.levels.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Ass3Game {

   /**
    * @param args
    */
   public static void main(String[] args) {
      AnimationRunner animationRunner = new AnimationRunner(60, "Ball Block Breaker", ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
      KeyboardSensor keyboardSensor = animationRunner.getGUI().getKeyboardSensor();
      GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
      List<LevelInformation> levels = new ArrayList<>();
      levels.add(new TestLevel());
      levels.add(new TestLevel2());
//      levels.add(new FirstLevel());
//      levels.add(new RandomLevel());
      gameFlow.runLevels(levels);
   }
}


//      long startTime = System.nanoTime();
//      long endTime = System.nanoTime();
//      System.out.println("Total time: " + (endTime - startTime) / 1000000);