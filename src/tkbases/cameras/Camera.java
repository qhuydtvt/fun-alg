package tkbases.cameras;

import tkbases.utils.Counter;

/**
 * Created by huynq on 5/16/17.
 */
public class Camera {
    private boolean isFrezzing;
    private Counter frezzeCounter;

    private static Camera instance;

    public static Camera getInstance() {
        return instance;
    }

    public Camera() {
        instance = this;
    }

    public void update() {
        if (isFrezzing) {
            if (frezzeCounter.update()) {
                isFrezzing = false;
            }
        }
    }

    public void frezze(int multiplier) {
        isFrezzing = true;
        frezzeCounter = new Counter(multiplier);
    }

    public boolean isFrezzing() {
        return isFrezzing;
    }
}
