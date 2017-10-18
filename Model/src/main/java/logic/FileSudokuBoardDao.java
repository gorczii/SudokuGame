package logic;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> { //nothing about AutoCloseable because streams already implement this interface and are autocloseable
    private String filename;

    public FileSudokuBoardDao(final String filename) {
        this.filename = filename;
    }

    public String getFileName() {
        return this.filename;
    }

    public SudokuBoard read() {
       try (FileInputStream file = new FileInputStream(filename);
            ObjectInputStream input = new ObjectInputStream(file)) {
            SudokuBoard newSudoku = (SudokuBoard) input.readObject();
            return newSudoku;
        } catch (IOException | ClassNotFoundException cnfe) {
           System.out.println(cnfe);
           return null;
       }

    }

    public void write(SudokuBoard board) {
     try (FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(board);
        } catch (IOException ioe) {
            System.out.println(ioe);
       }
   }

   @Override
   public void finalize() throws Throwable {
       super.finalize();
   }

}
