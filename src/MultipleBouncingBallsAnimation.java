import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 */
public class MultipleBouncingBallsAnimation {
   //class variables
   private static final int FRAME_WIDTH = 800;
   private static final int FRAME_HEIGHT = 800;
   private static final int SLEEP_IN_MILLISECONDS = 50;

   private static ArrayList<Ball> balls = new ArrayList<>();
   /**
    * @param args
    */
   public static void main(String[] args) {
      String[] arr = {"30", "22", "13", "43", "12", "92", "10", "33"};
      multipleBalls(arr);
   }

   static void multipleBalls(String[] args) {
      GUI gui = new GUI("Multiple Bouncing Balls Animation", 800, 800);
      java.util.Random rand = new java.util.Random();
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      for (int i = 0; i < args.length; i++) {
         balls.add(new Ball((double) rand.nextInt(200) + 200, (double) rand.nextInt(200) + 200,
                 Integer.parseInt(args[i]), new Color((int) (Math.random() * 0x1000000))));   //java.awt.Color.BLACK
         balls.get(i).setVelocity(velocityDeterminer(balls.get(i).getSize()));
      }

      while (true) {
         DrawSurface d = gui.getDrawSurface();
         for (Ball ball: balls) {
            ball.moveOneStep(FRAME_WIDTH, FRAME_HEIGHT);
            ball.drawOn(d);
         }
            gui.show(d);
            sleeper.sleepFor(SLEEP_IN_MILLISECONDS);
      }
   }

   static Velocity velocityDeterminer(int size) {
      java.util.Random rand = new java.util.Random();
      if (0 < size && size <= 10) { //smallest balls' speed between 60-70
         return Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 60);
      } else if (10 < size && size <= 20) {  //small balls' speed between 50-60
         return Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 50);
      } else if (20 < size && size <= 30) {  //medium balls' speed between 40-50
         return Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 40);
      } else if (30 < size && size <= 40) {  //big balls' speed between 30-40
         return Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 30);
      } else if (40 < size && size < 50) {   //very big balls' speed between 20-30
         return Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 20);
      } else { //size > 50 , huge balls' speed between 10-20
         return Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 10);
      }
   }
}


