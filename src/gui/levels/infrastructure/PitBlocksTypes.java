package gui.levels.infrastructure;

import gui.ScreenSettings;
import gui.shapes.Point;
import sprites.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for generating pit blocks in the game.
 *
 * Pit blocks are located at the bottom of the screen and are used to detect when balls fall off the screen.
 */
public class PitBlocksTypes {

   /**
    * Returns a list containing the regular pit block.
    *
    * This block is placed along the bottom edge of the screen and is used to detect when balls fall below the play area.
    *
    * @return a list containing a single pit block
    */
   public static List<Block> getRegularPitBlocks() {
      List<Block> resList = new ArrayList<>();
      resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.995), ScreenSettings.FRAME_WIDTH, (int) (ScreenSettings.FRAME_HEIGHT * 0.005), "PitBlock", Color.BLACK)); //lower screen block
      return resList;
   }
}
