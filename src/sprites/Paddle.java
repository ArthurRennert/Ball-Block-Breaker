package sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import collision.Collidable;
import gui.ScreenSettings;
import gui.motion.Velocity;
import gui.shapes.Line;
import gui.shapes.Point;
import sprites.infrastructure.Sprite;


import java.awt.Color;
import java.util.List;

/**
 *
 */
public class Paddle extends Block implements Sprite, Collidable {
    private static final int PADDLE_SEGMENTS = 5;

    private GUI gui;
    private biuoop.KeyboardSensor keyboard;

    private int paddleWidth;
    private int paddleHeight;
    private int paddleSpeed;
    private Point initialPoint;


    /**
     * @param paddleInitialPoint
     * @param paddleWidth
     * @param paddleHeight
     * @param paddleSpeed
     */
    public Paddle(Point paddleInitialPoint, int paddleWidth, int paddleHeight, int paddleSpeed) {
        super(new Point(paddleInitialPoint), paddleWidth, paddleHeight, "Paddle", Color.ORANGE);
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;
        this.paddleHeight = paddleHeight;
        initialPoint = paddleInitialPoint;
    }

    /**
     *
     */
    public void moveLeft() {
        if (getCollisionRectangle().getUpperLeft().getX() - paddleSpeed <= ScreenSettings.FRAME_WIDTH * 0.03) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.03, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - paddleSpeed, getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     *
     */
    public void moveRight() {
        if (getCollisionRectangle().getUpperLeft().getX() + paddleWidth + paddleSpeed >= ScreenSettings.FRAME_WIDTH * 0.97) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.97 - paddleWidth, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + paddleSpeed, getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     *
     */
    public void moveLeftSlow() {
        if (getCollisionRectangle().getUpperLeft().getX() - (paddleSpeed - 5) <= ScreenSettings.FRAME_WIDTH * 0.03) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.03, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - (paddleSpeed - 5), getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     *
     */
    public void moveLeftFast() {
        if (getCollisionRectangle().getUpperLeft().getX() - (paddleSpeed + 5) <= ScreenSettings.FRAME_WIDTH * 0.03) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.03, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - (paddleSpeed + 5), getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     *
     */
    public void moveRightSlow() {
        if (getCollisionRectangle().getUpperLeft().getX() + paddleWidth + paddleSpeed - 5 >= ScreenSettings.FRAME_WIDTH * 0.97) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.97 - paddleWidth, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + (paddleSpeed - 5), getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     *
     */
    public void moveRightFast() {
        if (getCollisionRectangle().getUpperLeft().getX() + paddleWidth + paddleSpeed + 5 >= ScreenSettings.FRAME_WIDTH * 0.97) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.97 - paddleWidth, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + (paddleSpeed + 5), getCollisionRectangle().getUpperLeft().getY()));
        }
    }


    /**
     *
     */
    @Override
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

    /**
     * @param point
     */
    public void setLocation(Point point) {
        getCollisionRectangle().setUpperLeftPoint(initialPoint);
    }


    /**
     * @param collisionPoint
     * @param currentVelocity
     * @return
     */
    @Override
    public Velocity hit(Ball b, Point collisionPoint, Velocity currentVelocity) {
        List<Line> sidesList = getCollisionRectangle().getSidesList();
        Line l = null;

        for (Line elem : sidesList) {
            if (Line.isPointOnSegment(elem.getStartPoint(), collisionPoint, elem.getEndPoint())) {
                l = elem;
                break;
            }
        }

        if (l.isVerticalLine()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        int paddleSegment = paddleWidth / PADDLE_SEGMENTS;
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
}