import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class PongGui {

    private final JLabel scoreL;
    private final JLabel scoreR;
    private final Ball ball;
    private final KeyMovement keyListenerP1;
    private final KeyMovement keyListenerP2;
    private int scoreP1l;
    private int scoreP2r;
    private boolean stop;

    public PongGui() {
        JFrame frame = new JFrame();
        JPanel root = new JPanel();
        JPanel header = new JPanel();
        JPanel field = new JPanel();
        JPanel scoreField = new JPanel();
        JPanel middleLine = new JPanel();
        JLabel head = new JLabel();
        scoreL = new JLabel();
        scoreR = new JLabel();
        PlayerBar p1l = new PlayerBar(100, 327);
        PlayerBar p2r = new PlayerBar(1288, 327);
        keyListenerP1 = new KeyMovement(p1l, false, frame);
        keyListenerP2 = new KeyMovement(p2r, true, frame);
        stop = false;
        ball = new Ball(p1l, p2r, field, this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 900);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.GREEN);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setTitle("Pong");

        root.setLayout(null);
        root.setBackground(Color.BLACK);
        root.setBounds(0, 0, 1400, 863);

        header.setLayout(null);
        header.setBackground(Color.DARK_GRAY);
        header.setBounds(0,0, 1400, 50);

        head.setForeground(new Color(78, 216, 172));
        head.setFont(new Font("Ink Free", Font.BOLD, 35));
        head.setText(" Pong");
        head.setOpaque(true);

        scoreField.setLayout(null);
        scoreField.setBounds(500, 0, 400, 100);
        scoreField.setBackground(Color.BLACK);

        scoreL.setForeground(Color.WHITE);
        scoreL.setFont(new Font("DSEG7 Classic-Regular", Font.PLAIN, 60));
        scoreL.setText("00");
        scoreL.setLocation(20, 0);
        scoreL.setBounds(20, 20, 100, 75);

        scoreR.setForeground(Color.WHITE);
        scoreR.setFont(new Font("DSEG7 Classic-Regular", Font.PLAIN, 60));
        scoreR.setText("00");
        scoreR.setBounds(275, 20, 100, 75);

        field.setLayout(null);
        field.setBounds(0, 120, 1400, 730);
        field.setBackground(Color.BLACK);

        middleLine.setBackground(Color.WHITE);
        middleLine.setBounds(696, 0, 8, 730);

        p1l.setForeground(Color.WHITE);
        p1l.setBounds(p1l.getX(), p1l.getY(), 12, 76);

        p2r.setForeground(Color.WHITE);
        p2r.setBounds(p2r.getX(), p2r.getY(), 12, 76);

        ball.setBackground(Color.WHITE);
        ball.setBounds(ball.getX(), ball.getY(), 16, 16);

        field.add(p1l);
        field.add(middleLine);
        field.add(p2r);
        field.add(ball);

        scoreField.add(scoreL);
        scoreField.add(scoreR);

        root.add(scoreField);
        root.add(field);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop = true;
            }
        });

        frame.add(root);
        frame.setVisible(true);
    }

    public void start() {
        try {
            gameTickGenerator();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void gameTickGenerator() throws InterruptedException {
        do {
            keyListenerP1.movePlayerBarIfNeeded();
            keyListenerP2.movePlayerBarIfNeeded();
            ball.cycle();
            Thread.sleep(5);
        } while (!stop);
    }

    private void score(JLabel scoreBoard) {
        if (scoreBoard == scoreL) {
            if (scoreP1l >= 9) {
                scoreP1l++;
                scoreL.setText(String.valueOf(scoreP1l));
            } else {
                scoreP1l++;
                scoreL.setText("0" + scoreP1l);
            }
        } else {
            if (scoreP2r >= 9) {
                scoreP2r++;
                scoreR.setText(String.valueOf(scoreP2r));
            } else {
                scoreP2r++;
                scoreR.setText("0" + scoreP2r);
            }
        }
    }

    public void pointScored(boolean isPlayer1) {
        if (isPlayer1)
            score(scoreL);
        else
            score(scoreR);
    }
}