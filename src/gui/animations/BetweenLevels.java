package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import sprites.infrastructure.SpriteCollection;

import java.awt.Color;

/**
 *
 */
public class BetweenLevels implements Animation {
    private static final Color DARK_YELLOW = new Color(255, 204, 0);

    private KeyboardSensor keyboard;
    private boolean running;
    private SpriteCollection gameScreen;
    private String name;


    /**
     * @param k
     * @param name
     * @param gameScreen
     */
    public BetweenLevels(KeyboardSensor k, String name, SpriteCollection gameScreen) {
        this.keyboard = k;
        this.running = false;
        this.gameScreen = gameScreen;
        this.name = name;
    }

    /**
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(DARK_YELLOW);
        d.drawText((int) (d.getWidth() / 10), d.getHeight() / 5, "You finished the "
                + this.name + ". Press the SPACE key to continue to the next level.", 30);
    }

    /**
     * @return - true whether the animation should stop, otherwise false.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}