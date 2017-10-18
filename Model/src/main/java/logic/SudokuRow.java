package logic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

public class SudokuRow implements SudokuStructure, Cloneable {
    private final int ROW_SIZE = 9;
    private SudokuField[] row = new SudokuField[ROW_SIZE];

    @Override
    public boolean equals(Object newObject) {
        if (newObject == null) {
            return false;
        }
        if (!(newObject instanceof SudokuRow)) {
            return false;
        }
        final SudokuRow toCheck = (SudokuRow) newObject;
        return Objects.equal(this.row, toCheck.row);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.row);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Values", row)
                .toString();
    }
    
    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        SudokuRow clone = (SudokuRow) super.clone();
        return clone;
    }


    public SudokuRow(final List<List<SudokuField>> board, final int x) {
        for (int i = 0; i < ROW_SIZE; i++) {
            row[i] = new SudokuField();
        }

        for (int column = 0; column < ROW_SIZE; column++) {
            row[column].setFieldValue(board.get(x).get(column).getFieldValue());
        }
    }

    public boolean verify() {
        int[] tab = new int[ROW_SIZE];

        for (int column = 0; column < ROW_SIZE; column++) {
            tab[row[column].getFieldValue() - 1] = row[column].getFieldValue();
        }

        for (int i = 0; i < ROW_SIZE; i++) {
            if (tab[i] == 0) {
                return false;
            }
        }
        return true;
    }

}
