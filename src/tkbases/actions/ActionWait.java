package tkbases.actions;

import tkbases.GameObject;
import tkbases.utils.Counter;

/**
 * Created by huynq on 5/18/17.
 */
public class ActionWait implements Action {
    private Counter counter;

    public ActionWait(int delay) {
        counter = new Counter(delay);
    }

    @Override
    public boolean execute(GameObject owner) {
        return counter.update();
    }

    @Override
    public void reset() {
        counter.reset();
    }
}
