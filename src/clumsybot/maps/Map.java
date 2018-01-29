package clumsybot.maps;

import clumsybot.Settings;
import clumsybot.bots.Bot;
import clumsybot.maps.pickables.Gem;
import tkbases.GameObject;
import tkbases.Vector2D;

/**
 * Created by huynq on 1/28/18.
 */
public class Map extends GameObject {
    public int numOfColumns;
    public int numOfRows;

    public MapObject[][] mapObjects;

    public static final Map instance = new Map(10, 10);

    private Map(int numOfColumns, int numOfRows) {
        super();
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.setupCells();
        this.setupMapObjects();
        this.setupGems();
        this.setupBot();
    }

    private void setupMapObjects() {
        mapObjects = new MapObject[numOfRows][numOfColumns];
    }

    private <T extends MapObject> T addMapObject(T object, int row, int col) {
        mapObjects[row][col] = object;
        this.children.add(object);
        object.position.set(col * Settings.MAP_CELL_SIZE, row * Settings.MAP_CELL_SIZE);
        return object;
    }

    public void setMapObjectAt(MapObject mapObject, int row, int col) {
        if (row >= 0 && row < numOfRows && col >= 0 && col < numOfColumns) {
            mapObjects[row][col] = mapObject;
        }
    }

    public void setMapObjectAt(MapObject mapObject, MapPosition mapPosition) {
        setMapObjectAt(mapObject, mapPosition.row, mapPosition.col);
    }

    public MapObject objectAt(MapPosition position) {
        return objectAt(position.row, position.col);
    }

    public MapObject objectAt(int row, int col) {
        if (row >= 0 && row < numOfRows && col >= 0 && col < numOfColumns) {
            return mapObjects[row][col];
        }
        return null;
    }

    public boolean validPosition(int row, int col) {
        if (row >= 0 && row < numOfRows && col >= 0 && col < numOfColumns) {
            return mapObjects[row][col] == null;
        }
        return false;
    }

    public boolean validPosition(MapPosition position) {
        return validPosition(position.row, position.col);
    }

    private void setupGems() {
        addMapObject(new Gem(), 0, 1);
        addMapObject(new Wall(), 1, 1);
    }

    private void setupBot() {
        this.children.add(Bot.instance);
    }

    private void setupCells() {
        for(int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfColumns; col++) {
                MapBrick newMapBrick = new MapBrick();
                newMapBrick.position.set(
                        col * Settings.MAP_CELL_SIZE,
                        row * Settings.MAP_CELL_SIZE);
                this.children.add(newMapBrick);
            }
        }
    }

    public static Vector2D translate(MapPosition position) {
        return new Vector2D(position.col * Settings.MAP_CELL_SIZE, position.row * Settings.MAP_CELL_SIZE);
    }
}
