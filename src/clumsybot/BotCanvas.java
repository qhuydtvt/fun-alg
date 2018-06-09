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
        setupHUD();
        Bot bot = new Bot();
        setupBot(bot);
        bot.check(new BotPositionCheck());
        Map.instance.addChild(bot);
    }

    private void setupHUD() {
        GameObject.add(new HUD());
    }

    private void setupMap() {
        Map map = Map.instance;
        map.position.set(100, 100);
        GameObject.add(map);
    }

    private void setupBot(Bot bot) {
        bot.forward();
        bot.pickUp();
        bot.forward();
        bot.right();
        bot.forward();
        bot.putDown();

        for(int i = 0; i < 3; i++) {
            bot.right();
        }

        bot.forward();
        bot.right();
        bot.forward();
    }
}
