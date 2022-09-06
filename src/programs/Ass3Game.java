package programs;

import gui.levels.GameLevel;

/**
 *
 */
public class Ass3Game {
   //class variables

   /**
    * @param args
    */
   public static void main(String[] args) {
      GameLevel gameLevel = new GameLevel();
      gameLevel.initialize();
      gameLevel.run();
   }

}


//      long startTime = System.nanoTime();
//      long endTime = System.nanoTime();
//      System.out.println("Total time: " + (endTime - startTime) / 1000000);