package sprites;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class SpriteCollection {

    private List<Sprite> listOfSpriteObjects;

    /**
     *
     */
    public SpriteCollection() {
        listOfSpriteObjects = new ArrayList<>();
    }

    /**
     * @param s
     */
    public void addSprite(Sprite s) {
        listOfSpriteObjects.add(s);
    }

    /**
     * @param s
     */
    public void removeSprite(Sprite s) {
//        Iterator<Sprite> it = listOfSpriteObjects.iterator();
//            Sprite sprite = it.next();
//            if (sprite.equals(s)) {
//                it.remove();
//            }
//        }
        listOfSpriteObjects.remove(s);
    }

    public int getNumOfSprites() {
        return listOfSpriteObjects.size();
    }

    /**
     *
     */
    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> copyList = new ArrayList<>(this.listOfSpriteObjects);
        for (Sprite elem : copyList) {
            elem.timePassed();
        }
    }

    /**
     * @param d
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite elem : listOfSpriteObjects) {
            elem.drawOn(d);
        }
    }
}