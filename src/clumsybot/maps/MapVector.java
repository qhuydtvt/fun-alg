package clumsybot.maps;

/**
 * Created by huynq on 1/29/18.
 */
public class MapVector {
    public int col;
    public int row;

    public static final MapVector RIGHT = new MapVector(1, 0);
    public static final MapVector LEFT = new MapVector(1, 0);
    public static final MapVector UP = new MapVector(0, -1);
    public static final MapVector DOWN = new MapVector(0, 1);

    public MapVector(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public MapVector clone() {
        return new MapVector(this.col, this.row);
    }

    public MapVector() {
        this(0, 0);
    }

    public void set(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void set(MapVector other) {
        set(other.row, other.col);
    }

    public void addUp(int colDelta, int rowDelta) {
        this.col += colDelta;
        this.row += rowDelta;
    }

    public void addUp(MapVector other) {
        addUp(other.col, other.row);
    }

    public MapVector add(int colDelta, int rowDelta) {
        return new MapVector(this.col + colDelta, this.row + rowDelta);
    }

    public MapVector add(MapVector other) {
        return add(other.col, other.row);
    }
}
