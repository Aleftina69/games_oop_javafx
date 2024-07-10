package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> logic.move(Cell.C1, Cell.H6));
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveOccupiedCellException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A7));
        logic.add(new BishopBlack(Cell.B8));
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> logic.move(Cell.A7, Cell.B8));
        assertThat(exception.getMessage()).isEqualTo("Cell is occupied.");
    }

    @Test
    public void whenMoveImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A2));
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.A2, Cell.A4));
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from %s to %s", Cell.A2, Cell.A4);
    }
}