package clumsybot;

import tkbases.GameWindow;

import java.util.Set;

/**
 * Created by huynq on 1/28/18.
 */
public class Program {
    public static void main(String[] args) {
        BotCanvas botCanvas = new BotCanvas(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        GameWindow window = new GameWindow(botCanvas);
        window.gameLoop();
    }
}
