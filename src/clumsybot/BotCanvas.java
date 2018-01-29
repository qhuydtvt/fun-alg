package clumsybot;

import clumsybot.bots.Bot;
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
        Map map = Map.instance;
        map.position.set(100, 100);
        GameObject.add(map);
    }

    private void setupBot() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 1; j ++) {
                Bot.instance.right();
            }
            Bot.instance.forward();
        }
    }
}
