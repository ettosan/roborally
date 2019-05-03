package inf112.skeleton.app;

import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.board.MovementBoardElement;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MovementBoardElementTest {
    private MovementBoardElement testMovementBoardElement;
    private int[] coordinates = {1, 2};
    private Direction direction = Direction.SOUTH;
    private int moveValue = 2;
    private boolean rotationDirection = true;

    @Before
    public void setup() {
        testMovementBoardElement = new MovementBoardElement(coordinates, direction, moveValue, rotationDirection);

    }
    @Test
    public void coordinatesTest(){
        assertEquals(coordinates,testMovementBoardElement.getCoordinates());
    }

    @Test
    public void directionTest(){
        assertEquals(direction,testMovementBoardElement.getDirection());
    }
    @Test
    public void moveValueTest(){
        assertEquals(moveValue,testMovementBoardElement.getMoveValue());
    }
    @Test
    public void rotationdirectiontest(){
        int[] testPosition = new int[2];
        testPosition [0] = 2;
        testPosition [1] = 2;
        assert (testMovementBoardElement.getRotationDirection());


    }

}
