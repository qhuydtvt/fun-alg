package clumsybot.maps;

import tkbases.GameObject;
import tkbases.Vector2D;
import tkbases.renderers.ImageRenderer;

import java.awt.*;

/**
 * Created by huynq on 1/28/18.
 */
public class MapCell extends GameObject {

    public MapCell() {
        super();
        this.renderer = new ImageRenderer("assets/images/brick.png");
    }
}
