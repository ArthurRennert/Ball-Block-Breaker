import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {

    private List<Sprite> listOfSpriteObjects;

    public SpriteCollection() {
        listOfSpriteObjects = new ArrayList<>();
    }

    public void addSprite(Sprite s) {
        listOfSpriteObjects.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite elem : listOfSpriteObjects) {
            elem.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite elem : listOfSpriteObjects) {
            elem.drawOn(d);
        }
    }
}