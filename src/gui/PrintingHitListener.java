package gui;

public class PrintingHitListener implements collision.HitListener {
   public void hitEvent(sprites.Block beingHit, sprites.Ball hitter) {
      System.out.println("A Block was hit.");
   }
}