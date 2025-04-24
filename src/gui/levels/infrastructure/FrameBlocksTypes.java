package gui.levels.infrastructure;

import gui.ScreenSettings;
import gui.shapes.Point;
import sprites.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for generating the default frame blocks around the game screen.
 *
 * These blocks form the top, left, and right borders of the game area
 * and are used to keep the ball inside the visible screen.
 */
public class FrameBlocksTypes {

   /**
    * Returns a list of frame blocks with the given color.
    *
    * The list includes:
    * - a top block spanning the width of the screen
    * - a left block along the left edge
    * - a right block along the right edge
    *
    * @param frameColor the color of the frame blocks
    * @return a list of frame blocks
    */
   public static List<Block> getRegularFrameBlocks(Color frameColor) {
      List<Block> resList = new ArrayList<>();
      resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.04), (int) (ScreenSettings.FRAME_WIDTH * 0.999999), (int) (ScreenSettings.FRAME_HEIGHT * 0.05), "FrameBlock", frameColor)); //upper screen block
      resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.09), (int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.91), "FrameBlock", frameColor)); //left screen block
      resList.add(new Block(new Point(ScreenSettings.FRAME_WIDTH * 0.97, ScreenSettings.FRAME_HEIGHT * 0.09), (int) (ScreenSettings.FRAME_WIDTH * 0.02999999), (int) (ScreenSettings.FRAME_HEIGHT * 0.91), "FrameBlock", frameColor)); //right screen block
      return resList;
   }
}
