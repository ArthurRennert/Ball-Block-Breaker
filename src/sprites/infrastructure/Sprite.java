package sprites.infrastructure;

import biuoop.DrawSurface;
import utilities.Timer;


/**
 * The Sprite interface represents any object that can be drawn on the screen and updated over time.
 * It is used for all visual game elements such as balls, blocks, backgrounds, and text displays.
 */
public interface Sprite {

    /**
     * Draws the sprite onto the given DrawSurface.
     * This method is responsible for rendering the sprite's current visual representation.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * This method allows the sprite to update its internal state based on the elapsed time.
     *
     * @param timer the Timer object that tracks the elapsed time
     */
    void timePassed(Timer timer);
}