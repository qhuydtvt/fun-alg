package tkbases.renderers;

import tkbases.Vector2D;
import tkbases.utils.SpriteUtils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 5/11/17.
 */
public class ImageRenderer implements Renderer, Transformable {

    private BufferedImage image;
    public Transform transform;

    public ImageRenderer(BufferedImage image) {
        this.image = image;
        this.transform = new Transform();
    }

    public ImageRenderer(String url) {
        this(SpriteUtils.loadImage(url));
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        transform.render(graphics, image, position);
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
