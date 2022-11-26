package sprites;

import biuoop.DrawSurface;
import gui.animations.GameLevel;
import sprites.infrastructure.Sprite;

import java.awt.Color;

/**
 *
 */
public class Background implements Sprite {
   private int frameWidth;
   private int frameHeight;
   private Color color;


   /**
    * @param frameWidth
    * @param frameHeight
    * @param color
    */
   public Background(int frameWidth, int frameHeight, Color color) {
      this.frameWidth = frameWidth;
      this.frameHeight = frameHeight;
      this.color = new Color(color.getRGB());
   }

   /**
    * @param d
    */
   @Override
   public void drawOn(DrawSurface d) {
      d.setColor(color);
      d.fillRectangle((int) (frameWidth * 0.03), (int) (frameHeight * 0.09), (int) (frameWidth * 0.94), (int) (frameHeight * 0.945));
//      d.fillRectangle(50, 50, 50, 30);
   }

   /**
    *
    */
   @Override
   public void timePassed() {

   }

   /**
    * @param g
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }
}
