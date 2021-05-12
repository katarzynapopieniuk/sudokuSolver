import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveToNextCellMethodTest {

    @Test
    void whenItsNotLastPointInRowNextPointInSameRowShouldBeChosen() throws ElementNotExistingException {
        CellCoordinates coordinates = new CellCoordinates(2,3);
        CellIterator.moveToNextCell(coordinates);
        assertEquals(2, coordinates.getRow());
        assertEquals(4, coordinates.getColumn());
    }

    @Test
    void whenItsLastInRowFirstPointInNextRowShouldBeChosen() throws ElementNotExistingException {
        CellCoordinates coordinates = new CellCoordinates(2,8);
        CellIterator.moveToNextCell(coordinates);
        assertEquals(3, coordinates.getRow());
        assertEquals(0, coordinates.getColumn());
    }

    @Test
    void whenItsLastPointExceptionShouldBeThrown() {
        CellCoordinates coordinates = new CellCoordinates(8,8);
        assertThrows(ElementNotExistingException.class, () -> CellIterator.moveToNextCell(coordinates));
    }
}