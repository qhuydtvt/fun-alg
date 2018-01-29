package tkbases.actions;

import tkbases.GameObject;
import tkbases.renderers.Transform;
import tkbases.renderers.Transformable;
import tkbases.utils.Mathx;

/**
 * Created by huynq on 1/29/18.
 */
public class ActionRotateBy implements Action {
    private final int time;
    private final float angleDelta;
    private float destAngle = -1;
    private int currentTime;

    public ActionRotateBy(int angleDelta, int time) {
        this.angleDelta = angleDelta;
        this.time = time;
    }

    @Override
    public boolean execute(GameObject owner) {
        Transform transform = ((Transformable)owner.renderer).getTransform();

        if (destAngle == -1) {
            destAngle = transform.angle + angleDelta;
        }

        transform.angle = Mathx.lerp(transform.angle, destAngle, (float)currentTime / time);
        this.currentTime++;
        return currentTime >= time;
    }

    @Override
    public void reset() {
        this.currentTime = 0;
        destAngle = -1;
    }
}
