import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.List;

public class Paddle extends Block implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;

    private static final int PADDLE_WIDTH = 140;
    private static final int PADDLE_SEGMENTS = 5;
    private static final int MOVE_LEFT = 15;
    private static final int MOVE_RIGHT = 15;
    private GUI gui;

    /**
     *
     */
    public Paddle() {
        super(new Point((Game.getFrameWidth() - PADDLE_WIDTH) / 2, Game.getFrameHeight() * 0.92), PADDLE_WIDTH, (int) (Game.getFrameHeight() * 0.03), "Paddle", 0, Color.ORANGE,false);
    }

    /**
     *
     */
    public void moveLeft() {
        if (getCollisionRectangle().getUpperLeft().getX() - MOVE_LEFT <= Game.getFrameWidth() * 0.05) {
            getCollisionRectangle().setUpperLeftPoint(new Point(Game.getFrameWidth() * 0.05, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - MOVE_LEFT, getCollisionRectangle().getUpperLeft().getY()));
        }

    }

    /**
     *
     */
    public void moveRight() {
        if (getCollisionRectangle().getUpperLeft().getX() + PADDLE_WIDTH + MOVE_RIGHT >= Game.getFrameWidth() * 0.95) {
            getCollisionRectangle().setUpperLeftPoint(new Point(Game.getFrameWidth() * 0.95 - PADDLE_WIDTH, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + MOVE_RIGHT, getCollisionRectangle().getUpperLeft().getY()));
        }

    }

    /**
     *
     */
    public void moveLeftSlow() {
        if (getCollisionRectangle().getUpperLeft().getX() - (MOVE_LEFT - 5) <= Game.getFrameWidth() * 0.05) {
            getCollisionRectangle().setUpperLeftPoint(new Point(Game.getFrameWidth() * 0.05, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - (MOVE_LEFT - 5), getCollisionRectangle().getUpperLeft().getY()));
        }

    }

    /**
     *
     */
    public void moveLeftFast() {
        if (getCollisionRectangle().getUpperLeft().getX() - (MOVE_LEFT + 5) <= Game.getFrameWidth() * 0.05) {
            getCollisionRectangle().setUpperLeftPoint(new Point(Game.getFrameWidth() * 0.05, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - (MOVE_LEFT + 5), getCollisionRectangle().getUpperLeft().getY()));
        }

    }

    /**
     *
     */
    public void moveRightSlow() {
        if (getCollisionRectangle().getUpperLeft().getX() + PADDLE_WIDTH + MOVE_RIGHT - 5 >= Game.getFrameWidth() * 0.95) {
            getCollisionRectangle().setUpperLeftPoint(new Point(Game.getFrameWidth() * 0.95 - PADDLE_WIDTH, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + (MOVE_RIGHT - 5), getCollisionRectangle().getUpperLeft().getY()));
        }

    }

    /**
     *
     */
    public void moveRightFast() {
        if(getCollisionRectangle().getUpperLeft().getX() + PADDLE_WIDTH + MOVE_RIGHT + 5 >= Game.getFrameWidth() * 0.95) {
            getCollisionRectangle().setUpperLeftPoint(new Point(Game.getFrameWidth() * 0.95 - PADDLE_WIDTH, getCollisionRectangle().getUpperLeft().getY()));
        }
        else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + (MOVE_RIGHT + 5), getCollisionRectangle().getUpperLeft().getY()));
        }

    }


    /**
     *
     */
    public void timePassed() {
        if ((keyboard.isPressed("left") || keyboard.isPressed("a")) && (keyboard.isPressed("up") || keyboard.isPressed("w"))) {
            moveLeftFast();
        } else if ((keyboard.isPressed("left") || keyboard.isPressed("a")) && (keyboard.isPressed("down") || keyboard.isPressed("s"))) {
            moveLeftSlow();
        } else if (keyboard.isPressed("left") || keyboard.isPressed("a")) {
            moveLeft();
        } else if ((keyboard.isPressed("right") || keyboard.isPressed("d")) && (keyboard.isPressed("up") || keyboard.isPressed("w"))) {
            moveRightFast();
        } else if ((keyboard.isPressed("right") || keyboard.isPressed("d")) && (keyboard.isPressed("down") || keyboard.isPressed("s"))) {
            moveRightSlow();
        } else if (keyboard.isPressed("right") || keyboard.isPressed("d")) {
            moveRight();
        }
    }

    /**
     * @param g
     */
    public void setGUI(GUI g) {
        gui = g;
        keyboard = gui.getKeyboardSensor();
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        List<Line> sidesList = getCollisionRectangle().getSidesList();
        Line l = null;

        for (Line elem : sidesList) {
            if(Line.isPointOnSegment(elem.getStartPoint(), collisionPoint, elem.getEndPoint())) {
                l = elem;
                break;
            }
        }

        if (l.isVerticalLine()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        int paddleSegment = PADDLE_WIDTH / PADDLE_SEGMENTS;
        int collision = (int) collisionPoint.getX();
        int paddleStart = (int) getCollisionRectangle().getXUpperLeftCoordinate();
        if (paddleStart <= collision && collision <= paddleStart + paddleSegment) { //region 1
            return Velocity.fromAngleAndSpeed(240, currentVelocity.getSpeed());
        } else if (paddleStart + paddleSegment < collision && collision <= paddleStart + (2 * paddleSegment)) { //region 2
            return Velocity.fromAngleAndSpeed(210, currentVelocity.getSpeed());
        } else if (paddleStart + (2 * paddleSegment) < collision && collision <= paddleStart + (3 * paddleSegment)) {//region 3
            return Velocity.fromAngleAndSpeed(180, currentVelocity.getSpeed());
        } else if (paddleStart + (3 * paddleSegment) < collision && collision <= paddleStart + (4 * paddleSegment)) { //region 4
            return Velocity.fromAngleAndSpeed(150, currentVelocity.getSpeed());
        } else { // (paddleStart + (4 * paddleSegment) < collision && collision <= paddleStart + (5 * paddleSegment)) //region 5
            return Velocity.fromAngleAndSpeed(120, currentVelocity.getSpeed());
        }
    }

//    public int getHitCount() {
//        return 1;
//    }

//    public void updateHitCount() {
//
//    }
}