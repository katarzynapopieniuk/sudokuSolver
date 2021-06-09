import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {

    public int[][] board1;
    public SudokuSolverTest() {
        board1 = new int[][] {
                {8, 1, 2, 7, 5, 3, 6, 4, 9},
                {9, 4, 3, 6, 8, 2, 1, 7, 5},
                {6, 7, 5, 4, 9, 1, 2, 8, 3},
                {1, 5, 4, 2, 3, 7, 8, 9, 6},
                {3, 6, 9, 8, 4, 5, 7, 2, 1},
                {2, 8, 7, 1, 6, 9, 5, 3, 4},
                {5, 2, 1, 9, 7, 4, 3, 6, 8},
                {4, 3, 8, 5, 2, 6, 9, 1, 7},
                {7, 9, 6, 3, 1, 8, 4, 5, 2},
        };
    }

    int[][] board2 = {
            {0, 1, 2, 7, 5, 3, 6, 4, 9},
            {9, 4, 3, 0, 8, 2, 1, 7, 5},
            {6, 7, 5, 4, 9, 1, 2, 8, 3},
            {1, 5, 4, 2, 3, 7, 8, 9, 6},
            {3, 6, 9, 8, 4, 5, 7, 2, 1},
            {2, 0, 7, 1, 6, 9, 5, 3, 4},
            {5, 2, 1, 9, 7, 4, 3, 6, 8},
            {4, 3, 8, 5, 2, 6, 9, 0, 7},
            {7, 9, 6, 3, 1, 8, 4, 5, 2},
    };

    int[][] board3 = {
            {0, 0, 0, 0, 5, 3, 6, 4, 9},
            {9, 4, 3, 6, 8, 2, 1, 7, 5},
            {6, 7, 5, 4, 9, 1, 2, 8, 3},
            {1, 5, 4, 2, 3, 7, 8, 9, 6},
            {3, 6, 9, 8, 4, 5, 7, 2, 1},
            {2, 8, 7, 1, 6, 9, 5, 3, 4},
            {5, 2, 1, 9, 7, 4, 3, 6, 8},
            {4, 3, 8, 5, 2, 6, 9, 1, 7},
            {7, 9, 6, 3, 1, 8, 4, 5, 2},
    };

    int[][] board4 = {
            {8, 1, 2, 0, 0, 0, 0, 0, 0},
            {9, 4, 3, 0, 0, 0, 0, 0, 0},
            {6, 7, 5, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    @Test
    void whenPointIsFilledTrueShouldBeReturned() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid);
        grid.setValue(0,0,1);
        assertTrue(solver.isPointFilled());
    }

    @Test
    void whenPointIsNotFilledFalseShouldBeThrown() {
        SudokuGrid grid = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid);
        assertFalse(solver.isPointFilled());
    }

    @Test
    void whenFindNextNotFilledPointMethodIsUsedItShouldFindNotFilledPoint() throws InvalidCoordinateException, ElementNotExistingException {
        SudokuGrid grid = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid);
        grid.setValue(0,0,1);
        grid.setValue(0,1,2);
        solver.findNextEmptyCell();
        assertEquals(0, solver.getCurrentRow());
        assertEquals(2, solver.getCurrentColumn());
    }

    @Test
    void whenFindNextNotFilledPointMethodIsUsedBuThereIsNoNextEmptyPointExceptionShouldBeThrown() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid);
        grid.setValue(8,7,1);
        grid.setValue(8,8,2);
        solver.setCurrentColumn(7);
        solver.setCurrentRow(8);
        assertThrows(ElementNotExistingException.class, () -> solver.findNextEmptyCell());
    }

    @Test
    void whenFindPreviousNotFilledPointMethodIsUsedItShouldFindNotFilledPoint() throws InvalidCoordinateException, ElementNotExistingException {
        SudokuGrid grid = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid);
        grid.setValue(5,5,1);
        grid.setValue(5,6,2);
        solver.setCurrentRow(5);
        solver.setCurrentColumn(6);
        solver.findPreviousEmptyCell();
        assertEquals(5, solver.getCurrentRow());
        assertEquals(4, solver.getCurrentColumn());
    }

    @Test
    void whenFindPreviousNotFilledPointMethodIsUsedBuThereIsNoPreviousEmptyPointExceptionShouldBeThrown() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid);
        grid.setValue(0,0,1);
        grid.setValue(0,1,2);
        solver.setCurrentColumn(2);
        assertThrows(ElementNotExistingException.class, () -> solver.findPreviousEmptyCell());
    }

    @Test
    void whenIsPointValidMethodIsCalledWithCorrectParametersShouldReturnTrue() {
        assertTrue(SudokuGrid.isPointValid(5,6));
        assertTrue(SudokuGrid.isPointValid(0,0));
        assertTrue(SudokuGrid.isPointValid(8,8));
        assertTrue(SudokuGrid.isPointValid(3,8));
    }

    @Test
    void whenIsPointValidMethodIsCalledWithWrongParametersShouldReturnFalse() {
        assertFalse(SudokuGrid.isPointValid(0,9));
        assertFalse(SudokuGrid.isPointValid(-1,9));
        assertFalse(SudokuGrid.isPointValid(9,0));
    }

    @Test
    void whenIsValueValidMethodIsCalledWithCorrectParametersShouldReturnTrue() {
        assertTrue(SudokuGrid.isValueValid(1));
        assertTrue(SudokuGrid.isValueValid(5));
        assertTrue(SudokuGrid.isValueValid(8));
    }

    @Test
    void whenIsValueValidMethodIsCalledWithWrongParametersShouldReturnFalse() {
        assertFalse(SudokuGrid.isValueValid(0));
        assertFalse(SudokuGrid.isValueValid(10));
    }

    @Test
    void whenDoesValueMatchMethodIsCalledWithMatchingParameterTrueShouldBeReturned() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuGrid workSheet = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid);
        workSheet.setValue(0,0,1);
        workSheet.setValue(0,1,2);
        workSheet.setValue(1,3,6);
        solver.setCurrentRow(1);
        solver.setCurrentColumn(1);
        assertTrue(solver.doesValueMatch(3));
        assertTrue(solver.doesValueMatch(7));
        assertTrue(solver.doesValueMatch(9));
    }

    @Test
    void whenDoesValueMatchMethodIsCalledWithNotMatchingParameterFalseShouldBeReturned() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuGrid workSheet = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid, workSheet);
        workSheet.setValue(0,0,1);
        workSheet.setValue(0,1,2);
        workSheet.setValue(1,3,6);
        solver.setCurrentRow(1);
        solver.setCurrentColumn(1);
        assertFalse(solver.doesValueMatch(1));
        assertFalse(solver.doesValueMatch(2));
        assertFalse(solver.doesValueMatch(6));
    }

    @Test
    void whenTryToFillPointMethodIsUsedItShouldFillPointWithLowestPossibleValue() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuGrid workSheet = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid, workSheet);
        workSheet.setValue(0,0,1);
        workSheet.setValue(0,1,2);
        workSheet.setValue(1,3,6);
        solver.setCurrentRow(1);
        solver.setCurrentColumn(1);
        assertTrue(solver.tryToFillCell());
        assertEquals(3, workSheet.getValue(1,1));
    }

    @Test
    void whenTryToFillPointMethodIsUsedAndGridIsEmptyItShouldFillPointWith1() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuGrid workSheet = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid, workSheet);
        assertTrue(solver.tryToFillCell());
        assertEquals(1, workSheet.getValue(0,0));
    }

    @Test
    void whenTryToFillPointMethodIsUsedButThereIsAlreadyValueItShouldFillPointWithNextLowestPossibleValue() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuGrid workSheet = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid, workSheet);
        workSheet.setValue(0,0,1);
        workSheet.setValue(0,1,2);
        workSheet.setValue(1,3,6);
        solver.setCurrentRow(1);
        solver.setCurrentColumn(1);
        solver.tryToFillCell();
        assertTrue(solver.tryToFillCell());
        assertEquals(4, workSheet.getValue(1,1));
    }

    @Test
    void whenTryToFillPointMethodIsUsedButThereIsNoMatchValueFalseShouldBeReturned() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuGrid workSheet = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid, workSheet);
        workSheet.setValue(0,0,1);
        workSheet.setValue(0,1,2);
        workSheet.setValue(1,2,3);
        workSheet.setValue(1,3,4);
        workSheet.setValue(1,4,5);
        workSheet.setValue(1,5,6);
        workSheet.setValue(1,6,7);
        workSheet.setValue(1,7,8);
        workSheet.setValue(1,8,9);
        solver.setCurrentRow(1);
        solver.setCurrentColumn(1);
        assertFalse(solver.tryToFillCell());
    }

    @Test
    void whenTryToFillPointMethodIsUsedButThereIsAlreadyAValueAndNoBiggerMatchValueFalseShouldBeReturned() throws InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid();
        SudokuGrid workSheet = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver(grid, workSheet);
        workSheet.setValue(0,0,1);
        workSheet.setValue(0,1,2);
        workSheet.setValue(1,2,3);
        workSheet.setValue(1,3,4);
        workSheet.setValue(1,5,6);
        workSheet.setValue(1,6,7);
        workSheet.setValue(1,7,8);
        workSheet.setValue(1,8,9);
        solver.setCurrentRow(1);
        solver.setCurrentColumn(1);
        workSheet.setValue(1,1,5);
        assertFalse(solver.tryToFillCell());
    }

    @Test
    void whenSudokuIsSolvedTrueShouldBeRetuned() throws InvalidGridSizeException {
        SudokuGrid grid = new SudokuGrid(board1);
        SudokuSolver solver = new SudokuSolver(grid);
        assertTrue(solver.trySolve());
    }

    @Test
    void whenSudokuPossibleToSolveIsGivenItShouldBeSolved() throws InvalidGridSizeException {
        SudokuGrid grid = new SudokuGrid(board2);
        SudokuSolver solver = new SudokuSolver(grid);
        assertTrue(solver.trySolve());

        SudokuGrid grid1 = new SudokuGrid(board3);
        SudokuSolver solver1 = new SudokuSolver(grid1);
        assertTrue(solver1.trySolve());
    }

    @Test
    void whenValueIsInCurrentSubsquareTrueShouldBeReturned() throws InvalidGridSizeException, InvalidCoordinateException {
        SudokuGrid grid = new SudokuGrid(board4);
        SudokuSolver solver = new SudokuSolver(grid);
        for(int i=1; i<=9; i++)
            assertTrue(solver.isValueInCurrentSubsquare(i));
    }

    @Test
    void whenValueIsntInCurrentSubsquareFalseShouldBeReturned() throws InvalidCoordinateException, InvalidGridSizeException {
        SudokuGrid grid = new SudokuGrid(board4);
        SudokuSolver solver = new SudokuSolver(grid);
        solver.setCurrentColumn(4);
        for(int i=1; i<=9; i++)
            assertFalse(solver.isValueInCurrentSubsquare(i));
    }
}