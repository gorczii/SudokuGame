package logic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

public class SudokuBox implements SudokuStructure, Cloneable{
    private final int BOX_SIZE = 3;
    private SudokuField[][] box = new SudokuField[BOX_SIZE][BOX_SIZE];
    private int x;

    @Override
    public boolean equals(Object newObject) {
        if (newObject == null) {
            return false;
        }
        if (!(newObject instanceof SudokuBox)) {
            return false;
        }
        final SudokuBox to_check = (SudokuBox) newObject;
        return Objects.equal(this.box, to_check.box);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.box);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Values", box)
                .toString();
    }
    
    @Override
    public SudokuBox clone() throws CloneNotSupportedException {
        SudokuBox clone = (SudokuBox) super.clone();
        return clone;
    }

    public SudokuBox(final List<List<SudokuField>> board, final int x, final int y) {
        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                box[i][j] = new SudokuField();
            }
        }

        for (int row = x; row < BOX_SIZE; row++) {
            for (int column = y; column < BOX_SIZE; column++) {
                box[row][column].setFieldValue(board.get(row).get(column).getFieldValue());
            }
        }
    }

    public boolean verify() {
        int[] tab = new int[BOX_SIZE * BOX_SIZE];

        for (int row = 0; row < BOX_SIZE; row++) {
            for (int column = 0; column < BOX_SIZE; column++) {
                tab[box[row][column].getFieldValue() - 1] = box[row][column].getFieldValue();
            }
        }

        for (int i = 0; i < BOX_SIZE * BOX_SIZE; i++) {
            if (tab[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
