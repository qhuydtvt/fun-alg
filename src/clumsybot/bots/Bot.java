package clumsybot.bots;

import clumsybot.BotDirection;
import clumsybot.Settings;
import clumsybot.maps.Map;
import clumsybot.maps.MapObject;
import clumsybot.maps.MapPosition;
import clumsybot.maps.pickables.Pickable;
import tkbases.GameObject;
import tkbases.Vector2D;
import tkbases.actions.*;
import tkbases.renderers.ImageRenderer;

import java.util.Set;

/**
 * Created by huynq on 1/28/18.
 */
public class Bot extends GameObject {
    public MapPosition mapPosition;

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
        mapPosition = new MapPosition();
        direction = BotDirection.RIGHT;
        sequence = new ActionSequence();
        addAction(sequence);
    }


    public void forward() {
        if (!Map.instance.validPosition(aheadMapPosition())) return;

        Vector2D moveDelta = Vector2D.ZERO;

        switch (direction) {
            case LEFT:
                mapPosition.col--;
                moveDelta = Vector2D.LEFT.multiply(Settings.MAP_CELL_SIZE);
                break;
            case RIGHT:
                mapPosition.col++;
                moveDelta = Vector2D.RIGHT.multiply(Settings.MAP_CELL_SIZE);
                break;
            case UP:
                mapPosition.row--;
                moveDelta = Vector2D.UP.multiply(Settings.MAP_CELL_SIZE);
                break;
            case DOWN:
                mapPosition.row++;
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

    private MapPosition aheadMapPosition() {
        MapPosition aheadPosition = mapPosition.clone();

        switch (direction) {
            case LEFT: aheadPosition.col--; break;
            case RIGHT: aheadPosition.col++; break;
            case UP: aheadPosition.row--; break;
            case DOWN: aheadPosition.row++; break;
        }

        return aheadPosition;
    }

    private Vector2D aheadPosition() {
        Vector2D returnValue = position.clone();

        switch (direction) {
            case LEFT: returnValue.x -= Settings.MAP_CELL_SIZE; break;
            case RIGHT: returnValue.x += Settings.MAP_CELL_SIZE; break;
            case UP: returnValue.y -= Settings.MAP_CELL_SIZE; break;
            case DOWN: returnValue.y += Settings.MAP_CELL_SIZE; break;
        }

        return returnValue;
    }

    public void pickUp() {

        MapPosition aheadPosition = this.aheadMapPosition();

        MapObject mapObject = Map.instance.objectAt(aheadPosition);

        if (mapObject instanceof Pickable) {
            Pickable pickable = (Pickable)mapObject;

            Map.instance.setMapObjectAt(null, aheadPosition);

            sequence.addAction(new Action() {
                @Override
                public boolean execute(GameObject owner) {
                    Bot bot = (Bot)owner;
                    bot.pickable = pickable;
                    bot.joint = pickable.getPosition().subtract(bot.position).multiply(0.4);
                    bot.startJointAngle = bot.transform.angle;
                    return true;
                }

                @Override
                public void reset() {

                }
            });
        }
    }

    public void putDown() {
        if(Map.instance.objectAt(aheadMapPosition()) != null) return;

        Vector2D putDownPosition = Map.translate(aheadMapPosition());

        sequence.addAction(new Action() {
            @Override
            public boolean execute(GameObject owner) {
                Bot bot = (Bot)owner;
                bot.pickable.getPosition().set(putDownPosition);
                Map.instance.setMapObjectAt((MapObject)bot.pickable, aheadMapPosition());
                bot.pickable = null;
                bot.joint = null;
                bot.startJointAngle = 0;
                return true;
            }

            @Override
            public void reset() {

            }
        });
    }

    @Override
    public void update(GameObject parent) {
        super.update(parent);
        if (this.pickable != null) {
            pickable.getPosition().set(this.position.add(this.joint.rotate(transform.angle - startJointAngle)));
        }
    }
}
