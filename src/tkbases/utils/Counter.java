package tkbases.utils;

/**
 * Created by huynq on 5/13/17.
 */
public class Counter {
    private int count;
    private int countMax;
    private boolean loop = true;

    public Counter(int countMax) {
        this(countMax, true);
    }

    public Counter(int countMax, boolean loop) {
        this.countMax = countMax;
        this.loop = loop;
    }

    public boolean update() {
        count++;
        if (count > countMax) {
            if (loop)
                count = 0;
            return true;
        }
        return false;
    }

    public void reset() {
        count = 0;
    }
}
