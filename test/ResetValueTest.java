import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResetValueTest {
    @Test
    void whenValueIsResetItShouldBeNoValue() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        grid.setValue(1,1,1);
        grid.resetCell(1,1);
        assertEquals(Constants.NO_VALUE, grid.getValue(1,1));
    }
}