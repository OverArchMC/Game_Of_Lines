import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import static java.lang.Math.abs;
public class Board {
    private final HashMap<Integer, HashSet<Integer>> pairs = new HashMap<>();
    public final HashMap<Integer, Cell> cells;
    public final ArrayList<Cell> cellIndex;
    private int min;
    public Board(int[][] initial){
        this.min = 0;
        this.cells = new HashMap<>();
        this.cellIndex = new ArrayList<>();
        for (int[] coord : initial){
            Cell cell = new Cell(coord, true, cellIndex.size());
            cells.put(cell.coordsID(), cell);
            cellIndex.add(cell);
            pairs.put(cell.id, new HashSet<>());
        }
    }

    public void step(int num, GUI gui){
        for (int i = 0; i < num; i++) {
            int[] line = nextLine();
            int sum = sumOfLine(line);
            while (sum != 0){
                int[] coord = nextPlaceLocation(line);
                gui.putPixel(coord[0], coord[1], Color.blue);
                gui.drawLine(cellIndex.get(line[0]).coords(), cellIndex.get(line[1]).coords());
                Cell cell = new Cell(coord, sum < 0, cellIndex.size());
                cells.put(cell.coordsID(), cell);
                cellIndex.add(cell);
                pairs.put(cell.id, new HashSet<>());
                sum += -1*(sum)/abs(sum);
            }
        }
    }
    private int sumOfLine(int[] line){
        int sum = 0;
        for (Cell c : cellIndex){
            if (doesIntersect(c.coords(), line)){
                sum += c.getState();
            }
        }
        return sum;
    }
    private boolean antiBias = false;
    private int[] nextPlaceLocation(int[] line){
        int offset = 0;
        int[] a = cellIndex.get(line[0]).coords();
        int[] b = cellIndex.get(line[1]).coords();
        while(true){
            int[] posA;
            int[] posB;
            if (a[0] == b[0]){
                posA = new int[]{a[0], offset};
                posB = new int[]{a[0], -offset};
            }
            else if (a[1] == b[1]){
                posA = new int[]{offset, a[1]};
                posB = new int[]{-offset, a[1]};
            }
            else{
                posA = lineComputations.integerPoint(a, b, offset);
                posB = lineComputations.integerPoint(a, b, -offset);
            }
            if(antiBias) {
                if (!cells.containsKey(posA[0] >= posA[1] ? posA[0] * posA[0] + posA[0] + posA[1] : posA[0] + posA[1] * posA[1])) {
                    antiBias = false;
                    return posA;
                }
                if (!cells.containsKey(posB[0] >= posB[1] ? posB[0] * posB[0] + posB[0] + posB[1] : posB[0] + posB[1] * posB[1])) {
                    antiBias = false;
                    return posB;
                }
            }
            else {
                if (!cells.containsKey(posB[0] >= posB[1] ? posB[0] * posB[0] + posB[0] + posB[1] : posB[0] + posB[1] * posB[1])) {
                    antiBias = true;
                    return posB;
                }
                if (!cells.containsKey(posA[0] >= posA[1] ? posA[0] * posA[0] + posA[0] + posA[1] : posA[0] + posA[1] * posA[1])) {
                    antiBias = true;
                    return posA;
                }
            }
            offset += 1;
        }
    }
    private boolean doesIntersect(int[] coords, int[] line){
        int[] a = cellIndex.get(line[0]).coords();
        int[] b = cellIndex.get(line[1]).coords();
        return (coords[1] - b[1]) * (b[0] - a[0]) ==
                (b[1] - a[1]) * (coords[0] - b[0]);
    }
    //TODO Optimize by making it only traverse half the list
    private int[] nextLine(){
        for (int i = 0; i < cellIndex.size(); i++) {
            if (i+cellIndex.size()-1 >= min && min - i != i && !(i > min)){
                if(!pairs.get(i).contains(min-i)){
                    pairs.get(i).add(min-i);
                    pairs.get(min-i).add(i);
                    return new int[]{i, min-i};
                }
            }
        }
        min += 1;
        return nextLine();
    }
}
