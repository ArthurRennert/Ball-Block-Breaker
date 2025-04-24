package main;

import biuoop.KeyboardSensor;
import gui.animations.infrastructure.AnimationRunner;
import gui.ScreenSettings;
import gui.levels.*;
import gui.levels.infrastructure.GameFlow;
import gui.levels.infrastructure.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The BallBlockBreaker class is the main entry point for the game.
 * It initializes the animation runner, keyboard sensor, game flow, and level list,
 * then starts running the game.
 */
public class BallBlockBreaker {

   /**
    * The main method that launches the Ball Block Breaker game.
    * It creates an AnimationRunner and GameFlow instance, initializes the levels,
    * and begins the game sequence.
    *
    * @param args command-line arguments (not used)
    */
   public static void main(String[] args) {
      AnimationRunner animationRunner = new AnimationRunner(60, "Ball Block Breaker", ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
      KeyboardSensor keyboardSensor = animationRunner.getGUI().getKeyboardSensor();
      GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
      List<LevelInformation> levels = new ArrayList<>();
      levels.add(new Friends());
      levels.add(new Terminator());
      gameFlow.runLevels(levels);
   }
}
