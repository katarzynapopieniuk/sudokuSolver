import java.util.ArrayList;

public class SudokuGrid {

    private int[][] grid;
    private int size;

    public SudokuGrid( int size) {
        this.size = size;
        this.grid = new int[size][size];
    }

    public SudokuGrid(int[][] grid) throws InvalidGridSizeException {
        this.grid = grid;
        int firstRowLength = grid[0].length;
        for(int[] row : grid) {
            if(row.length != firstRowLength)
                throw new InvalidGridSizeException("Grid isn't square");
        }
    }

    public int getSize() {
        return size;
    }

    public int getValue(int row, int column) throws InvalidCoordinateException {
        if(!isPointValid(row,column))
            throw new InvalidCoordinateException("Coordinates out of range");
        return grid[row][column];
    }

    public void setValue(int row, int column, int value) throws InvalidCoordinateException {
        if(!isValueValid(value))
            throw new IllegalArgumentException("value out of range");
        if(!isPointValid(row,column))
            throw new InvalidCoordinateException("Coordinates out of range");
        grid[row][column] = value;
    }

    private boolean isPointValid(int row, int column) {
        if( row < 0 || row >= size || column < 0 || column > size )
            return false;
        else
            return true;
    }

    private boolean isValueValid(int value) {
        if( value <= 0 || value > size)
            return false;
        else
            return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<size*5; i++)
            builder.append("_");
        builder.append("\n");
        for(int[] row : grid) {
            for(int i=0; i<size; i++)
                builder.append(" | " + row[i]);
            builder.append(" |\n");
            for(int i=0; i<size*5; i++)
                builder.append("_");
            builder.append("\n");
        }
        return builder.toString();
    }
}
