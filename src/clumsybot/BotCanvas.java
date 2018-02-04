package clumsybot;

import clumsybot.bots.Bot;
import clumsybot.hud.HUD;
import clumsybot.maps.Map;
import clumsybot.maps.checks.BotPositionCheck;
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
        setupHUD();
    }

    private void setupHUD() {
        GameObject.add(new HUD());
    }

    private void setupMap() {
        Map map = Map.instance;
        map.position.set(100, 100);
        GameObject.add(map);
    }

    private void setupBot() {
        Bot.instance.forward();
        Bot.instance.pickUp();
        Bot.instance.forward();
        Bot.instance.right();
        Bot.instance.forward();
        Bot.instance.putDown();

        for(int i = 0; i < 3; i++) {
            Bot.instance.right();
        }

        Bot.instance.forward();
        Bot.instance.right();
        Bot.instance.forward();

        Bot.instance.check(new BotPositionCheck());
    }
}
