package tkbases.physics;
import tkbases.GameObject;

/**
 * Created by huynq on 5/13/17.
 */
public class BoxCollider extends GameObject {
    private int width;
    private int height;

    public BoxCollider(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean inRange(double value, double min, double max) {
        return (value >= min) && (value <= max);
    }

    public boolean intersects(double otherX, double otherY, double otherWidth, double otherHeight) {
        boolean xIntersects = inRange(screenPosition.x, otherX, otherX + otherWidth) || inRange(otherX, screenPosition.x, screenPosition.x + width);
        boolean yIntersects = inRange(screenPosition.y, otherY, otherY + otherHeight) || inRange(otherY, screenPosition.y, screenPosition.y + height);
        return xIntersects && yIntersects;
    }

    public void print() {
        System.out.println(String.format("%s, %s, %s, %s", screenPosition.x, screenPosition.y, width, height));
    }
}
