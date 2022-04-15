import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.List;

/**
 *
 */
public class Ass3Game {

   /**
    * @param args
    */
   public static void main(String[] args) {

      GUI gui = new GUI("The game", 800, 800);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      GameEnvironment ge = new GameEnvironment(800, 800);
      Ball ball = new Ball(new Point(300, 300), 10, Color.BLACK);
      ball.setGameEnvironment(ge);
      ball.setVelocity(Velocity.fromAngleAndSpeed(20, 10));
//      System.out.println(ball.getVelocity().getDx());
      List<Collidable> list = ge.getListOfCollidableObjects();

      while (true) {
         DrawSurface d = gui.getDrawSurface();
         d.setColor(Color.BLUE);
         for (Collidable elem : list) {
            d.fillRectangle((int) elem.getCollisionRectangle().getUpperLeft().getX(),
                    (int) elem.getCollisionRectangle().getUpperLeft().getY(),
                    (int) elem.getCollisionRectangle().getUpperLeft().getX()
                            + (int) elem.getCollisionRectangle().getWidth(),
                    (int) elem.getCollisionRectangle().getUpperLeft().getY()
                            + (int) elem.getCollisionRectangle().getHeight());
         }
//         System.out.println(ball.getVelocity().getDy());
         ball.moveOneStep();
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(20);  // wait for 50 milliseconds.
      }
   }
}
