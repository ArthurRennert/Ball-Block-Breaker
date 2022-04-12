import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 */
public class MultipleBouncingBallsAnimation {

   private static ArrayList<Ball> balls = new ArrayList<>();
   /**
    * @param args
    */
   public static void main(String[] args) {
      String[] arr = {"30", "22", "13", "43", "12", "92", "10", "33"};
      multipleBalls(arr);
   }

   static void multipleBalls(String[] args) {
      GUI gui = new GUI("Bouncing Balls", 800, 800);
      java.util.Random rand = new java.util.Random();
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      for (int i = 0; i < args.length; i++) {
         balls.add(new Ball((double) rand.nextInt(600) + 20, (double) rand.nextInt(600) + 20,
                 Integer.parseInt(args[i]), new Color((int) (Math.random() * 0x1000000))));   //java.awt.Color.BLACK

         int size = Integer.parseInt(args[i]);
         if (0 < size && size <= 10) {
            balls.get(i).setVelocity(rand.nextInt(360), rand.nextInt(10) + 60); //smallest balls' speed between 60-70
         } else if (10 < size && size <= 20) {
            balls.get(i).setVelocity(rand.nextInt(360), rand.nextInt(10) + 50); //small balls' speed between 50-60
         } else if (20 < size && size <= 30) {
            balls.get(i).setVelocity(rand.nextInt(360), rand.nextInt(10) + 40); //medium balls' speed between 40-50
         } else if (30 < size && size <= 40) {
            balls.get(i).setVelocity(rand.nextInt(360), rand.nextInt(10) + 30); //big balls' speed between 30-40
         } else if (40 < size && size < 50) {
            balls.get(i).setVelocity(rand.nextInt(360), rand.nextInt(10) + 20); //very big balls' speed between 20-30
         } else { //size > 50
            balls.get(i).setVelocity(rand.nextInt(360), rand.nextInt(10) + 10); //huge balls' speed between 10-20
         }
      }

      while (true) {
         DrawSurface d = gui.getDrawSurface();
         for (Ball ball: balls) {
            ball.moveOneStep(800, 800);
            ball.drawOn(d);
         }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
      }
   }
}


