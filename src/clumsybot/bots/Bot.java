package clumsybot.bots;

import clumsybot.MapDirection;
import clumsybot.Settings;
import clumsybot.maps.Map;
import clumsybot.maps.MapObject;
import clumsybot.maps.MapVector;
import clumsybot.maps.checks.Check;
import clumsybot.maps.checks.CheckResult;
import clumsybot.maps.pickables.Gem;
import clumsybot.maps.pickables.Pickable;
import clumsybot.maps.walls.Wall;
import tkbases.GameObject;
import tkbases.Vector2D;
import tkbases.actions.*;
import tkbases.renderers.ImageRenderer;
import tkbases.utils.AudioUtils;

import javax.sound.sampled.Clip;

import static clumsybot.Settings.soundEffectEnabled;

/**
 * Created by huynq on 1/28/18.
 */
public class Bot extends GameObject {
    private MapVector mapPosition;

    private MapDirection direction;

    private ImageRenderer imageRenderer;

    private ActionSequence sequence;

    private Pickable pickable;
    private Vector2D joint;
    private float startJointAngle;

    public static Bot getInstance() {
        return instance;
    }

    private static Bot instance;

    public Bot() {
        super();
        imageRenderer = new ImageRenderer("images/robot.png");
        renderer = imageRenderer;
        mapPosition = new MapVector();
        direction = MapDirection.RIGHT;
        sequence = new ActionSequence();
        addAction(sequence);
        instance = this;
    }

    private void appendAction(Action action) {
        sequence.addAction(action);
    }

    public MapVector getMapPosition() {
        return mapPosition;
    }

    public int getCol() {
        return mapPosition.col;
    }

    public int getRow() {
        return mapPosition.row;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public boolean wallAhead() {
        return Map.instance.objectAt(aheadMapPosition()) instanceof Wall;
    }

    public boolean gemAhead() {
        return Map.instance.objectAt(aheadMapPosition()) instanceof Gem;
    }

    private Vector2D facing() {
        switch (direction) {
            case LEFT:
                return Vector2D.LEFT.multiply(Settings.MAP_CELL_SIZE);
            case RIGHT:
                return Vector2D.RIGHT.multiply(Settings.MAP_CELL_SIZE);
            case UP:
                return Vector2D.UP.multiply(Settings.MAP_CELL_SIZE);
            case DOWN:
                return Vector2D.DOWN.multiply(Settings.MAP_CELL_SIZE);
            default:
                return Vector2D.RIGHT.multiply(Settings.MAP_CELL_SIZE);
        }
    }

    private MapVector mapFacing() {
        switch (direction) {
            case LEFT:
                return MapVector.LEFT.clone();
            case RIGHT:
                return MapVector.RIGHT.clone();
            case UP:
                return MapVector.UP.clone();
            case DOWN:
                return MapVector.DOWN.clone();
        }

        return MapVector.RIGHT;
    }

    public void forward() {
        if (!Map.instance.validPosition(aheadMapPosition())) return;

        mapPosition.addUp(mapFacing());
        appendAction(new ActionMoveBy(facing(), 15));
    }

    public void right() {
        switch (direction) {
            case LEFT:
                this.direction = MapDirection.UP;
                break;
            case RIGHT:
                this.direction = MapDirection.DOWN;
                break;
            case UP:
                this.direction = MapDirection.RIGHT;
                break;
            case DOWN:
                this.direction = MapDirection.LEFT;
                break;
        }
        appendAction(new ActionRotateBy(90, 15));
    }

    public void check(Check ch) {
        Clip winningSound = AudioUtils.loadSound("music/wining.wav");
        Clip lostSound = AudioUtils.loadSound("music/lost.wav");

        appendAction(new Action() {

            @Override
            public boolean execute(GameObject owner) {
                Bot bot = (Bot) owner;
                CheckResult result = ch.check(bot);
                if (result.pass) {
                    if (soundEffectEnabled) {
                        winningSound.start();
                    }
                } else {
                    if (soundEffectEnabled) {
                        lostSound.start();
                    }
                }
                return true;
            }

            @Override
            public void reset() {

            }
        });
    }

    private MapVector aheadMapPosition() {
        MapVector aheadPosition = mapPosition.clone();

        switch (direction) {
            case LEFT: aheadPosition.col--; break;
            case RIGHT: aheadPosition.col++; break;
            case UP: aheadPosition.row--; break;
            case DOWN: aheadPosition.row++; break;
        }

        return aheadPosition;
    }

    public void pickUp() {

        MapVector aheadPosition = this.aheadMapPosition();

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

            sequence.addAction(new ActionWait(10));
        }
    }

    public void putDown() {
        if (!Map.instance.validPosition(aheadMapPosition())) return;

        Map.instance.setMapObjectAt((MapObject)this.pickable, aheadMapPosition());
        Vector2D putDownPosition = Map.translate(aheadMapPosition());

        sequence.addAction(new Action() {
            @Override
            public boolean execute(GameObject owner) {
                Bot bot = (Bot)owner;
                bot.pickable.getPosition().set(putDownPosition);
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
