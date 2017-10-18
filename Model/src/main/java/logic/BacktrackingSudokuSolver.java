package logic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;


public class BacktrackingSudokuSolver implements SudokuSolver {
    private final int MAX_SUDOKU_VALUE = 9;
    private final int MIN_SUDOKU_VALUE = 1;
    private String name = "Backtracking";

    @Override
    public boolean equals(Object newObject) {
        if (newObject == null) {
            return false;
        }
        if (!(newObject instanceof BacktrackingSudokuSolver)) {
            return false;
        }
        final BacktrackingSudokuSolver toCheck = (BacktrackingSudokuSolver) newObject;
        return Objects.equal(this.name, toCheck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", name)
                .toString();
    }

    public boolean solve(SudokuBoard board) {

        int[] emptyCell = findEmptyCell(board);
        int row = emptyCell[0];
        int column = emptyCell[1];

        if (row == -1) {
            return true;
        }
        for (int i = 0; i < board.getBoardSize(); i++) {
            int temp = generateRandomNumber();
            if (board.setBoardElement(row, column, temp)) {
                if (solve(board)) {
                    return true;
                }
                board.setEmptyValue(row, column);
            }
        }
        return false;
    }

    private int generateRandomNumber() {
        int range = (MAX_SUDOKU_VALUE - MIN_SUDOKU_VALUE) + 1;
        return (int) (Math.random() * range) + MIN_SUDOKU_VALUE;
    }

    private int[] findEmptyCell(SudokuBoard board) {
        int[] cell = new int[2]; // cell[0]-row cell[1] -column
        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int column = 0; column < board.getBoardSize(); column++) {
                if (board.getBoardElement(row, column).getFieldValue() == 0) {
                    cell[0] = row;
                    cell[1] = column;
                    return cell;
                }
            }
        }
        cell[0] = -1;
        cell[1] = -1;
        return cell;
    }

    private boolean isSafe(int row, int column, int value, SudokuBoard board) {
        if (!isUsedInRow(row, value, board) && !isUsedInColumn(column, value, board) && !isUsedInBox(board.getBoxSize() * (row / board.getBoxSize()), board.getBoxSize() * (column / board.getBoxSize()), value, board)) {
            return true;
        }
        return false;
    }

    private boolean isUsedInRow(int row, int value, SudokuBoard board) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            if (board.getBoardElement(row, i).getFieldValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsedInColumn(int column, int value, SudokuBoard board) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            if (board.getBoardElement(i, column).getFieldValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsedInBox(int startRow, int startColumn, int value, SudokuBoard board) {
        for (int row = 0; row < board.getBoxSize(); row++) {
            for (int column = 0; column < board.getBoxSize(); column++) {
                if (board.getBoardElement(row + startRow, column + startColumn).getFieldValue() == value) {
                    return true;
                }
            }
        }
        return false;
    }
}