package programs;

import biuoop.DrawSurface;
import biuoop.GUI;
import sprites.Ball;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 */
public class MultipleFramesBouncingBallsAnimation {
   //class variables
   private static final int SLEEP_IN_MILLISECONDS = 50;
   private static final int FRAME_WIDTH = 700;
   private static final int FRAME_HEIGHT = 700;

   private static ArrayList<Ball> balls = new ArrayList<>();


   /**
    * @param args - the classic args.
    */
   public static void main(String[] args) {
      String[] arr = {"40", "30", "25", "12", "12", "3", "4", "2", "9", "15", "10"};
      multipleBallsAndFrames(arr);
   }

   static void multipleBallsAndFrames(String[] args) {
      GUI theGui = new GUI("Multiple Balls And Frames Animation", FRAME_WIDTH, FRAME_HEIGHT);
      java.util.Random rand = new java.util.Random();
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      gui.motion.Velocity v;
      for (int i = 0; i < args.length / 2; i++) {
         balls.add(new Ball((double) rand.nextInt(100) + 200,
                    (double) rand.nextInt(100) + 200,
                    Integer.parseInt(args[i]), new Color((int) (Math.random() * 0x1000000))));
         v = gui.motion.Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 10);
         balls.get(i).setVelocity(v);
      }
      for (int i = args.length / 2; i < args.length; i++) {
         balls.add(new Ball((double) rand.nextInt(10) + 500,
                 (double) rand.nextInt(10) + 500,
                 Integer.parseInt(args[i]), new Color((int) (Math.random() * 0x1000000))));
         v = gui.motion.Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 10);
         balls.get(i).setVelocity(v);
      }


      while (true) {
         DrawSurface d = theGui.getDrawSurface();
         d.setColor(Color.GRAY);
         d.fillRectangle(50, 50, 450, 450);
         d.setColor(Color.YELLOW);
         d.fillRectangle(450, 450, 150, 150);

         for (int i = 0; i < args.length / 2; i++) {
            balls.get(i).moveOneStep(50, 50, 500, 500);
            balls.get(i).drawOn(d);
         }

         for (int i = args.length / 2; i < args.length; i++) {
            balls.get(i).moveOneStep(450, 450, 600, 600);
            balls.get(i).drawOn(d);
         }
         theGui.show(d);
         sleeper.sleepFor(SLEEP_IN_MILLISECONDS);  // wait for 50 milliseconds.
      }
   }
}
