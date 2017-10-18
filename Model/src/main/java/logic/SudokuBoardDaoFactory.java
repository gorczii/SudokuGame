package logic;

/**
 * Created by jusia on 19.04.2017.
 */
public class SudokuBoardDaoFactory {
    public Dao getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }
}
