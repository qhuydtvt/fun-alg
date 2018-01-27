package tkbases.actions;

import tkbases.GameObject;

/**
 * Created by huynq on 5/18/17.
 */
public class ActionRepeatForever implements Action {
    private Action action;

    public ActionRepeatForever(Action action) {
        this.action = action;
    }

    @Override
    public boolean execute(GameObject owner) {
        if (action.execute(owner)) {
            action.reset();
        }

        return false;
    }

    @Override
    public void reset() {
        action.reset();
    }
}
