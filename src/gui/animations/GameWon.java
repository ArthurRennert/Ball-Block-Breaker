package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import sprites.infrastructure.SpriteCollection;

import java.awt.Color;

/**
 *
 */
public class GameWon implements Animation {
    private static final Color DARK_YELLOW = new Color(255, 204, 0);

    private KeyboardSensor keyboard;
    private SpriteCollection gameScreen;
    private boolean running;
    private int score;


    /**
     * @param k
     * @param gameScreen
     * @param score
     */
    public GameWon(KeyboardSensor k, SpriteCollection gameScreen, int score) {
        this.keyboard = k;
        this.running = true;
        this.score = score;
        this.gameScreen = gameScreen;
    }

    /**
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(DARK_YELLOW);
        d.drawText((int) (d.getWidth() / 5), d.getHeight() / 5, "You Win. Your score is " +  this.score + ". Press the SPACE key to terminate.", 30);
//        running = false;
    }

    /**
     * @return
     */
    public boolean shouldStop() {
        return !this.running;
    }


//    /**
//     * @param val
//     */
//    public void setShouldStop(boolean val) {
//        running = false;
//    }
}