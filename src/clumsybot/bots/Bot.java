package clumsybot.bots;

import clumsybot.BotDirection;
import clumsybot.Settings;
import clumsybot.grabables.Pickable;
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

    private Pickable pickable;
    private Vector2D joint;
    private float startJointAngle;

    public static final Bot instance = new Bot();

    private Bot() {
        super();
        imageRenderer = new ImageRenderer("assets/images/robot.png");
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
                col--;
                moveDelta = Vector2D.LEFT.multiply(Settings.MAP_CELL_SIZE);
                break;
            case RIGHT:
                col++;
                moveDelta = Vector2D.RIGHT.multiply(Settings.MAP_CELL_SIZE);
                break;
            case UP:
                row--;
                moveDelta = Vector2D.UP.multiply(Settings.MAP_CELL_SIZE);
                break;
            case DOWN:
                row++;
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

    public void pickUp(Pickable pickable) {
        this.pickable = pickable;
        this.joint = pickable.getPosition().subtract(this.position);
        this.startJointAngle = transform.angle;
    }

    public void putDown() {

    }

    @Override
    public void update(GameObject parent) {
        super.update(parent);
        if (this.pickable != null) {
            pickable.getPosition().set(this.position.add(this.joint.rotate(transform.angle - startJointAngle)));
        }
    }
}
