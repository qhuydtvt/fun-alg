package clumsybot.maps.pickables;

import clumsybot.MapDirection;
import clumsybot.maps.MapObject;
import tkbases.renderers.ImageRenderer;

/**
 * Created by huynq on 1/29/18.
 */
public class Gem extends MapObject implements Pickable {

    public Gem() {
        super();
        this.renderer = new ImageRenderer("assets/images/small_blue_square.png");
    }

    @Override
    public void move(MapDirection direction) {
    }

    @Override
    public void pickUp() {

    }

    @Override
    public void putDown() {

    }
}
