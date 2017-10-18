package logic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Comparator;
//import java.lang.Comparable;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;
    
    @Override
    public boolean equals(Object newObject) {
        if (newObject == null) {
            return false;
        }
        if (!(newObject instanceof SudokuField)) {
            return false;
        }
        final SudokuField toCheck = (SudokuField) newObject;
        return Objects.equal(this.value, toCheck.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Value", value)
                .toString();
    }
    
    @Override
    public int compareTo(SudokuField field) {
        if (field == null) {
            throw new NullPointerException();
        } else {
            String stringField = field.toString();
            String thisString = this.toString();
            return stringField.compareTo(thisString); // jesli stringField > thisString to zwroci wieksze od 0, jesli = to 0, jesli < to mniejsze od 0
        }
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        SudokuField clone = (SudokuField) super.clone();
        return clone;
    }
    
    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }
}
