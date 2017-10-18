package logic;
import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuFieldTest {
    
    @Test
    public void compareToTest1() {
        boolean thrown = false;
        SudokuField field = null;
        SudokuField instance = new SudokuField();
        try {
            instance.compareTo(field);
        }
        catch (NullPointerException e) {
            thrown = true;
        }
        assertEquals(true, thrown); //NullPointerException was thrown
    }
    
    @Test
    public void compareToTest2() {
        SudokuField one = new SudokuField();
        one.setFieldValue(1);
        SudokuField two = new SudokuField();
        two.setFieldValue(2);
        boolean result = false;
         
        if (one.compareTo(two) > 0) {
            result = true;
        }

        assertEquals(true, result);
    }

    @Test
    public void compareToTest3() {
        SudokuField one = new SudokuField();
        one.setFieldValue(1);
        SudokuField oone = new SudokuField();
        oone.setFieldValue(1);
         
        assertEquals(0, one.compareTo(oone));
    }
}
