package tkbases.physics;

/**
 * Created by huynq on 5/13/17.
 */
public interface PhysicsBody {
    BoxCollider getBoxCollider();
    boolean isActive();
}
