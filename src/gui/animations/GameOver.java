package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import sprites.infrastructure.SpriteCollection;

import java.awt.Color;

/**
 * An animation that displays the "Game Over" screen.
 *
 * Shows the final score and a message instructing the player to press SPACE to terminate.
 */
public class GameOver implements Animation {
    private static final Color DARK_YELLOW = new Color(255, 204, 0);

    private KeyboardSensor keyboard;
    private boolean running;
    private SpriteCollection gameScreen;
    private int score;


    /**
     * Creates a new GameOver screen.
     *
     * @param k the keyboard sensor used for input
     * @param gameScreen the background screen to display behind the message
     * @param score the final score to display
     */
    public GameOver(KeyboardSensor k, SpriteCollection gameScreen, int score) {
        this.keyboard = k;
        this.running = true;
        this.gameScreen = gameScreen;
        this.score = score;
    }

    /**
     * Draws one frame of the Game Over screen.
     *
     * @param d the surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(DARK_YELLOW);
        d.drawText((int) (d.getWidth() / 5), d.getHeight() / 5, "Game over. Your score is " +  this.score + ". Press the SPACE key to terminate.", 30);
        running = false;
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