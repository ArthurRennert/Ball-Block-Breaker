package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;

/**
 * A wrapper animation that stops when a specific key is pressed.
 *
 * This class ensures that the animation will stop only after the key is released and pressed again,
 * to avoid stopping immediately if the key was already held down from a previous screen.
 */
public class KeyPressStoppable implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Creates a new KeyPressStoppable animation.
     *
     * @param sensor the keyboard sensor used to detect key presses
     * @param key the key that stops the animation when pressed
     * @param animation the inner animation to run until the key is pressed
     */
    public KeyPressStoppable(KeyboardSensor sensor, String key, Animation animation) {
        keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        stop = false;
        isAlreadyPressed = true;
    }

    /**
     * Performs one frame of the animation.
     *
     * Delegates drawing to the inner animation. Stops only if the key was not previously held down
     * and is now pressed.
     *
     * @param d the surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(key)) {
            if (isAlreadyPressed) {
                return;
            }
            stop = true;
        } else if (!this.keyboardSensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    /**
     * Indicates whether the animation should stop.
     *
     * @return true if the key has been pressed and released, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}