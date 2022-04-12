import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 *
 */
public class BouncingBallAnimation {

   /**
    * @param args
    */
   public static void main(String[] args) {
      drawAnimation(new Point(40, 80), 2, 1);
//      drawAnimation(new Point(40, 80), 10, 20);
//      drawAnimation();
   }


   /**
    *
    */
   private static void drawAnimation() {
      GUI gui = new GUI("title", 200, 200);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      java.util.Random rand = new java.util.Random();
      while (true) {
         DrawSurface d = gui.getDrawSurface();
         Ball ball = new Ball(new Point(rand.nextInt(200), rand.nextInt(200)), 30, Color.BLACK);
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(50);  // wait for 50 milliseconds.
      }
   }

   /**
    * @param start
    * @param dx
    * @param dy
    */
   static void drawAnimation(Point start, double dx, double dy) {
      GUI gui = new GUI("title", 500, 500); //200, 200
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
      ball.setVelocity(dx, dy);
      Velocity v = Velocity.fromAngleAndSpeed(90, 10);
      ball.setVelocity(v);

      while (true) {
         ball.moveOneStep(500, 500);
         DrawSurface d = gui.getDrawSurface();
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(50);  // wait for 50 milliseconds.
      }
   }
}
