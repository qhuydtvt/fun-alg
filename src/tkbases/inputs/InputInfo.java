package tkbases.inputs;

import tkbases.GameObject;
import tkbases.Vector2D;
import tkbases.renderers.ImageRenderer;
import tkbases.renderers.Renderer;
import tkbases.renderers.Transform;

/**
 * Created by huynq on 2/4/18.
 */
public class InputInfo {
    public Vector2D position;
    public boolean pressed;

    public InputInfo() {
        position = new Vector2D();
    }

    public boolean inside(GameObject gameObject) {
        Renderer renderer = gameObject.renderer;
        if (renderer instanceof ImageRenderer) {
            ImageRenderer imageRenderer = (ImageRenderer) renderer;
            Vector2D objectPosition = gameObject.getPosition();
            Vector2D anchor = gameObject.transform.anchor;
            Vector2D leftCorner = objectPosition.subtract(
                    imageRenderer.getWidth() * anchor.x,
                    imageRenderer.getHeight() * anchor.y);
            return position.x >= leftCorner.x && position.x <= leftCorner.x + imageRenderer.getWidth() &&
                    position.y >= leftCorner.y && position.y <= leftCorner.y + imageRenderer.getHeight();
        } else {
            return false;
        }
    }
}
