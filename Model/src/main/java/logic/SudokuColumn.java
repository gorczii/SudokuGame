package logic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

public class SudokuColumn implements SudokuStructure, Cloneable{
    private final int COLUMN_SIZE = 9;
    private SudokuField[] column = new SudokuField[9];

    @Override
    public boolean equals(Object newObject) {
        if (newObject == null) {
            return false;
        }
        if (!(newObject instanceof SudokuColumn)) {
            return false;
        }
        final SudokuColumn toCheck = (SudokuColumn) newObject;
        return Objects.equal(this.column, toCheck.column);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.column);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Values", column)
                .toString();
    }
    
    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {
        SudokuColumn clone = (SudokuColumn) super.clone();
        return clone;
    }

    public SudokuColumn(final List<List<SudokuField>> board, final int y) {
        for (int i = 0; i < COLUMN_SIZE; i++) {
            column[i] = new SudokuField();
        }

        for (int row = 0; row < COLUMN_SIZE; row++) {
            column[row].setFieldValue(board.get(row).get(y).getFieldValue());
        }
    }

    public boolean verify() {
        int[] tab = new int[COLUMN_SIZE];

        for (int row = 0; row < COLUMN_SIZE; row++) {
            tab[column[row].getFieldValue() - 1] = column[row].getFieldValue();
        }

        for (int i = 0; i < COLUMN_SIZE; i++) {
            if (tab[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
