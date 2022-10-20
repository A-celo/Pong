import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Paddle {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 80;

    private final int[] yPositions = { 0,80,160,240,320,400};

    private Rectangle2D.Double rectangle, rectangle2;
    private int pos = 0;
    private int pos2 = 0;

    public Paddle() {
        super();
        rectangle = new Rectangle2D.Double(0,225,WIDTH,HEIGHT);
        rectangle2 = new Rectangle2D.Double(610,225,WIDTH,HEIGHT);
    }

    public Rectangle getBounds(){
        return new Rectangle(0, yPositions[pos], WIDTH, HEIGHT);
    }
    public Rectangle getBounds2(){
        return new Rectangle(610, yPositions[pos2], WIDTH, HEIGHT);
    }
    public int getTopX() {
        return 30;
    }
    public int getTop2X() {
        return 610;
    }
    public void moveUp() {
        if( pos < 5 ) {
            pos++;
            rectangle.y = yPositions[pos];
        }
    }
    public void moveDown() {
        if( pos > 0 ) {
            pos--;
            rectangle.y = yPositions[pos];
        }
    }
    public void moveUp2() {
        if( pos2 < 5 ) {
            pos2++;
            rectangle2.y = yPositions[pos2];
        }
    }
    public void moveDown2() {
        if( pos2 > 0 ) {
            pos2--;
            rectangle2.y = yPositions[pos2];
        }
    }
    public Rectangle2D.Double getRectangle() {
        return rectangle;
    }
    public Rectangle2D.Double getRectangle2() {
        return rectangle2;
    }
    public boolean check( int x, int y) {
        return rectangle.contains(x,y);
    }
}
