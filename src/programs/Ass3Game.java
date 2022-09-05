package programs;

/**
 *
 */
public class Ass3Game {
   //class variables

   /**
    * @param args
    */
   public static void main(String[] args) {
      gui.Game game = new gui.Game();
      game.initialize();
      game.run();
   }

}


//      long startTime = System.nanoTime();
//      long endTime = System.nanoTime();
//      System.out.println("Total time: " + (endTime - startTime) / 1000000);