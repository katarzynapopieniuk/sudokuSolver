import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetValueTest {
    @Test
    void whenCalledWithCoordinatesOutOfRangeExceptionShouldBeThrown() {
        SudokuGrid grid = new SudokuGrid(2);
        assertThrows(InvalidCoordinateException.class, () -> grid.setValue(3,1,1));
    }

    @Test
    void whenCalledWithIllegalValueExceptionShouldBeThrown() {
        SudokuGrid grid = new SudokuGrid(3);
        assertThrows(IllegalArgumentException.class, () -> grid.setValue(2,1,5));
    }

    @Test
    void whenCalledWithCorrectParametersValueShouldBeSet() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid(9);
        grid.setValue(3,4,5);
        assertEquals(5, grid.getValue(3,4));
    }
}