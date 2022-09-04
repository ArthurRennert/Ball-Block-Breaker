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
   public static final int BALL_SPEED = 10;
   private static final int BALL_ANGLE = 20;

   private static int stepsToNextCollision;
   private static CollisionInfo collInfo;
   private static GameEnvironment ge;
   private static Ball ball;
//   private static int count = 50000;

   /**
    * @param args
    */
   public static void main(String[] args) {
//      long startTime = System.nanoTime();
      GUI gui = new GUI("The game", FRAME_WIDTH, FRAME_HEIGHT);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      ge = new GameEnvironment(FRAME_WIDTH, FRAME_HEIGHT);
      ball = new Ball(new Point(120, 120), 10, Color.BLUE);
      ball.setGameEnvironment(ge);
      ball.setVelocity(Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED));
      List<Collidable> list = ge.getListOfCollidableObjects();
      calcStepsToNextCollision();

      while (true) {
         DrawSurface d = gui.getDrawSurface();
         for (Collidable elem : list) {
            elem.drawOn(d);
         }
         ball.moveOneStep(stepsToNextCollision);
         stepsToNextCollision--;
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(16);

//         count--;
//         if (count == 0) {
//            long endTime = System.nanoTime();
//            System.out.println("Total time: " + (endTime - startTime) / 1000000);
//            System.exit(0);
//         }
      }
   }

   public static void calcStepsToNextCollision() {
      collInfo = ge.getClosestCollision(new Line(ball.getPoint(), new Point(ball.getVelocity().getDx() * 600000, ball.getVelocity().getDy() * 600000)));
      double distanceToCollision = ball.getPoint().distance(collInfo.collisionPoint());
      stepsToNextCollision = (int) (distanceToCollision / BALL_SPEED);
//      System.out.println("stepsToNextCollision: " + stepsToNextCollision);
   }

   public static CollisionInfo getCollInfo () {
      return collInfo;
   }
}
