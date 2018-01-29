package tkbases.actions;

import tkbases.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 5/18/17.
 */
public class ActionSequence implements Action {
    private List<Action> actionList;
    private int currentActionIndex;

    public ActionSequence(Action... actions) {
        actionList = new ArrayList<>();
        for (Action action : actions) {
            actionList.add(action);
        }
        currentActionIndex = 0;
    }

    public void addAction(Action action) {
        actionList.add(action);
    }

    @Override
    public boolean execute(GameObject owner) {
        if (currentActionIndex >= actionList.size()) {
            return true;
        } else {
            Action action = actionList.get(currentActionIndex);
            if (action.execute(owner)) {
                currentActionIndex++;
            }
            return false;
        }
    }

    @Override
    public void reset() {
        currentActionIndex = 0;
        for (Action action: actionList) {
            action.reset();
        }
    }
}
