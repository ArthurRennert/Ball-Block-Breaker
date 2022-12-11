package gui.levels.infrastructure;

import gui.ScreenSettings;
import gui.shapes.Point;
import sprites.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PitBlocksTypes {


   /**
    * @return
    */
   public static List<Block> getRegularPitBlocks() {
      List<Block> resList = new ArrayList<>();
      resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.995), ScreenSettings.FRAME_WIDTH, (int) (ScreenSettings.FRAME_HEIGHT * 0.005), "LowerScreen", Color.BLACK)); //lower screen block
      return resList;
   }
}
