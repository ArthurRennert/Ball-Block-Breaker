import biuoop.DrawSurface;

import java.awt.*;
import java.util.List;

public class Paddle extends Block implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;


    public Paddle(biuoop.GUI gui) {
        super(new Point(500, 500), 40, 30, "Paddle", 0, Color.ORANGE,false);
        keyboard = gui.getKeyboardSensor();
    }

    public void moveLeft() {
        getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - 20, getCollisionRectangle().getUpperLeft().getY()));

    }
    public void moveRight() {
        getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + 20, getCollisionRectangle().getUpperLeft().getY()));

    }

    // Sprite
    public void timePassed() {
        if(keyboard.isPressed("a")) {
            moveLeft();
        } else if(keyboard.isPressed("d")) {
            moveRight();
        }
    }
//    public void drawOn(DrawSurface surface) {
//        block.drawOn(surface);
//    }

    // Collidable
//    public Rectangle getCollisionRectangle() {
//        return block.getCollisionRectangle();
//    }

//    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
//        return block.hit(collisionPoint, currentVelocity);
//    }

    // Add this paddle to the game.
//    public void addToGame(Game g) {
//        block.addToGame(g);
//    }

//    public int getHitCount() {
//        return 1;
//    }

//    public void updateHitCount() {
//
//    }

//    public boolean isDisappearable() {
//        return block.isDisappearable();
//    }
}