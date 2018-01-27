package tkbases;

import tkbases.GameObject;

/**
 * Created by huynq on 5/15/17.
 */
public class GameObjectDeactive {
    private double screenWidth;
    private double screenHeight;

    public GameObjectDeactive(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void update(GameObject gameObject) {
        if (!gameObject.getPosition().inside(
                screenWidth,
                screenHeight)) {
            gameObject.setActive(false);
        }
    }
}
