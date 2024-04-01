public class Cell {
    public int id;
    public int x;
    public int y;
    public final boolean state;

    public int getState() {
        if (state) {
            return 1;
        } else {
            return -1;
        }
    }

    public Cell(int[] coords, boolean state, int id) {
        this.id = id;
        this.x = coords[0];
        this.y = coords[1];
        this.state = state;
    }

    public int[] coords() {
        return new int[]{x, y};
    }

    public int coordsID() {
        return x >= y ? x * x + x + y : x + y * y;
    }
}