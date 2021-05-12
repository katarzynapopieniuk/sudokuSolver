import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetValueTest {
    @Test
    void whenCalledWithCoordinatesOutOfRangeExceptionShouldBeThrown() {
        SudokuGrid grid = new SudokuGrid();
        assertThrows(InvalidCoordinateException.class, () -> grid.setValue(10,1,1));
    }

    @Test
    void whenCalledWithIllegalValueExceptionShouldBeThrown() {
        SudokuGrid grid = new SudokuGrid();
        assertThrows(IllegalArgumentException.class, () -> grid.setValue(2,1,16));
    }

    @Test
    void whenCalledWithCorrectParametersValueShouldBeSet() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        grid.setValue(3,4,5);
        assertEquals(5, grid.getValue(3,4));
    }
}