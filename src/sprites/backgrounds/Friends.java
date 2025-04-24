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
 * The Friends class is a dynamic background implementation based on the popular TV show theme.
 * It extends the Background class and overrides the drawing behavior to switch between
 * different background images based on the timer. The images are switched every 3 minutes.
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
    * Constructs a Friends background.
    * Initializes the image list and positions for the background images.
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
    * Draws the current background image onto the given DrawSurface.
    *
    * @param d the DrawSurface on which to draw the background
    */
   @Override
   public void drawOn(DrawSurface d) {
      d.drawImage((int) (wallpapersCoordinates.get(imageToShow).getX()), (int) (wallpapersCoordinates.get(imageToShow).getY()), image);
   }

   /**
    * Updates the background image based on the elapsed time.
    * Switches between three images every 3 minutes using modulo logic.
    *
    * @param timer the game timer used to determine the image to show
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
    * Adds this background to the given game level.
    *
    * @param g the GameLevel to which the background should be added
    */
   public void addToGame(GameLevel g) {
      g.addSprite(this);
   }
}
