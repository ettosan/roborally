package inf112.skeleton.app;

import inf112.skeleton.app.board.Direction;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DirectionTest {
    private Direction testDirection;
    private String symbol = "E";
    private int directionValue = 0;
    private int dx = 0;
    private int dy = 0;




    @Test
    public void stringTest() { assertEquals(symbol,Direction.EAST.getSymbol());
    }
    @Test
    public void directionValueTest() {assertEquals(directionValue,Direction.NORTH.getDirectionValue());
    }
    @Test
    public void deltaXTest() {assertEquals(dx,Direction.SOUTH.getDeltaX());
    }
    @Test
    public void deltaYTest() {assertEquals(dy,Direction.WEST.getDeltaY());
    }

}
