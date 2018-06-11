package clumsybot;

import clumsybot.bots.Bot;
import tkbases.GameWindow;

/**
 * Created by huynq on 1/28/18.
 */
public class Program {
    public static void main(String[] args) {
        BotWindow botWindow = new BotWindow();

        Bot bot = new Bot();
        bot.forward();
        bot.pickUp();
        bot.forward();
        bot.right();
        for(int i = 0; i < 5; i++) {
            bot.forward();
        }

        botWindow.mainLoop();
    }
}
