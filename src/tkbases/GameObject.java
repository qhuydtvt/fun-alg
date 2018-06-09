package tkbases;

import tkbases.actions.Action;
import tkbases.renderers.Renderer;
import tkbases.renderers.Transform;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

/**
 * Created by huynq on 5/11/17.
 */
public class GameObject {

    protected boolean isActive = true;
    public Vector2D position;
    public Transform transform;
    public Renderer renderer;
    protected List<GameObject> children;
    protected Vector2D screenPosition;

    private static List<GameObject> gameObjects;
    private static List<GameObject> newGameObjects;
    private static final Object gameObjectLock = new Object();

    private List<Action> actionList;

    static {
        gameObjects = new Vector<>();
        newGameObjects = new Vector<>();
    }

    public static void runAll() {
        for (GameObject gameObject: gameObjects) {
            if (gameObject.isActive()) {
                gameObject.update(null);
            }
        }


        for (GameObject gameObject: gameObjects) {
            if (gameObject.isActive()) {
                gameObject.executeAction();
            }
        }

        synchronized (gameObjectLock) {
            for (GameObject newGameObject: newGameObjects) {
                gameObjects.add(newGameObject);
            }
        }

        newGameObjects.clear();
    }

    public static void renderAll(Graphics graphics, Vector2D rootPosition) {
        synchronized (gameObjectLock) {
            for (GameObject gameObject : gameObjects) {
                if (gameObject.isActive()) {
                    gameObject.render(graphics, rootPosition);
                }
            }
        }
    }

    public static <T> T find(Class<T> classz) {
        synchronized (gameObjectLock) {
            for (GameObject gameObject: gameObjects) {
                if (gameObject.isActive()) {
                    if (gameObject.getClass().equals(classz)) {
                        return (T) gameObject;
                    }
                }
            }
        }
        return null;
    }

    public static void clearAll() {
        synchronized (gameObjectLock) {
            gameObjects.clear();
        }
    }

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public static <E extends GameObject> E create(Class<E> clazz) {
        try {
            E newGameObject = clazz.newInstance();
            add(newGameObject);
            return newGameObject;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GameObject() {
        this(new Vector2D(0,0));
    }

    public GameObject(Vector2D position) {
        this(position, null);
    }

    public GameObject(Vector2D position, Renderer renderer) {
        this.position = position;
        this.screenPosition = new Vector2D(position.x, position.y);
        this.renderer = renderer;
        this.transform = new Transform();
        children = new Vector<>();
        actionList = new Vector<>();
    }

    public GameObject setRenderer(Renderer renderer) {
        this.renderer = renderer;
        return this;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public void render(Graphics graphics, Vector2D rootPosition) {
        if (isActive) {
            Vector2D drawPosition = position.add(rootPosition);
            if (renderer != null) {
                renderer.render(graphics, drawPosition, transform);
            }
            for (GameObject gameObject: children) {
                gameObject.render(graphics, drawPosition);
            }
        }
    }

    public void update(GameObject parent) {
        if (isActive) {
            if(parent == null) {
                screenPosition.set(position);
            } else {
                screenPosition.set(parent.position.add(position));
            }
            for (GameObject gameObject : children) {
                gameObject.update(this);
            }
        }
    }

    public void executeAction() {
        actionList.removeIf(action -> action.execute(this));
        children.forEach(GameObject::executeAction);
    }

    public void addAction(Action action) {
        actionList.add(action);
    }

    public void addChild(GameObject gameObject) {
        this.children.add(gameObject);
    }

    public void reset() {
        actionList.clear();
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getScreenPosition() {
        return screenPosition;
    }
}
