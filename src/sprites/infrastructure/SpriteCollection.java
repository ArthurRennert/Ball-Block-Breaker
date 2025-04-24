package sprites.infrastructure;

import biuoop.DrawSurface;
import utilities.Timer;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class manages a list of Sprite objects.
 * It provides methods to add, remove, update, and draw all the sprites in the collection.
 */
public class SpriteCollection {

    private List<Sprite> listOfSpriteObjects;

    /**
     * Constructs an empty SpriteCollection.
     */
    public SpriteCollection() {
        listOfSpriteObjects = new ArrayList<>();
    }


    /**
     * Constructs a new SpriteCollection as a deep copy of another collection.
     *
     * @param other the SpriteCollection to copy from
     */
    public SpriteCollection(SpriteCollection other) {
        listOfSpriteObjects = new ArrayList<>(other.listOfSpriteObjects);
    }


    /**
     * Adds a new sprite to the collection.
     *
     * @param s the Sprite to add
     */
    public void addSprite(Sprite s) {
        listOfSpriteObjects.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the Sprite to remove
     */
    public void removeSprite(Sprite s) {
        listOfSpriteObjects.remove(s);
    }

    /**
     * Returns the number of sprites currently in the collection.
     *
     * @return the size of the sprite list
     */
    public int getSize() {
        return listOfSpriteObjects.size();
    }

    /**
     * Returns the number of sprites in the collection.
     * This is an alias for getSize().
     *
     * @return number of sprites
     */
    public int getNumOfSprites() {
        return listOfSpriteObjects.size();
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     * This is used to update their state.
     *
     * @param timer the Timer object representing the current time state
     */
    public void notifyAllTimePassed(Timer timer) {
        List<Sprite> copyList = new ArrayList<>(this.listOfSpriteObjects);
        for (Sprite elem : copyList) {
            elem.timePassed(timer);
        }
    }

    /**
     * Draws all the sprites in the collection on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite elem : listOfSpriteObjects) {
            elem.drawOn(d);
        }
    }
}