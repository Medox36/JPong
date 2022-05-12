import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyMovement extends KeyAdapter {
    private final PlayerBar p;
    private final boolean isArrowKeys;
    private boolean keyPressedUP;
    private boolean keyPressedDown;

    public KeyMovement(PlayerBar p, boolean isArrowKeys, JFrame frame) {
        this.p = p;
        this.isArrowKeys = isArrowKeys;
        frame.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isArrowKeys) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> keyPressedUP = true;
                case KeyEvent.VK_DOWN -> keyPressedDown = true;
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> keyPressedUP = true;
                case KeyEvent.VK_S -> keyPressedDown = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (isArrowKeys) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> keyPressedUP = false;
                case KeyEvent.VK_DOWN -> keyPressedDown = false;
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> keyPressedUP = false;
                case KeyEvent.VK_S -> keyPressedDown = false;
            }
        }
    }

    public void movePlayerBarIfNeeded() {
        if (keyPressedUP) {
            p.moveUp();
        } else if (keyPressedDown) {
            p.moveDown();
        }
    }
}