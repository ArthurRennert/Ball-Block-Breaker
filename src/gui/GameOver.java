package gui;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 *
 */
public class GameOver implements Animation {
    private static final Color DARK_YELLOW = new Color(255, 204, 0);

    private KeyboardSensor keyboard;
    private boolean running;
    private SpriteCollection gameScreen;
    private int score;


    /**
     * @param k
     * @param gameScreen
     * @param score
     */
    public GameOver(KeyboardSensor k, SpriteCollection gameScreen, int score) {
        this.keyboard = k;
        this.running = false;
        this.gameScreen = gameScreen;
        this.score = score;
    }

    /**
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(DARK_YELLOW);
        d.drawText((int) (d.getWidth() / 5), d.getHeight() / 5, "Game over. Your score is " +  this.score + ". Press the SPACE key to terminate.", 40);
    }

    /**
     * @return
     */
    public boolean shouldStop() {
        return !this.running;
    }
}