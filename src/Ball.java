import javax.swing.*;
import java.util.Random;

public class Ball extends JPanel{
    private final Random random;
    private int xVelocity;
    private int yVelocity;
    private final JPanel p1l, p2r, field;
    private final PongGui pongGui;

    public Ball(JPanel p1l, JPanel p2r, JPanel field, PongGui pongGui) {
        this.p1l = p1l;
        this.p2r = p2r;
        this.field = field;
        this.pongGui = pongGui;
        random = new Random();
        toStartPos(Math.round(Math.random()) < 1);
        field.add(this);
    }

    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        setLocation(getX()+xVelocity, getY()+yVelocity);
    }

    public void checkCollision() {
        if (getY() <= 0)
            setYDirection(-yVelocity);
        if (getY()+16 >= field.getHeight())
            setYDirection(-yVelocity);
        if (getX()+16 >= p2r.getX() && getX()+16 <= p2r.getX()) {
            if (getY() >= p2r.getY() && getY()+1 <= p2r.getY()+76) {
                setXDirection(-xVelocity);
            }
            if (getY() >= p2r.getY() && getY()+16 <= p2r.getY()) {
                setXDirection(-xVelocity);
            }
        } else if (getX() <= p1l.getX()+12 && getX() >= p1l.getX()+12) {
            if (getY() >= p1l.getY() && getY()+1 <= p1l.getY()+76) {
                setXDirection(-xVelocity);
            }
            if (getY() >= p1l.getY() && getY()+16 <= p1l.getY()) {
                setXDirection(-xVelocity);
            }
        }
    }

    private boolean checkScoringPoint() {
        if  (getX() < 0)
            return true;
        else
            return getX() > 1400;
    }

    private void resetAndScore() {
        pongGui.pointScored(getX() < 0);
        toStartPos(getX() < 0);
    }

    private void toStartPos(boolean onLeftSide) {
        /*
        y = random.nextInt(600);
        if (y < 130)
            y = 130;
         */
        int randomXDirection = random.nextInt(2);
        int randomYDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection++;
        if (randomYDirection == 0)
            randomYDirection++;
        if (onLeftSide) {
            setXDirection(randomXDirection);
            setYDirection(randomYDirection);
            setLocation(560, 357);
        } else {
            setXDirection(-randomXDirection);
            setYDirection(-randomYDirection);
            setLocation(830, 357);
        }
    }

    public void cycle() {
        move();
        checkCollision();
        if (checkScoringPoint()) {
            resetAndScore();
        }
    }
}