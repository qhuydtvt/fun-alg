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

    private boolean environmentSetup;

    public BotCanvas() {
        this(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
    }

    public BotCanvas(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    public void setupEnvironment() {
        setupMap();
        setupHUD();
        environmentSetup = true;
    }

    public void setupBot() throws Exception {
        if(!environmentSetup) {
            throw new Exception("setupEnvironment() must be called first");
        }
        Bot bot = Bot.getInstance();
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
}
