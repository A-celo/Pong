import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BallRunner implements Runnable {

    public static final int MAX_X = 640;
    public static final int MAX_Y = 480;
    public static final int SIGN = -1;

    public static final int DX = 10;
    public static final int DY = 10;
    private Ellipse2D.Double ball;

    private Paddle paddle;

    private int ballX;
    private int ballY;
    public BallRunner(Ellipse2D.Double shape, Paddle p) {
        ball = ( Ellipse2D.Double) shape;
        paddle = p;
        ballX = 320;
        ballY = 240;
        ball.x = ballX ;
        ball.y = ballY;
    }
        private boolean paused;
    public void pause(){
        paused = true;
    }
    public void resume(){
        paused = false;
    }

    @Override
    public void run() {
        int directionY = 1;
        int directionX = 1;

        while( true ) {

            if (paused){
                continue;
            }
            int y = (int) ball.getMaxY();
            int x = (int)  ball.getX();

            if( paddle.check(x,y) ) {
                directionY = directionY * SIGN;
                // sX = sX * SIGN;
                ballX = ballX + (DX * directionX) ;
                ballY = ballY + (DY * directionY);
                ball.x = ballX ;
                ball.y = ballY;
                continue;
            }
            if (collision()){
                directionY = -1;
                ballX = paddle.getTopX();
            }
            if (collision2()){
                directionX = -1;
                ballX = paddle.getTop2X() - 20;
            }
            if( ball.getMinY() < 0 ) {
                directionY = directionY * SIGN;
            }

            if( ball.getMinX() < 0 ) {
                directionX = directionX * SIGN;
            }
            if( ball.getMaxY() > MAX_Y ) {
                directionY = directionY * SIGN;
            }

            if( ball.getMaxX() > MAX_X ) {
                //directionX = directionX * SIGN;
            }
            ballX = ballX + (DX * directionX) ;
            ballY = ballY + (DY * directionY);
            ball.x = ballX ;
            ball.y = ballY;
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean collision() {
        return paddle.getBounds().intersects(getBounds()) ;
    }
    private boolean collision2() {
        return paddle.getBounds2().intersects(getBounds());
    }
    private Rectangle getBounds() {
        return new Rectangle(ballX, ballY, 20, 20);
    }


}

