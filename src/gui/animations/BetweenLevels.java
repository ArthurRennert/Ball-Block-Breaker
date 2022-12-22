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
    private int minutesElapsedToFinishLevel;
    private int secondsElapsedToFinishLevel;
    private boolean isLastLevel;


    /**
     * @param k
     * @param minutesElapsedToFinishLevel
     * @param secondsElapsedToFinishLevel
     * @param name
     * @param gameScreen
     * @param isLastLevel
     */
    public BetweenLevels(KeyboardSensor k, int minutesElapsedToFinishLevel, int secondsElapsedToFinishLevel, String name, SpriteCollection gameScreen, boolean isLastLevel) {
        this.keyboard = k;
        this.running = false;
        this.gameScreen = gameScreen;
        this.levelName = name;
        this.minutesElapsedToFinishLevel = minutesElapsedToFinishLevel;
        this.secondsElapsedToFinishLevel = secondsElapsedToFinishLevel;
        this.isLastLevel = isLastLevel;
    }

    /**
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(DARK_YELLOW);

        String minutesElapsed = minutesElapsedToFinishLevel == 1 ? "minute" : "minutes";
        minutesElapsed = minutesElapsedToFinishLevel + " " + minutesElapsed;
        String secondsElapsed = secondsElapsedToFinishLevel == 1 ? "second" : "seconds";
        secondsElapsed = secondsElapsedToFinishLevel + " " + secondsElapsed;

        String timeElapsed = minutesElapsed + " and " + secondsElapsed;

        String isLast = isLastLevel ? "move to the next screen." : "continue to the next level.";

        d.drawText((int) (d.getWidth() / 12), d.getHeight() / 5, "You finished the "
                + this.levelName + " level in "
                + timeElapsed + " .Press the SPACE key to " + isLast, 25);
    }

    /**
     * @return - true whether the animation should stop, otherwise false.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}