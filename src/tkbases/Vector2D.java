package tkbases;

/**
 * Created by huynq on 5/11/17.
 */
public class Vector2D {
    public double x;
    public double y;

    public static final Vector2D ZERO = new Vector2D(0, 0);
    public static final Vector2D ONE = new Vector2D(1, 1);

    public static final Vector2D UP = new Vector2D(0, -1);
    public static final Vector2D DOWN = new Vector2D(0, 1);

    public static final Vector2D LEFT = new Vector2D(-1, 0);
    public static final Vector2D RIGHT = new Vector2D(1, 0);

    public Vector2D() {
    }

    public Vector2D(Vector2D other) {
        this(other.x, other.y);
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D clone() {
        return new Vector2D(this);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void addUp(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void addUp(Vector2D other) {
        addUp(other.x, other.y);
    }

    public Vector2D add(Vector2D other) {
        return add(other.x, other.y);
    }

    public Vector2D add(double x, double y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D subtract(double x, double y) {
        return new Vector2D(this.x - x, this.y - y);
    }

    public Vector2D subtract(Vector2D other) {
        return subtract(other.x, other.y);
    }

    public Vector2D normalize() {
        double length = Math.sqrt(x * x + y * y);
        return new Vector2D(x / length, y / length);
    }

    public Vector2D multiply(double m) {
        return new Vector2D(x * m, y * m);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D rotate(double angle) {
        double sinAngle = Math.sin(angle);
        double cosAngle = Math.cos(angle);
        return new Vector2D(x * cosAngle - y * sinAngle, x * sinAngle + y * cosAngle);
    }

    public boolean inside(double minX, double maxX, double minY, double maxY) {
        return x > minX && x < maxX && y > minY && y < maxY;
    }

    public boolean inside(double maxX, double maxY) {
        return inside(0, maxX, 0, maxY);
    }

    public void set(Vector2D position) {
        set(position.x, position.y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
