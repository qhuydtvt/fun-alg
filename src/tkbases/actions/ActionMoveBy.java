package tkbases.actions;

import tkbases.GameObject;
import tkbases.Vector2D;
import tkbases.utils.Counter;
import tkbases.utils.Mathx;

/**
 * Created by huynq on 5/19/17.
 */
public class ActionMoveBy implements Action {

    private final Vector2D moveDelta;
    private final int time;

    private Vector2D moveDest;
    private int currentTime;


    public ActionMoveBy(Vector2D moveDelta, int time) {
        this.moveDelta = moveDelta;
        this.time = time;
        this.currentTime = 0;
        this.moveDest = null;
    }

    @Override
    public boolean execute(GameObject owner) {
        if(moveDest == null) {
            moveDest = owner.position.add(moveDelta);
        }

        owner.position.set(Mathx.lerp(owner.position, this.moveDest, (float)currentTime / time));

        currentTime++;
        return currentTime >= time;
    }

    @Override
    public void reset() {
        currentTime = 0;
        moveDest = null;
    }
}
