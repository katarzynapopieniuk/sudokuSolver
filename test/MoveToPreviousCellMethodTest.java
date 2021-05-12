import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveToPreviousCellMethodTest {

    CellCoordinates notFirstInRowCell = new CellCoordinates(2,3);
    CellCoordinates firstInRowCell = new CellCoordinates(1,0);

    @Test
    void MovingFromNotFirstInRowDecrementsCoordinatesColumn() throws ElementNotExistingException {
        CellIterator.moveToPreviousCell(notFirstInRowCell);
        assertEquals(2, notFirstInRowCell.getColumn());
    }

    @Test
    void MovingFromNotFirstInRowDoNotUpdateCoordinatesRow() throws ElementNotExistingException {
        CellIterator.moveToPreviousCell(notFirstInRowCell);
        assertEquals(2, notFirstInRowCell.getRow());
    }

    @Test
    void MovingFromFirstInRowSetsCoordinatesColumnToLast() throws ElementNotExistingException {
        CellIterator.moveToPreviousCell(firstInRowCell);
        assertEquals(8, firstInRowCell.getColumn());
    }

    @Test
    void MovingFromFirstInRowDecrementsCoordinatesRow() throws ElementNotExistingException {
        CellIterator.moveToPreviousCell(firstInRowCell);
        assertEquals(0, firstInRowCell.getRow());
    }

    @Test
    void TryToMoveFromFirstCellThrowsException() {
        CellCoordinates coordinates = new CellCoordinates(0,0);
        assertThrows(ElementNotExistingException.class, () -> CellIterator.moveToPreviousCell(coordinates));
    }
}