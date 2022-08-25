import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 *
 */
public class BouncingBallAnimation {
   //class variables
   private static final int FRAME_WIDTH = 800;
   private static final int FRAME_HEIGHT = 800;
   private static final int MILLISECONDS_IN_SECOND = 1000;
   private static final int SLEEP_IN_MILLISECONDS = 50;
   private static final int FPS = MILLISECONDS_IN_SECOND / SLEEP_IN_MILLISECONDS;


   /**
    * @param args
    */
   public static void main(String[] args) {
      drawAnimation(new Point(40, 80), 10, 90);
//      drawAnimation(new Point(40, 80), 10, 20);
//      drawAnimation();
   }


   /**
    *
    */
   private static void drawAnimation() {
      GUI gui = new GUI("title", FRAME_WIDTH, FRAME_HEIGHT);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      java.util.Random rand = new java.util.Random();
      while (true) {
         DrawSurface d = gui.getDrawSurface();
         Ball ball = new Ball(new Point(rand.nextInt(FRAME_HEIGHT), rand.nextInt(FRAME_WIDTH)), 30, Color.BLACK);
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(SLEEP_IN_MILLISECONDS);
      }
   }

   /**
    * @param start
    * @param dx
    * @param dy
    */
   static void drawAnimation(Point start, double dx, double dy) {
      GUI gui = new GUI("title", FRAME_WIDTH, FRAME_HEIGHT);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
      Velocity v = Velocity.fromAngleAndSpeed(dy + 90, dx);
      ball.setVelocity(v);
      while (true) {
         ball.moveOneStep(FRAME_WIDTH, FRAME_HEIGHT);
         DrawSurface d = gui.getDrawSurface();
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(SLEEP_IN_MILLISECONDS);  // wait for 50 milliseconds.
      }
   }
}
