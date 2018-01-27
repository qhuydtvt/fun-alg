package tkbases.actions;

import tkbases.GameObject;
import tkbases.utils.Counter;

/**
 * Created by huynq on 5/19/17.
 */
public class ActionMoveBy implements Action {

    private double dx;
    private double dy;
    private Counter counter;

    public ActionMoveBy(double dx, double dy, int delay) {
        this.dx = dx;
        this.dy = dy;
        this.counter = new Counter(delay);
    }

    @Override
    public boolean execute(GameObject owner) {
        owner.getPosition().addUp(dx, dy);
        return counter.update();
    }

    @Override
    public void reset() {
        counter.reset();
    }
}
