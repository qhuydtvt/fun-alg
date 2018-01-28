package clumsybot;

import clumsybot.maps.Map;
import tkbases.GameCanvas;
import tkbases.GameObject;

/**
 * Created by huynq on 1/28/18.
 */
public class BotCanvas extends GameCanvas {
    public BotCanvas(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        setupMap();
        setupBot();
    }

    private void setupMap() {
        Map map = new Map(10, 10);
        map.position.set(100, 100);
        GameObject.add(map);
    }

    private void setupBot() {
    }
}
