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
public class Aden extends Background {
    private static final Color DARK_GREEN = new Color(0, 100, 0);
    private static final Color DARK_YELLOW = new Color(255, 204, 0);
    private static final Color VERY_DARK_GRAY = new Color(51, 51, 51);
    /**
     *
     */
    public Aden() {}

    /**
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(DARK_GREEN);
        d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.09),
                (int) (ScreenSettings.FRAME_WIDTH * 0.94), (int) (ScreenSettings.FRAME_HEIGHT * 0.945));

        //the tower
        d.setColor(VERY_DARK_GRAY);
        d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.1 - 6), (int) (ScreenSettings.FRAME_HEIGHT / 2.7), 12, ScreenSettings.FRAME_HEIGHT);
        d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.1 - 18), (int) (ScreenSettings.FRAME_HEIGHT / 1.45), 36, ScreenSettings.FRAME_HEIGHT);
        d.setColor(Color.BLACK);
        d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.1 - 54), (int) (ScreenSettings.FRAME_HEIGHT / 1.32), 108, ScreenSettings.FRAME_HEIGHT);
        d.setColor(Color.WHITE);
        d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.1 - 41), (int) (ScreenSettings.FRAME_HEIGHT / 1.29), 82, ScreenSettings.FRAME_HEIGHT);
        d.setColor(Color.BLACK);
        int rectWidth = 8;
        int betweenRects = 10;
        for (int i = 1; i <= 4; i++) {
            d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.1 - 41 + (betweenRects * i + (i-1) * rectWidth)), (int) (ScreenSettings.FRAME_HEIGHT / 1.29), rectWidth, ScreenSettings.FRAME_HEIGHT);
        }
        int rectHeight = 7;
        betweenRects = 30;
        for (int i = 1; i <= 5; i++) {
            d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.1 - 41), (int) (ScreenSettings.FRAME_HEIGHT / 1.29 + ((betweenRects * i + (i-1) * rectHeight))) , 82, rectHeight);
        }
        d.setColor(DARK_YELLOW);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH * 0.1), (int) (ScreenSettings.FRAME_HEIGHT / 2.7), 12);
        d.setColor(Color.RED);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH * 0.1), (int) (ScreenSettings.FRAME_HEIGHT / 2.7), 8);
        d.setColor(Color.WHITE);
        d.fillCircle((int) (ScreenSettings.FRAME_WIDTH * 0.1), (int) (ScreenSettings.FRAME_HEIGHT / 2.7), 3);
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
