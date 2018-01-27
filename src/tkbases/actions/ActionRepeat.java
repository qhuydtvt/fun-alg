package tkbases.actions;

import org.omg.PortableInterceptor.ACTIVE;
import tkbases.GameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by huynq on 5/20/17.
 */
public class ActionRepeat implements Action {
    private Action action;
    private int repeatTimes;
    private int repeatCount;

    public ActionRepeat(Action action, int repeatTimes) {
        this.action = action;
        this.repeatTimes = repeatTimes;
        this.repeatCount = 0;
    }

    @Override
    public boolean execute(GameObject owner) {
        if (action.execute(owner)) {
            repeatCount++;
            action.reset();
            if (repeatCount >= repeatTimes) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void reset() {
        repeatCount = 0;
        action.reset();
    }
}
