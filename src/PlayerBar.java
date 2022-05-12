import javax.swing.*;

public class PlayerBar extends JPanel {

    public PlayerBar(int x, int y) {
        setLocation(x, y);
    }

    public void moveUp() {
        if ((getY()-2) > 0) {
            setLocation(getX(), getY()-2);
        }
    }

    public void moveDown() {
        if ((getY()+2) < 654) {
            setLocation(getX(), getY()+2);
        }
    }
}