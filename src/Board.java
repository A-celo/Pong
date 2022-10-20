import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;

public class Board extends JComponent implements Runnable, KeyListener {
    private Dimension preferredSize = null;

    private String Score1 = "0", Score2 = "0";

    private int sc1 = 0, sc2 = 0;
    private Ellipse2D.Double ball;

    private Paddle paddle;
    BallRunner ballRunner;

    public Board() {
        setOpaque(true);
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE));
        ball = new Ellipse2D.Double(20, 320, 20, 20);

        paddle = new Paddle();
        BallRunner ballRunner = new BallRunner(ball, paddle);
        Thread t1 = new Thread(ballRunner);
        t1.start();
        Thread t2 = new Thread(this);
        t2.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor(new Color(137, 190, 193));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(getForeground());
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(5.0f));
        g2.setPaint(Color.white);
        g2.setFont(new Font("Monospaced Plain", Font.BOLD, 35));
        g2.drawString(Score1, 213, 48);
        g2.drawString(Score2, 426, 48);
        g2.drawLine(320, 0, 320, 480);
        g2.setColor(new Color(239, 208, 94));
        g2.fill(ball);
        g2.setColor(new Color(22, 160, 133));
        g2.fill(paddle.getRectangle());

        g2.setColor(new Color(159, 138, 191));
        g2.fill(paddle.getRectangle2());


    }

    public Dimension getPreferredSize() {
        if (preferredSize == null) {
            return new Dimension(640, 480);
        } else {
            return super.getPreferredSize();
        }
    }

    public void setPreferredSize(Dimension newPrefSize) {
        preferredSize = newPrefSize;
        super.setPreferredSize(newPrefSize);
    }

    @Override
    public void run() {
        while (true) {
            if (ball.x < 0){
                newBall();
                newPaddle();
                sc2++;
                Score2 = Integer.toString(sc2);
            }
            if (ball.x > 640){
                newBall();
                newPaddle();
                sc1++;
                Score1 = Integer.toString(sc1);
            }
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S:
                paddle.moveUp();
                break;
            case KeyEvent.VK_W:
                paddle.moveDown();
                break;
            case KeyEvent.VK_R:
                ballRunner.resume();
                break;
            case KeyEvent.VK_SPACE:
                ballRunner.pause();
                break;
            case KeyEvent.VK_DOWN:
                paddle.moveUp2();
                break;
            case KeyEvent.VK_UP:
                paddle.moveDown2();
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {

        }
    }
    public void newBall(){
        ball = new Ellipse2D.Double(20, 320, 20, 20);
        BallRunner ballRunner = new BallRunner(ball, paddle);
        Thread t1 = new Thread(ballRunner);
        t1.start();
    }
    public void newPaddle(){
        paddle = new Paddle();

    }
}


