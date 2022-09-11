package gui;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 *
 */
public class GameWon implements Animation {
    private static final Color DARK_YELLOW = new Color(255, 204, 0);

    private KeyboardSensor keyboard;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int score;


    /**
     * @param k
     * @param score
     */
    public GameWon(KeyboardSensor k, SpriteCollection gameScreen, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.gameScreen = gameScreen;
    }

    /**
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(DARK_YELLOW);
        d.drawText((int) (d.getWidth() / 5), d.getHeight() / 5, "You Win. Your score is " +  this.score + ". Press the SPACE key to terminate.", 40);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * @return
     */
    public boolean shouldStop() {
        return this.stop;
    }
}