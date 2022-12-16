package programs;

import biuoop.KeyboardSensor;
import collision.listeners.hit_listeners.SoundMaker;
import gui.animations.infrastructure.AnimationRunner;
import gui.ScreenSettings;
import gui.levels.*;
import gui.levels.infrastructure.GameFlow;
import gui.levels.infrastructure.LevelInformation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
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

//      SoundMaker s = new SoundMaker();
//      Sound b = new Sound("/Terminator.wav");
//      playMusic(b.getSound());

      AnimationRunner animationRunner = new AnimationRunner(60, "Ball Block Breaker", ScreenSettings.FRAME_WIDTH, ScreenSettings.FRAME_HEIGHT);
      KeyboardSensor keyboardSensor = animationRunner.getGUI().getKeyboardSensor();
      GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
      List<LevelInformation> levels = new ArrayList<>();
//      levels.add(new DirectHit());
//      levels.add(new WideEasy());
      levels.add(new TheTower());
      levels.add(new InClouds());
      gameFlow.runLevels(levels);
   }
}


//      long startTime = System.nanoTime();
//      long endTime = System.nanoTime();
//      System.out.println("Total time: " + (endTime - startTime) / 1000000);