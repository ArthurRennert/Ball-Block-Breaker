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
    private String levelName;
    private String timeElapsedToFinishLevel;


    /**
     * @param k
     * @param timeElapsed
     * @param name
     * @param gameScreen
     */
    public BetweenLevels(KeyboardSensor k, String timeElapsed, String name, SpriteCollection gameScreen) {
        this.keyboard = k;
        this.running = false;
        this.gameScreen = gameScreen;
        this.levelName = name;
        this.timeElapsedToFinishLevel = timeElapsed;
    }

    /**
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(DARK_YELLOW);
        d.drawText((int) (d.getWidth() / 11), d.getHeight() / 5, "You finished the "
                + this.levelName + " level in " + timeElapsedToFinishLevel + " minutes. Press the SPACE key to continue to the next level.", 25);
    }

    /**
     * @return - true whether the animation should stop, otherwise false.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}