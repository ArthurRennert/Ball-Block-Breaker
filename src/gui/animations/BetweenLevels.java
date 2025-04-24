package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import sprites.infrastructure.SpriteCollection;

import java.awt.Color;

/**
 * An animation screen that is displayed between levels.
 *
 * Shows the level name, time taken to finish the level, and a message prompting the player to continue.
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
     * Creates a new BetweenLevels animation.
     *
     * @param k the keyboard sensor used to detect user input
     * @param minutesElapsedToFinishLevel the number of minutes it took to complete the level
     * @param secondsElapsedToFinishLevel the number of seconds it took to complete the level
     * @param name the name of the level just completed
     * @param gameScreen the sprite collection representing the game screen
     * @param isLastLevel true if this was the last level, false otherwise
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
     * Draws one frame of the between-level animation.
     *
     * @param d the surface to draw on
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
     * Indicates whether the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        return !this.running;
    }
}