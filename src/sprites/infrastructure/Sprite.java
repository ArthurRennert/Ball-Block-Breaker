package sprites.infrastructure;

import biuoop.DrawSurface;


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
    void timePassed();
}