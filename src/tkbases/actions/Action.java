package tkbases.actions;

import tkbases.GameObject;

/**
 * Created by huynq on 5/18/17.
 */
public interface Action {
    boolean execute(GameObject owner);
    void reset();
}
