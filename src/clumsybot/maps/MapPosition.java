package clumsybot.maps;

/**
 * Created by huynq on 1/29/18.
 */
public class MapPosition {
    public int col;
    public int row;

    public MapPosition(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public MapPosition clone() {
        return new MapPosition(this.col, this.row);
    }

    public MapPosition() {
        this(0, 0);
    }

    public void set(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void set(MapPosition other) {
        set(other.row, other.col);
    }

    public void addUp(int colDelta, int rowDelta) {
        this.col += colDelta;
        this.row += rowDelta;
    }

    public MapPosition add(int colDelta, int rowDelta) {
        return new MapPosition(this.col + colDelta, this.row + rowDelta);
    }
}
