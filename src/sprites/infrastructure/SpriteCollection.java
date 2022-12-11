package sprites.infrastructure;

import biuoop.DrawSurface;
import java.util.ArrayList;
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
     * @param other
     */
    public SpriteCollection(SpriteCollection other) {
        listOfSpriteObjects = new ArrayList<>(other.listOfSpriteObjects);
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

//    /**
//     * @return
//     */
//    public List<Sprite> getListOfSpriteObjects() {
//        return listOfSpriteObjects;
//    }

    /**
     * @return
     */
    public int getSize() {
        return listOfSpriteObjects.size();
    }

    /**
     * @return
     */
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