package assignment5;

import java.util.Iterator;

public class StringGridIterator implements Iterator<String> {
    public final StringGrid stringGrid;
    public final boolean iterateOverColumns;
    private int columnIndex;
    private int rowIndex;

    public StringGridIterator(StringGrid stringGrid, boolean iterateOverColumns) {
        this.stringGrid = stringGrid;
        this.iterateOverColumns = iterateOverColumns;

        // Sets the first value to iterate to -1 and the other to 0
        // this is because you do not want to iterate the first run
        // so you can get the first element
        this.columnIndex = iterateOverColumns ? -1 : 0;
        this.rowIndex = iterateOverColumns ? 0 : -1;
    }

    public boolean hasNext() {
        int maxStringGridIndex = this.stringGrid.getRowCount() * this.stringGrid.getColumnCount();
        int currentIndex = this.stringGrid.getColumnCount() * rowIndex + columnIndex;

        return currentIndex < maxStringGridIndex - 1;
    }

    public String next() {
        this.iterateIndices();

        return this.stringGrid.getElement(this.rowIndex, this.columnIndex);
    }

    private void iterateIndices() {
        if (this.iterateOverColumns) {
            this.iterateColumnIndex();
        } else {
            this.iterateRowIndex();
        }
    }

    private void iterateColumnIndex() {
        int maxColumnIndex = this.stringGrid.getColumnCount() - 1;

        if (this.columnIndex < maxColumnIndex) {
            this.columnIndex += 1;
        } else {
            this.columnIndex = 0;
            this.rowIndex += 1;
        }
    }

    private void iterateRowIndex() {
        int maxRowIndex = this.stringGrid.getRowCount() - 1;

        if (this.rowIndex < maxRowIndex) {
            this.rowIndex += 1;
        } else {
            this.rowIndex = 0;
            this.columnIndex += 1;
        }
    }
}
