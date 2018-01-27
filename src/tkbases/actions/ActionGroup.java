package tkbases.actions;

import tkbases.GameObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huynq on 5/18/17.
 */
public class ActionGroup implements Action {
    private List<Action> actionList;

    public ActionGroup(Action... actions) {
        actionList = new ArrayList<>();
        for (Action action:actions) {
            actionList.add(action);
        }
    }

    @Override
    public boolean execute(GameObject owner) {
        if (actionList.size() == 0) return true;
        Iterator<Action> iterator = actionList.iterator();
        while (iterator.hasNext()) {
            Action action = iterator.next();
            if (action.execute(owner)) {
                iterator.remove();
            }
        }
        return false;
    }

    @Override
    public void reset() {
        for (Action action: actionList) {
            action.reset();
        }
    }
}
