package tkbases.utils;

import tkbases.GameObject;

import java.util.Vector;

/**
 * Created by huynq on 5/13/17.
 */
public class GameObjectPool {
    private static Vector<GameObject> gameObjects;

    static  {
        gameObjects = new Vector<>();
    }

    public static <E extends GameObject> E create(Class<E> classz) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getClass().equals(classz) && !gameObject.isActive()) {
                gameObject.reset();
                gameObject.setActive(true);
                return (E) gameObject;
            }
        }

        E gameObject = GameObject.create(classz);
        gameObjects.add(gameObject);
        return gameObject;
    }

    public static void clearAll() {
        gameObjects.clear();
    }
}
