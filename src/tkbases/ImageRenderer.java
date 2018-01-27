package tkbases;

import tkbases.utils.SpriteUtils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 5/11/17.
 */
public class ImageRenderer implements Renderer {

    private BufferedImage image;

    public ImageRenderer(BufferedImage image) {
        this.image = image;
    }

    public ImageRenderer(String url) {
        this(SpriteUtils.loadImage(url));
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        SpriteUtils.renderAtCenter(graphics, image, position.x, position.y);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
