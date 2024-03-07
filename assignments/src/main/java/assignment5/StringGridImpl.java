package assignment5;

import java.util.ArrayList;

public class StringGridImpl implements StringGrid {
    private ArrayList<String> strings = new ArrayList<>();
    private int columnCount;
    private int rowCount;

    public StringGridImpl(int rowCount, int columnCount) {
        this.columnCount = columnCount;
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public String getElement(int row, int column) {
        return strings.get(translateTo2DIndex(row, column));
    }

    public void setElement(int row, int column, String element) {
        strings.add(translateTo2DIndex(row, column), element);
    }

    private int translateTo2DIndex(int row, int column) {
        return row * this.columnCount + column;
    }
}
