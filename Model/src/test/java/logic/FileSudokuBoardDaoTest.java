package logic;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class FileSudokuBoardDaoTest {
    private FileSudokuBoardDao file;
    private SudokuBoard board;

    @Before
    public void initObjects() {
        board = new SudokuBoard();
    }

    @Test
    public void readTest() {
        file = new FileSudokuBoardDao("aaa.txt");
        assertSame(null, file.read());
    }
}
