package tkbases.physics;

import java.util.List;
import java.util.Vector;

/**
 * Created by huynq on 5/13/17.
 */
public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();
    public static void add(PhysicsBody body) {
        bodies.add(body);
    }

    public static List<PhysicsBody> bodyInRectAll(double x, double y, double width, double height) {
        List<PhysicsBody> returnBodies = new Vector<>();
        for (PhysicsBody body : bodies) {
            if (body.getBoxCollider().intersects(x, y, width, height)) {
                returnBodies.add(body);
            }
        }
        return returnBodies;
    }

    public static PhysicsBody bodyInRect(double x, double y, double width, double height) {
        for (PhysicsBody body : bodies) {
            if (body.getBoxCollider().intersects(x, y, width, height)) {
                return body;
            }
        }
        return null;
    }

    public static PhysicsBody bodyInRect(BoxCollider boxCollider) {
        return bodyInRect(boxCollider.getScreenPosition().x, boxCollider.getScreenPosition().y, boxCollider.getWidth(), boxCollider.getHeight());
    }

    public static List<PhysicsBody> bodyInRectAll(BoxCollider boxCollider) {
        return bodyInRectAll(boxCollider.getScreenPosition().x, boxCollider.getScreenPosition().y, boxCollider.getWidth(), boxCollider.getHeight());
    }

    public static void clearAll() {
        bodies.clear();
    }
}
