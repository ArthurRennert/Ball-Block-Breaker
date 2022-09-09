package programs;

import gui.levels.FirstLevel;
import gui.levels.GameLevel;
import gui.levels.LevelInformation;

/**
 *
 */
public class Ass3Game {
   //class variables

   /**
    * @param args
    */
   public static void main(String[] args) {
      LevelInformation firstLevel = new FirstLevel();
      GameLevel gameLevel = new GameLevel(firstLevel);
      gameLevel.initialize();
      gameLevel.run();
   }

}


//      long startTime = System.nanoTime();
//      long endTime = System.nanoTime();
//      System.out.println("Total time: " + (endTime - startTime) / 1000000);