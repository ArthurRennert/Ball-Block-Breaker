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
//      levels.add(new Friends());
      levels.add(new Terminator());
      gameFlow.runLevels(levels);
   }
}
