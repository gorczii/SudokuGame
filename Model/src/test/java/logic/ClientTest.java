package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    private Client client;
    private  BacktrackingSudokuSolver solver;
    private SudokuBoard board;
    private SudokuBoardDaoFactory factory;

    @Before
    public void initObjects() {
        client = new Client();
        board = new SudokuBoard();
        solver = new BacktrackingSudokuSolver();
        factory = new SudokuBoardDaoFactory();
    }

    @Test
    public void writeTest() {
        solver.solve(board);
        factory.getFileDao("test.txt").write(board);
        SudokuBoard newBoard = (SudokuBoard) factory.getFileDao("test.txt").read();
        assertEquals(board, newBoard);

    }
}
