package clumsybot.grabables;

import clumsybot.BotDirection;
import tkbases.Vector2D;

/**
 * Created by huynq on 1/29/18.
 */
public interface Pickable {
    Vector2D getPosition();
    void move(BotDirection direction);
}
