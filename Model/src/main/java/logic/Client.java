package logic;

/**
 * Created by jusia on 19.04.2017.
 */
public class Client {

    public static void main(String[] args) {
        Client one = new Client();
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        factory.getFileDao("test.txt").write(board);
        SudokuBoard newBoard = (SudokuBoard) factory.getFileDao("test.txt").read();
        System.out.println(board);
        System.out.println(newBoard);


    }
}
