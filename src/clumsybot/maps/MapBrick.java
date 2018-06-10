package clumsybot.maps;

import tkbases.GameObject;
import tkbases.Vector2D;
import tkbases.renderers.ImageRenderer;

import java.awt.*;

/**
 * Created by huynq on 1/28/18.
 */
public class MapBrick extends GameObject {

    public MapBrick() {
        super();
        this.renderer = new ImageRenderer("images/brick.png");
    }
}
