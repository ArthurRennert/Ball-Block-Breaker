package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import sprites.infrastructure.SpriteCollection;

import java.awt.Color;

/**
 * An animation that displays the "You Win" screen.
 *
 * Shows a congratulatory message with the final score and instructions to terminate.
 */
public class GameWon implements Animation {
    private static final Color DARK_YELLOW = new Color(255, 204, 0);

    private KeyboardSensor keyboard;
    private SpriteCollection gameScreen;
    private boolean running;
    private int score;


    /**
     * Creates a new GameWon screen.
     *
     * @param k the keyboard sensor used for input
     * @param gameScreen the background screen to display behind the message
     * @param score the final score to display
     */
    public GameWon(KeyboardSensor k, SpriteCollection gameScreen, int score) {
        this.keyboard = k;
        this.running = true;
        this.score = score;
        this.gameScreen = gameScreen;
    }

    /**
     * Draws one frame of the "You Win" animation.
     *
     * @param d the surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(DARK_YELLOW);
        d.drawText((int) (d.getWidth() / 5), d.getHeight() / 5, "You Win. Your score is " +  this.score + ". Press the SPACE key to terminate.", 30);
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