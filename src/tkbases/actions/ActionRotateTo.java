package tkbases.actions;

import tkbases.GameObject;
import tkbases.renderers.Renderer;
import tkbases.renderers.Transform;
import tkbases.renderers.Transformable;
import tkbases.utils.Mathx;

import java.awt.*;

/**
 * Created by huynq on 1/28/18.
 */
public class ActionRotateTo implements Action {

    public final float destAngle;
    public final float time;

    public int currentTime;

    public ActionRotateTo(float destAngle, float time) {
        this.destAngle = destAngle;
        this.time = time;
    }

    @Override
    public boolean execute(GameObject owner) {
        Transform transform = ((Transformable)owner.renderer).getTransform();
        transform.angle = Mathx.lerp(transform.angle, destAngle, ((float)currentTime / time));
        this.currentTime++;
        return this.currentTime >= this.time;
    }

    @Override
    public void reset() {
        currentTime = 0;
    }
}
