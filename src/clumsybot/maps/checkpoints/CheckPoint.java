package clumsybot.maps.checkpoints;

import clumsybot.maps.MapObject;
import tkbases.renderers.ImageRenderer;

/**
 * Created by huynq on 2/2/18.
 */
public class CheckPoint extends MapObject {
    public CheckPoint() {
        super();
        this.renderer = new ImageRenderer("assets/images/red_dot.png");
    }
}
