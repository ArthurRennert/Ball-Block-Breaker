package sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import collision.Collidable;
import collision.listeners.hit_notifiers.HitNotifier;
import gui.ScreenSettings;
import gui.motion.Velocity;
import gui.shapes.Line;
import gui.shapes.Point;
import sprites.infrastructure.Sprite;
import utilities.Timer;
import java.awt.Color;
import java.util.List;

/**
 * The Paddle class represents the player's paddle in the game.
 * It responds to keyboard input to move left or right and handles ball collisions.
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
     * Constructs a new Paddle.
     *
     * @param paddleInitialPoint the initial top-left position of the paddle
     * @param paddleWidth        the width of the paddle
     * @param paddleHeight       the height of the paddle
     * @param paddleSpeed        the speed (in pixels per frame) at which the paddle moves
     */
    public Paddle(Point paddleInitialPoint, int paddleWidth, int paddleHeight, int paddleSpeed) {
        super(new Point(paddleInitialPoint), paddleWidth, paddleHeight, "Paddle", Color.ORANGE);
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;
        this.paddleHeight = paddleHeight;
        initialPoint = paddleInitialPoint;
    }

    /**
     * Moves the paddle left by its standard speed, unless it hits the screen border.
     */
    public void moveLeft() {
        if (getCollisionRectangle().getUpperLeft().getX() - paddleSpeed <= ScreenSettings.FRAME_WIDTH * 0.03) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.03, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - paddleSpeed, getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * Moves the paddle right by its standard speed, unless it hits the screen border.
     */
    public void moveRight() {
        if (getCollisionRectangle().getUpperLeft().getX() + paddleWidth + paddleSpeed >= ScreenSettings.FRAME_WIDTH * 0.97) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.97 - paddleWidth, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + paddleSpeed, getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * Moves the paddle left more slowly than usual.
     */
    public void moveLeftSlow() {
        if (getCollisionRectangle().getUpperLeft().getX() - (paddleSpeed - 5) <= ScreenSettings.FRAME_WIDTH * 0.03) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.03, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - (paddleSpeed - 5), getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * Moves the paddle left faster than usual.
     */
    public void moveLeftFast() {
        if (getCollisionRectangle().getUpperLeft().getX() - (paddleSpeed + 5) <= ScreenSettings.FRAME_WIDTH * 0.03) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.03, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() - (paddleSpeed + 5), getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * Moves the paddle right more slowly than usual.
     */
    public void moveRightSlow() {
        if (getCollisionRectangle().getUpperLeft().getX() + paddleWidth + paddleSpeed - 5 >= ScreenSettings.FRAME_WIDTH * 0.97) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.97 - paddleWidth, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + (paddleSpeed - 5), getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * Moves the paddle right faster than usual.
     */
    public void moveRightFast() {
        if (getCollisionRectangle().getUpperLeft().getX() + paddleWidth + paddleSpeed + 5 >= ScreenSettings.FRAME_WIDTH * 0.97) {
            getCollisionRectangle().setUpperLeftPoint(new Point(ScreenSettings.FRAME_WIDTH * 0.97 - paddleWidth, getCollisionRectangle().getUpperLeft().getY()));
        } else {
            getCollisionRectangle().setUpperLeftPoint(new Point(getCollisionRectangle().getUpperLeft().getX() + (paddleSpeed + 5), getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * Called once per frame to update paddle position based on keyboard input.
     * Supports fast/slow movement using key combinations.
     *
     * @param timer the game timer (not used in this method)
     */
    @Override
    public void timePassed(Timer timer) {
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
     * Sets the GUI object and initializes the paddle's keyboard sensor.
     *
     * @param g the GUI instance from which to obtain the keyboard sensor
     */
    public void setGUI(GUI g) {
        gui = g;
        keyboard = gui.getKeyboardSensor();
    }

    /**
     * Resets the paddle's position to its initial starting point.
     *
     * @param point not used in current implementation
     */
    public void setLocation(Point point) {
        getCollisionRectangle().setUpperLeftPoint(initialPoint);
    }

    /**
     * Handles a ball collision with the paddle and returns the new velocity.
     * Splits the paddle into 5 segments to determine reflection angle.
     *
     * @param b                the ball that hit the paddle
     * @param collisionPoint   the point of collision
     * @param currentVelocity  the current velocity of the ball
     * @return the new velocity after the hit
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

        this.notifyHit(b);
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