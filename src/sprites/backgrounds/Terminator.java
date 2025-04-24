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
 * The Terminator class represents a dynamic background inspired by the Terminator movie.
 * It extends the Background class and switches between different themed wallpapers based on the timer.
 * The displayed image changes every 3 minutes.
 */
public class Terminator extends Background {
   private static final Color DARK_GREEN = new Color(0, 100, 0);
   private static final Color DARK_YELLOW = new Color(255, 204, 0);
   private static final Color VERY_DARK_GRAY = new Color(51, 51, 51);

   private Image image;
   private int imageToShow;
   private List<Point> wallpapersCoordinates;

   /**
    * Constructs a Terminator background with predefined image positions
    * and loads the initial wallpaper image.
    */
   public Terminator() {
      imageToShow = 0;
      image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator2.jpg"));
      wallpapersCoordinates = new ArrayList<>();
      wallpapersCoordinates.add(new Point(30, (int) (ScreenSettings.FRAME_HEIGHT * 0.08)));
      wallpapersCoordinates.add(new Point(-80, (int) (ScreenSettings.FRAME_HEIGHT * 0.05)));
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
    * Updates the background image based on the game timer.
    * Changes the image every 3 minutes in a rotating cycle.
    *
    * @param timer the game timer used to determine which image to display
    */
   @Override
   public void timePassed(Timer timer) {
      if (timer.getMinute() % 3 == 0) {
         imageToShow = 0;
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator2.jpg"));
      } else if (timer.getMinute() % 3 == 1) {
         imageToShow = 1;
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator.jpg"));
      } else if (timer.getMinute() % 3 == 2) {
         imageToShow = 2;
         image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Terminator3.jpg"));
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
