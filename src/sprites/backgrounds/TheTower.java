package sprites.backgrounds;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import sprites.backgrounds.infrastructure.Background;

import java.awt.Color;

/**
 *
 */
public class TheTower extends Background {
    private static final Color DARK_GREEN = new Color(0, 100, 0);
    /**
     *
     */
    public TheTower() {}

    /**
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(DARK_GREEN);
        d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.09),
                (int) (ScreenSettings.FRAME_WIDTH * 0.94), (int) (ScreenSettings.FRAME_HEIGHT * 0.945));
    }

    /**
     *
     */
    @Override
    public void timePassed() {

    }

    /**
     * @param g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
