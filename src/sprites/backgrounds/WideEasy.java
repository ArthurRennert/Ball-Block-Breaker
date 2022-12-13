package sprites.backgrounds;

import biuoop.DrawSurface;
import gui.ScreenSettings;
import gui.animations.GameLevel;
import sprites.backgrounds.infrastructure.Background;

import java.awt.Color;

/**
 *
 */
public class WideEasy extends Background {
    private static final Color ICTERINE = new Color(252, 247, 94);
    private static final Color LEMON_CHIFFON = new Color(255, 250, 205);
    private static final Color YELLOW = new Color(255, 255, 0);
    /**
     *
     */
    public WideEasy() {}

    /**
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle((int) (ScreenSettings.FRAME_WIDTH * 0.03), (int) (ScreenSettings.FRAME_HEIGHT * 0.09),
                (int) (ScreenSettings.FRAME_WIDTH * 0.94), (int) (ScreenSettings.FRAME_HEIGHT * 0.945));
        d.setColor(LEMON_CHIFFON);
        for(int i = 0; i < 94; i++) {
            d.drawLine(ScreenSettings.FRAME_WIDTH / 6, ScreenSettings.FRAME_HEIGHT / 4, (int) (ScreenSettings.FRAME_WIDTH * ((i * 0.01) + 0.03)), (int) (ScreenSettings.FRAME_HEIGHT * 0.45));
        }
        d.fillCircle(ScreenSettings.FRAME_WIDTH / 6, ScreenSettings.FRAME_HEIGHT / 4, 80);
        d.setColor(ICTERINE);
        d.fillCircle(ScreenSettings.FRAME_WIDTH / 6, ScreenSettings.FRAME_HEIGHT / 4, 70);
        d.setColor(YELLOW);
        d.fillCircle(ScreenSettings.FRAME_WIDTH / 6, ScreenSettings.FRAME_HEIGHT / 4, 55);
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