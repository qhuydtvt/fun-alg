package tkbases.renderers;

import tkbases.Vector2D;
import tkbases.renderers.Renderer;
import tkbases.utils.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import java.awt.Graphics;

/**
 * Created by huynq on 5/12/17.
 */
public class Animation implements Renderer {

    protected List<BufferedImage> imageList;
    private boolean loop;
    private boolean hasEnded;
    private int delayCount;
    private int delayMultiplier;
    private int imageIndex;
    private boolean blink;
    private boolean reverseOrder;

    public Animation(boolean loop, BufferedImage[] images, int delayMultiplier) {
        this(loop, images, delayMultiplier, false);
    }

    public Animation(boolean loop, BufferedImage[] images, int delayMultiplier, boolean reverseOrder) {
        this.loop = loop;
        this.delayMultiplier = delayMultiplier;
        imageList = new java.util.Vector();

        if (reverseOrder) {
            for (int imageIndex = images.length - 1; imageIndex >= 0; imageIndex--) {
                imageList.add(images[imageIndex]);
            }
        } else {
            Collections.addAll(imageList, images);
        }
        this.reverseOrder = reverseOrder;
    }

    public void reset() {
        imageIndex = 0;
        hasEnded = false;
    }

    public void setBlink(boolean blink) {
        this.blink = blink;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void render(Graphics graphics, Vector2D position, Transform transform) {
        if (loop || !hasEnded) {
            delayCount++;
            if (delayCount > delayMultiplier) {
                delayCount = 0;
                imageIndex++;
                if (!loop && imageIndex == imageList.size() - 1) {
                    hasEnded = true;
                }
                imageIndex %= imageList.size();
            }
        }

        if (blink && (imageIndex % 2 == 0)) {
            SpriteUtils.renderAtCenter(graphics, SpriteUtils.maskWhite(imageList.get(imageIndex)), position.x, position.y);
        } else {
            SpriteUtils.renderAtCenter(graphics, imageList.get(imageIndex), position.x, position.y);
        }
    }

    public boolean isHasEnded() {
        return hasEnded;
    }
}
