/**
 * Class block.
 */
public class Block implements Collidable {

   private Rectangle rectangle;


   /**
    * @param p
    * @param width
    * @param height
    */
   public Block(Point p, int width, int height) {
      rectangle = new Rectangle(p, width, height);
   }

   @Override
   public Rectangle getCollisionRectangle() {
      return rectangle;
   }

   @Override
   public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
      return null;
   }
}
