package clumsybot.maps;

import clumsybot.Settings;
import tkbases.GameObject;
import tkbases.Vector2D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by huynq on 1/28/18.
 */
public class Map extends GameObject {
    public int numOfColumns;
    public int numOfRows;

    public Map(int numOfColumns, int numOfRows) {
        super();
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.setupCells();
    }

    private void setupCells() {
        for(int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfColumns; col++) {
                MapCell newMapCell = new MapCell();
                newMapCell.position.set(
                        col * Settings.MAP_CELL_SIZE,
                        row * Settings.MAP_CELL_SIZE);
                this.children.add(newMapCell);
            }
        }
    }

    @Override
    public void render(Graphics graphics, Vector2D rootPosition) {
        super.render(graphics, rootPosition);
    }
}
