import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetValueTest {
    @Test
    void whenCalledWithIllegalParametersExceptionShouldBeThrown() {
        SudokuGrid grid = new SudokuGrid();
        assertThrows(InvalidCoordinateException.class, () -> grid.getValue(2, 10));
    }
}