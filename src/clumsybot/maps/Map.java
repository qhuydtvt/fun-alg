package clumsybot.maps;

import clumsybot.Settings;
import clumsybot.bots.Bot;
import clumsybot.grabables.Gem;
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

    public static final Map instance = new Map(10, 10);

    private Map(int numOfColumns, int numOfRows) {
        super();
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.setupCells();
        this.setupGems();
        this.setupBot();
    }

    private void setupGems() {
        Gem gem = new Gem();
        gem.position.set(Settings.MAP_CELL_SIZE, 0);
        this.children.add(gem);
        Bot.instance.pickUp(gem);
    }

    private void setupBot() {
//        Bot.instance.right();
        this.children.add(Bot.instance);
        Bot.instance.right();
        Bot.instance.forward();
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
}
