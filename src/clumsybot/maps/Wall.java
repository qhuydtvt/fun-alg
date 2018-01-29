package clumsybot.maps;

import tkbases.renderers.ImageRenderer;

/**
 * Created by huynq on 1/30/18.
 */
public class Wall extends MapObject {
    public Wall() {
        super();
        this.renderer = new ImageRenderer("assets/images/wall.png");
    }
}
