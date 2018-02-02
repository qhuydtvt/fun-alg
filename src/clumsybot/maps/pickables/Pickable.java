package clumsybot.maps.pickables;

import clumsybot.MapDirection;
import tkbases.Vector2D;

/**
 * Created by huynq on 1/29/18.
 */
public interface Pickable {
    Vector2D getPosition();
    void move(MapDirection direction);
    void pickUp();
    void putDown();
}
