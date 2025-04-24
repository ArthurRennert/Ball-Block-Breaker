package gui.animations.infrastructure;

import biuoop.DrawSurface;

/**
 * An interface for animations in the game.
 *
 * Each animation must implement how a single frame is drawn,
 * and indicate when it should stop.
 */
public interface Animation {
   /**
    * Performs one frame of the animation.
    *
    * @param d the surface on which to draw the frame
    */
   void doOneFrame(DrawSurface d);

   /**
    * Indicates whether the animation should stop.
    *
    * @return true if the animation should stop, false otherwise
    */
   boolean shouldStop();
}