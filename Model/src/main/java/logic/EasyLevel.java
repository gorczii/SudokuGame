package logic;

public class EasyLevel implements Level{
    private final int numberOfFieldsToRemove = 40;
    private final int MAX_SUDOKU_VALUE = 9;
    private final int MIN_SUDOKU_VALUE = 1;
    private final int EMPTY_VALUE = 0;
    
    @Override
    public SudokuBoard prepareEmptyFields(SudokuBoard board) {
        int temp = 0;
        int row, column;
        
        while (temp < numberOfFieldsToRemove) {
            row = generateRandomNumber() - 1;
            column = generateRandomNumber() - 1;
            if (board.getBoardElement(row, column).getFieldValue() != EMPTY_VALUE) {
                board.setEmptyValue(row, column);
            }
            temp++;
        }
        return board;
    }
    
    private int generateRandomNumber() {
        int range = (MAX_SUDOKU_VALUE - MIN_SUDOKU_VALUE) + 1;
        return (int) (Math.random() * range) + MIN_SUDOKU_VALUE;
    }
}
