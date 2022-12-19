package sprites.backgrounds;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import gui.shapes.Point;
import sprites.backgrounds.infrastructure.Background;
import utilities.Timer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Friends extends Background {
   private static final Color LIGHT_BLUE = new Color(51, 153, 255);
   private static final Color SILVER = new Color(192, 192, 192);
   private static final Color LIGHT_GRAY = new Color(211, 211, 211);
   private static final Color DARK_GRAY = new Color(169, 169, 169);

   private Image image;
   private int imageToShow;
   private List<Point> wallpapersCoordinates;

   /**
    *
    */
   public Friends() {
      imageToShow = 0;
      image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Friends.jpg"));
      wallpapersCoordinates = new ArrayList<>();
      wallpapersCoordinates.add(new Point(-25, (int) (ScreenSettings.FRAME_HEIGHT * 0.08)));
      wallpapersCoordinates.add(new Point(-35, (int) (ScreenSettings.FRAME_HEIGHT * 0.08)));
      wallpapersCoordinates.add(new Point(0, (int) (ScreenSettings.FRAME_HEIGHT * 0.05)));

   }

   /**
    * @param d
    */
   @Override
   public void drawOn(DrawSurface d) {
      d.drawImage((int) (wallpapersCoordinates.get(imageToShow).getX()), (int) (wallpapersCoordinates.get(imageToShow).getY()), image);
   }

   /**
    *
    */
   @Override
   public void timePassed(Timer timer) {
      if (timer.getMinute() % 3 == 0) {
         imageToShow = 0;
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Friends.jpg"));
      } else if (timer.getMinute() % 3 == 1) {
         imageToShow = 1;
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Friends2.jpg"));
      } else if (timer.getMinute() % 3 == 2) {
         imageToShow = 2;
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Friends3.jpg"));
      }
   }

   /**
    * @param g
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }
}
