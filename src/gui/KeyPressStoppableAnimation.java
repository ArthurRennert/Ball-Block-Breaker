package gui;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * @param sensor
     * @param key
     * @param animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        stop = false;
        isAlreadyPressed = true;
    }

    /**
     * @param d
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
     * @return
     */
    @Override
    public boolean shouldStop() {
//        System.out.println(!running);
        return this.stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}