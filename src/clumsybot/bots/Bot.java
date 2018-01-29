package clumsybot.bots;

import clumsybot.Settings;
import tkbases.GameObject;
import tkbases.Vector2D;
import tkbases.actions.*;
import tkbases.renderers.ImageRenderer;

/**
 * Created by huynq on 1/28/18.
 */
public class Bot extends GameObject {
    public int col;
    public int row;
    public BotDirection direction;


    private ImageRenderer imageRenderer;

    private ActionSequence sequence;

    public static final Bot instance = new Bot();

    private Bot() {
        super();
        imageRenderer = new ImageRenderer("assets/images/purple_triangle.png");
        renderer = imageRenderer;
        col = 0;
        row = 0;
        direction = BotDirection.RIGHT;
        sequence = new ActionSequence();
        addAction(sequence);
    }

    public void forward() {
        Vector2D moveDelta = Vector2D.ZERO;
        switch (direction) {
            case LEFT:
                moveDelta = Vector2D.LEFT.multiply(Settings.MAP_CELL_SIZE);
                break;
            case RIGHT:
                moveDelta = Vector2D.RIGHT.multiply(Settings.MAP_CELL_SIZE);
                break;
            case UP:
                moveDelta = Vector2D.UP.multiply(Settings.MAP_CELL_SIZE);
                break;
            case DOWN:
                moveDelta = Vector2D.DOWN.multiply(Settings.MAP_CELL_SIZE);
                break;
        }

        sequence.addAction(new ActionMoveBy(moveDelta, 15));
    }

    public void right() {
        switch (direction) {
            case LEFT:
                this.direction = BotDirection.UP;
                break;
            case RIGHT:
                this.direction = BotDirection.DOWN;
                break;
            case UP:
                this.direction = BotDirection.RIGHT;
                break;
            case DOWN:
                this.direction = BotDirection.LEFT;
                break;
        }

        sequence.addAction(new ActionRotateBy(90, 15));
    }
}
