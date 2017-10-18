package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BacktrackingSudokuSolverTest {
    private  BacktrackingSudokuSolver solver;
    private SudokuBoard board;

    @Before
    public void initObjects() {
        solver = new BacktrackingSudokuSolver();
        board = new SudokuBoard();
    }

    @Test
    public void solveColumnTest() {
        solver.solve(board);
        int[] tab = new int[9];
        for (int column = 0; column < board.getBoardSize(); column++) {
            for (int row = 0; row < board.getBoardSize(); row++) {
                tab[board.getBoardElement(row, column).getFieldValue() - 1] = board.getBoardElement(row, column).getFieldValue();
            }
            for (int j = 0; j < board.getBoardSize(); j++) {
                assertSame(tab[j], j + 1);
            }
        }
    }

    @Test
    public void solveRowTest() {
        solver.solve(board);
        int[] tab = new int[9];
        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int column = 0; column < board.getBoardSize(); column++) {
                tab[board.getBoardElement(row, column).getFieldValue() - 1] = board.getBoardElement(row, column).getFieldValue();
            }
            for (int j = 0; j < board.getBoardSize(); j++) {
                assertSame(tab[j], j + 1);
            }
        }
    }

    @Test
    public void solveBoxTest() {
        solver.solve(board);
        int[] tab = new int[9];
        for (int row = 0; row < board.getBoardSize(); row += 3) {
            for (int column = 0; column < board.getBoardSize(); column += 3) {

                for (int boxRow = row; boxRow < board.getBoxSize(); boxRow++) {
                    for (int boxColumn = column; boxColumn < board.getBoxSize(); boxColumn++) {
                        tab[board.getBoardElement(boxRow, boxColumn).getFieldValue() - 1] = board.getBoardElement(boxRow, boxColumn).getFieldValue();
                    }
                }
                for (int j = 0; j < board.getBoardSize(); j++) {
                    assertSame(tab[j], j + 1);
                }
            }
        }
    }

}