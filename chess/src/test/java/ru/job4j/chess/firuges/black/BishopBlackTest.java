package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopBlackTest {

    @Test
    public void whenPosition() {
        Cell start = Cell.findBy(3, 6);
        BishopBlack bishopBlack = new BishopBlack(start);
        Cell finish = bishopBlack.position();
        assertThat(finish).isEqualTo(start);
    }

    @Test
    public void whenWay() {
        Cell start = Cell.findBy(2, 0);
        BishopBlack bishopBlack = new BishopBlack(start);
        Cell dest = Cell.findBy(5, 3);
        Cell[] steps = bishopBlack.way(dest);
        assertThat(steps).containsExactly(
                Cell.findBy(3, 1),
                Cell.findBy(4, 2),
                Cell.findBy(5, 3)
        );
    }

    @Test
    public void whenCopy() {
        Cell finish = Cell.findBy(5, 8);
        BishopBlack bishopBlackCopy = new BishopBlack(finish);
        assertThat(bishopBlackCopy.position()).isEqualTo(finish);
    }

    @Test
    public void whenWayNotDiagonalThenImpossibleMoveException() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () ->
                bishop.way(Cell.C2));
        assertEquals("Could not move by diagonal from C1 to C2",
                exception.getMessage()
        );
    }
}