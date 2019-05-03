package inf112.skeleton.app;

import inf112.skeleton.app.board.BoardElement;
import inf112.skeleton.app.board.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardElementTest {

    @Test
    public void getDirectionTest () {
        Direction directionFromMethod = BoardElement.WALL_EAST.getDirection();


        Direction direction = Direction.EAST;

        assertEquals(direction,directionFromMethod);
    }

    @Test
    public void getValueTest() {
        int valueFromMethod = BoardElement.WALL_EAST.getValue();
        int value = -1;

        assertEquals(value,valueFromMethod);

    }

}
