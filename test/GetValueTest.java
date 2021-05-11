import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetValueTest {
    @Test
    void whenCalledWithIllegalParametersExceptionShouldBeThrown() {
        SudokuGrid grid = new SudokuGrid(7);
        assertThrows(InvalidCoordinateException.class, () -> grid.getValue(2, 10));
    }
}