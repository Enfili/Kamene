package test;

import core.Field;
import core.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolvedTest {
    private Field field;

    @BeforeEach
    public void initTest() {
        field = new Field(4, 4);
    }

    @Test
    public void checkIsSolve() {
        assertEquals(true, field.isSolved(), "Wrongly generated field.");
        swapStones(field.getStones()[field.getRowCount() - 2][field.getColumnCount() - 1]);
        assertEquals(false, field.isSolved(), "Empty stone is not the last one");
        swapStones(field.getStones()[field.getRowCount() - 1][field.getColumnCount() - 1]);
        assertEquals(true, field.isSolved(), "After solving the field, it returns false.");
    }

    private void swapStones(Stone stone) {
        field.getStones()[stone.getRow()][stone.getColumn()] = null;
        field.getStones()[field.getEmptyStoneRow()][field.getEmptyStoneColumn()] = stone;
        int newEmptyRow = stone.getRow();
        int newEmptyColumn = stone.getColumn();
        stone.setRow(field.getEmptyStoneRow());
        stone.setColumn(field.getEmptyStoneColumn());
        field.setEmptyStoneRow(newEmptyRow);
        field.setEmptyStoneColumn(newEmptyColumn);
    }
}
