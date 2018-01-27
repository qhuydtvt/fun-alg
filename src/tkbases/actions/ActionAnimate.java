package tkbases.actions;

import tkbases.Animation;
import tkbases.GameObject;
import tkbases.Renderer;

/**
 * Created by huynq on 8/20/17.
 */
public class ActionAnimate implements Action {

    private Animation animation;

    public ActionAnimate(Animation animation) {
        this.animation = animation;
    }

    @Override
    public boolean execute(GameObject owner) {
        owner.setRenderer(animation);
        return true;
    }

    @Override
    public void reset() {
        animation.reset();
    }
}
