package sprites.infrastructure;

import biuoop.DrawSurface;
import utilities.Timer;


/**
 *
 */
public interface Sprite {

    /**
     * @param d
     */
    // draw the sprite to the screen
    void drawOn(DrawSurface d);


    /**
     *
     */
    // notify the sprite that time has passed
    void timePassed(Timer timer);
}