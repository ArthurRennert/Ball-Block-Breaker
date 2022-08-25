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
   /**
    * @param args
    */
   public static void main(String[] args) {

      GUI gui = new GUI("The game", 800, 800);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      GameEnvironment ge = new GameEnvironment(FRAME_WIDTH, FRAME_HEIGHT);
      Ball ball = new Ball(new Point(300, 300), 10, Color.BLACK);
      ball.setGameEnvironment(ge);
      ball.setVelocity(Velocity.fromAngleAndSpeed(20, 10));
//      System.out.println(ball.getVelocity().getDx());
      List<Collidable> list = ge.getListOfCollidableObjects();

      while (true) {
         DrawSurface d = gui.getDrawSurface();
         d.setColor(Color.GRAY);
         for (Collidable elem : list) {
            d.fillRectangle((int) elem.getCollisionRectangle().getXUpperLeftCoordinate(),
                    (int) elem.getCollisionRectangle().getYUpperLeftCoordinate(),
                    (int) elem.getCollisionRectangle().getXUpperLeftCoordinate()
                            + (int) elem.getCollisionRectangle().getWidth(),
                    (int) elem.getCollisionRectangle().getYUpperLeftCoordinate()
                            + (int) elem.getCollisionRectangle().getHeight());
         }
//         System.out.println(ball.getVelocity().getDy());
         ball.moveOneStep(d);
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(200);  // wait for 50 milliseconds.
      }
   }
}
