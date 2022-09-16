package gui;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean running;
    private boolean isAlreadyPressed;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        running = true;
        isAlreadyPressed = true;
    }

    /**
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
//        while (running) {
            animation.doOneFrame(d);
            if (this.keyboardSensor.isPressed(key)) {
                if (isAlreadyPressed)
//                    continue;
                    return;
                running = false;
            } else if (!this.keyboardSensor.isPressed(key)) {
                isAlreadyPressed = false;
            }
//        }
    }

    /**
     * @return
     */
    @Override
    public boolean shouldStop() {
        return animation.shouldStop();
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}