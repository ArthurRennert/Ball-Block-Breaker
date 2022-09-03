import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.List;

/**
 *
 */
public class Ass3Game {
   //class variables
   private static final int FRAME_WIDTH = 800;
   private static final int FRAME_HEIGHT = 800;

   private static int count = 5000;

   /**
    * @param args
    */
   public static void main(String[] args) {
      long startTime = System.nanoTime();
      GUI gui = new GUI("The game", FRAME_WIDTH, FRAME_HEIGHT);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      GameEnvironment ge = new GameEnvironment(FRAME_WIDTH, FRAME_HEIGHT);
      Ball ball = new Ball(new Point(120, 120), 10, Color.BLUE);
      ball.setGameEnvironment(ge);
      ball.setVelocity(Velocity.fromAngleAndSpeed(20, 10));
//      System.out.println(ball.getVelocity().getDx());
      List<Collidable> list = ge.getListOfCollidableObjects();

      while (true) {
         DrawSurface d = gui.getDrawSurface();
         d.setColor(Color.GRAY);
         for (Collidable elem : list) {
            System.out.println(elem);
            d.fillRectangle((int) elem.getCollisionRectangle().getXUpperLeftCoordinate(),
                    (int) elem.getCollisionRectangle().getYUpperLeftCoordinate(),
                    (int) elem.getCollisionRectangle().getWidth(),
                    (int) elem.getCollisionRectangle().getHeight());
         }
//         System.out.println(ball.getVelocity().getDy());
         ball.moveOneStep(d);
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(10);  // wait for 50 milliseconds.

         count--;
         if (count == 0) {
            long endTime = System.nanoTime();
            System.out.println("Total time: " + (endTime - startTime) / 1000000);
            System.exit(0);
         }
      }
   }
}
