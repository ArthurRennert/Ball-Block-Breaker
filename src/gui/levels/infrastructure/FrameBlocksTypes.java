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
public class FrameBlocksTypes {


   /**
    * @return
    */
   public static List<Block> getRegularFrameBlocks(Color frameColor) {
      List<Block> resList = new ArrayList<>();
      resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.04), (int) (ScreenSettings.FRAME_WIDTH * 0.999999), (int) (ScreenSettings.FRAME_HEIGHT * 0.05), "FrameBlock", frameColor)); //upper screen block
      resList.add(new Block(new Point(0, ScreenSettings.FRAME_HEIGHT * 0.09), (int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.91), "FrameBlock", frameColor)); //left screen block
      resList.add(new Block(new Point(ScreenSettings.FRAME_WIDTH * 0.97, ScreenSettings.FRAME_HEIGHT * 0.09), (int) (ScreenSettings.FRAME_WIDTH * 0.02999999), (int) (ScreenSettings.FRAME_HEIGHT * 0.91), "FrameBlock", frameColor)); //right screen block
      return resList;
   }


}
