import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 */
public class MultipleFramesBouncingBallsAnimation {

   private static final ArrayList<Ball> BALLS = new ArrayList<>();


   /**
    * @param args - the classic args.
    */
   public static void main(String[] args) {
      String[] arr = {"12", "2", "3", "4", "2", "9", "20"};
      multipleBallsAndFrames(args);
   }

   static void multipleBallsAndFrames(String[] args) {
      GUI gui = new GUI("Multiple Balls And Frames Animation", 700, 700);
      java.util.Random rand = new java.util.Random();
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      Velocity v;
      for (int i = 0; i < args.length / 2; i++) {
            BALLS.add(new Ball((double) rand.nextInt(100) + 200,
                    (double) rand.nextInt(100) + 200,
                    Integer.parseInt(args[i]), new Color((int) (Math.random() * 0x1000000))));
         v = Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 10);
         BALLS.get(i).setVelocity(v);
      }
      for (int i = args.length / 2; i < args.length; i++) {
         BALLS.add(new Ball((double) rand.nextInt(10) + 500,
                 (double) rand.nextInt(10) + 500,
                 Integer.parseInt(args[i]), new Color((int) (Math.random() * 0x1000000))));
         v = Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 10);
         BALLS.get(i).setVelocity(v);
      }


      while (true) {
         DrawSurface d = gui.getDrawSurface();
         d.setColor(Color.GRAY);
         d.fillRectangle(50, 50, 450, 450);
         d.setColor(Color.YELLOW);
         d.fillRectangle(450, 450, 150, 150);

         for (int i = 0; i < args.length / 2; i++) {
            BALLS.get(i).moveOneStep(50, 50, 500, 500);
            BALLS.get(i).drawOn(d);
         }

         for (int i = args.length / 2; i < args.length; i++) {
            BALLS.get(i).moveOneStep(450, 450, 600, 600);
            BALLS.get(i).drawOn(d);
         }
         gui.show(d);
         sleeper.sleepFor(50);  // wait for 50 milliseconds.
      }
   }
}
