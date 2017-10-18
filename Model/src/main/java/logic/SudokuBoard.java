package logic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuBoard implements Serializable, Cloneable {
    private final int BOARD_SIZE = 9;
    private final int BOX_SIZE = 3;
    private final int EMPTY_VALUE = 0;
    private List<List<SudokuField>> board;
    private List<SudokuField> board2;
    private String name = "board";

    @Override
    public boolean equals(Object newObject) {
        if (newObject == null) {
            return false;
        }
        if (!(newObject instanceof SudokuBoard)) {
            return false;
        }
        final SudokuBoard toCheck = (SudokuBoard) newObject;
        return Objects.equal(this.board, toCheck.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.board);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Values", board)
                .toString();
    }
    
    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard clone = (SudokuBoard) super.clone();
        return clone;
    }

    public SudokuBoard() {
        SudokuField[][] tempBoard = new SudokuField[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                tempBoard[i][j] = new SudokuField();
            }
        }
        board = Arrays.stream(tempBoard).map(Arrays::asList).collect(Collectors.toList());
    }

    public SudokuField getBoardElement(int row, int column) {
        return board.get(row).get(column);
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public int getBoxSize() {
        return BOX_SIZE;
    }

    public String getName() {
        return this.name;
    }

    public boolean setBoardElement(int row, int column, int value) {
        if (checkBoard(row, column, value)) {
            board.get(row).get(column).setFieldValue(value);
            return true;
        }
        return false;
    }

    public void setEmptyValue(int row, int column) {
        board.get(row).get(column).setFieldValue(EMPTY_VALUE);
    }

    public SudokuRow getRow(int x) {
        return new SudokuRow(board, x);
    }

    public SudokuColumn getColumn(int y) {
        return new SudokuColumn(board, y);
    }

    public SudokuBox getBox(int row, int column) {
        return new SudokuBox(board, row, column);
    }

    private boolean checkBoard(int row, int column, int value) {
        if (!isUsedInRow(row, value) && !isUsedInColumn(column, value) && !isUsedInBox(getBoxSize() * (row / getBoxSize()), getBoxSize() * (column / getBoxSize()), value)) {
            return true;
        }
        return false;
    }

    public boolean checkBoardResult(int row, int column, int value) {
        return checkBoard(row, column, value);
    }

    private boolean isUsedInRow(int row, int value) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board.get(row).get(i).getFieldValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsedInColumn(int column, int value) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board.get(i).get(column).getFieldValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsedInBox(int startRow, int startColumn, int value) {
        for (int row = 0; row < BOX_SIZE; row++) {
            for (int column = 0; column < BOX_SIZE; column++) {
                if (board.get(row + startRow).get(column + startColumn).getFieldValue() == value) {
                    return true;
                }
            }
        }
        return false;
    }
    
  /* public static void main(String[] args) {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard();
        solver.solve(board);
        DifficultLevel hard = new DifficultLevel();
        hard.prepareEmptyFields(board);
        int i = 0;
        
        while (i < 9) {
        for (int j=0; j < 9; j++) {
            System.out.print(board.getBoardElement(i, j).getFieldValue());
            }
        System.out.print(System.lineSeparator());
        i++;
        }
        
    }*/
}