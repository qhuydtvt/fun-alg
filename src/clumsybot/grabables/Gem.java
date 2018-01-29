package clumsybot.grabables;

import clumsybot.BotDirection;
import tkbases.GameObject;
import tkbases.renderers.ImageRenderer;

/**
 * Created by huynq on 1/29/18.
 */
public class Gem extends GameObject implements Pickable {
    public Gem() {
        super();
        this.renderer = new ImageRenderer("assets/images/small_blue_square.png");
    }

    @Override
    public void move(BotDirection direction) {

    }
}
