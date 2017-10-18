package logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuBoardTest {
    private SudokuBoard board;
    private BacktrackingSudokuSolver solver;
    private final int MAX_SUDOKU_INDEX = 8;
    private final int MIN_SUDOKU_INDEX = 0;
    private final int BOARD_SIZE = 9;
    private final int MAX_SUDOKU_VALUE = 9;
    private final int MIN_SUDOKU_VALUE = 1;

    @Before
    public void initObjects() {
        board = new SudokuBoard();
        solver = new BacktrackingSudokuSolver();
    }

    private int generateRandomSudokuIndex() {
        return (int) (Math.random() * (MAX_SUDOKU_INDEX - MIN_SUDOKU_INDEX)) + MIN_SUDOKU_INDEX;
    }

    private int generateRandomNumber() {
        int range = (MAX_SUDOKU_VALUE - MIN_SUDOKU_VALUE) + 1;
        return (int) (Math.random() * range) + MIN_SUDOKU_VALUE;
    }

    @Test
    public void checkBoardTestOne() {
        boolean result;
        result = board.checkBoardResult(generateRandomSudokuIndex(), generateRandomSudokuIndex(), generateRandomNumber());
        assertSame(result, true);
    }

    @Test
    public void checkBoardTestTwo() {
        solver.solve(board);
        boolean result = false;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!board.checkBoardResult(i, j, generateRandomNumber())) { // kazda liczba od 1 do 9 jest juz w rzedach, kolumnach i boxach -> false
                    result = false;
                }
            }
        }
        assertEquals(result, false);
    }
}