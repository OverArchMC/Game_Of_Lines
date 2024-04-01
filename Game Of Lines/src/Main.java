import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Board board = new Board(new int[][]{{0,0}, {1,1}, {-1, 1}});
        GUI gui = new GUI();
        while(true){
            for (int i = -32; i < 32; i++) {
                for (int j = -32; j < 32; j++) {
                    if (!board.cells.containsKey(hash(i,j))){
                        gui.putPixel(i,j, Color.gray);
                    }
                }
            }
            board.step(25, gui);
            gui.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static int hash(int x, int y){
        return x >= y ? x * x + x + y : x + y * y;
    }
}