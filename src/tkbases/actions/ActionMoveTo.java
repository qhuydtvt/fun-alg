package tkbases.actions;

import tkbases.utils.Mathx;
import tkbases.GameObject;
import tkbases.Vector2D;

/**
 * Created by huynq on 5/20/17.
 */
public class ActionMoveTo implements Action {
    private Vector2D destination;
    private int time;
    private int currentTime;
    private Vector2D startPosition;


    public ActionMoveTo(Vector2D destination, int time) {
        this.destination = destination;
        this.time = time;
        this.currentTime = 0;
    }

    @Override
    public boolean execute(GameObject owner) {
        if (startPosition == null) {
            startPosition = new Vector2D();
            startPosition.set(owner.getPosition());
        }
        Vector2D newPosition = Mathx.lerp(startPosition, this.destination, (double) (this.currentTime) / (double)this.time);
        owner.getPosition().set(newPosition);
        this.currentTime++;
        return this.currentTime >= this.time;
    }

    @Override
    public void reset() {
        this.currentTime = 0;
        this.startPosition = null;
    }
}
