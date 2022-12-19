package sprites.backgrounds;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import sprites.backgrounds.infrastructure.Background;
import utilities.Timer;

import java.awt.Color;

/**
 *
 */
public class Clouds extends Background {
    private static final Color LIGHT_BLUE = new Color(51, 153, 255);
    private static final Color SILVER = new Color(192, 192, 192);
    private static final Color LIGHT_GRAY = new Color(211, 211, 211);
    private static final Color DARK_GRAY = new Color(169, 169, 169);

    /**
     *
     */
    public Clouds() {}

    /**
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(LIGHT_BLUE);
        d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.09),
                (int) (ScreenSettings.FRAME_WIDTH * 0.94), (int) (ScreenSettings.FRAME_HEIGHT * 0.945));
        d.setColor(LIGHT_GRAY);
        for(int i = 0; i <= 20; i++) {
            d.drawLine((int) ((ScreenSettings.FRAME_WIDTH / 3.7) - (7 * i)), (int) (ScreenSettings.FRAME_HEIGHT / 1.4), (int) ((ScreenSettings.FRAME_WIDTH / 4) - (7 * i)), ScreenSettings.FRAME_HEIGHT);
        }
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 4.9), (int) (ScreenSettings.FRAME_HEIGHT / 1.35), 29);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 5.3), (int) (ScreenSettings.FRAME_HEIGHT / 1.4), 29);
        d.setColor(SILVER);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 4.6), (int) (ScreenSettings.FRAME_HEIGHT / 1.43), 40);
        d.setColor(DARK_GRAY);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 4), (int) (ScreenSettings.FRAME_HEIGHT / 1.4), 40);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 4.3), (int) (ScreenSettings.FRAME_HEIGHT / 1.35), 25);


        d.setColor(LIGHT_GRAY);
        for(int i = 0; i <= 13; i++) {
            d.drawLine((int) ((ScreenSettings.FRAME_WIDTH / 1.19) - (10 * i)), (int) (ScreenSettings.FRAME_HEIGHT / 1.22), (int) ((ScreenSettings.FRAME_WIDTH / 1.24) - (10 * i)), ScreenSettings.FRAME_HEIGHT);
        }
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 1.3), (int) (ScreenSettings.FRAME_HEIGHT / 1.24), 25);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 1.28), (int) (ScreenSettings.FRAME_HEIGHT / 1.19), 29);
        d.setColor(SILVER);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 1.25), (int) (ScreenSettings.FRAME_HEIGHT / 1.23), 35);
        d.setColor(DARK_GRAY);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 1.24), (int) (ScreenSettings.FRAME_HEIGHT / 1.18), 23);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 1.22), (int) (ScreenSettings.FRAME_HEIGHT / 1.22), 35);
//        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 5.3), (int) (ScreenSettings.FRAME_HEIGHT / 1.4), 29);
//        d.setColor(SILVER);
//        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 4.6), (int) (ScreenSettings.FRAME_HEIGHT / 1.43), 40);
//        d.setColor(DARK_GRAY);
//        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 4), (int) (ScreenSettings.FRAME_HEIGHT / 1.4), 40);
//        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH / 4.3), (int) (ScreenSettings.FRAME_HEIGHT / 1.35), 25);

    }

    /**
     *
     */
    @Override
    public void timePassed(Timer timer) {

    }

    /**
     * @param g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
