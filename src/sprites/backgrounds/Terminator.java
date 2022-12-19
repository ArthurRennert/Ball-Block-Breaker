package sprites.backgrounds;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import gui.shapes.Point;
import sprites.backgrounds.infrastructure.Background;
import utilities.Timer;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

/**
 *
 */
public class Terminator extends Background {
   private static final Color DARK_GREEN = new Color(0, 100, 0);
   private static final Color DARK_YELLOW = new Color(255, 204, 0);
   private static final Color VERY_DARK_GRAY = new Color(51, 51, 51);

   private Image image;
   private int imageToShow;
   private List<Point> wallpapersCoordinates;

   /**
    *
    */
   public Terminator() {
      imageToShow = 0;
      image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator.jpg"));
      wallpapersCoordinates = new ArrayList<>();
      wallpapersCoordinates.add(new Point(-80, (int) (ScreenSettings.FRAME_HEIGHT * 0.05)));
      wallpapersCoordinates.add(new Point(30, (int) (ScreenSettings.FRAME_HEIGHT * 0.08)));
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
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator.jpg"));
      } else if (timer.getMinute() % 3 == 1) {
         imageToShow = 1;
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator2.jpg"));
      } else if (timer.getMinute() % 3 == 2) {
         imageToShow = 2;
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator3.jpg"));
      }
   }

   /**
    * @param g
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }
}
