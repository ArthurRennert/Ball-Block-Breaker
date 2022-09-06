package gui;

import biuoop.DrawSurface;

/**
 *
 */
public interface Animation {
   /**
    * @param d
    */
   void doOneFrame(DrawSurface d);

   /**
    * @return
    */
   boolean shouldStop();
}