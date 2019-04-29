package inf112.skeleton.app.board.conveyorBelts;

import inf112.skeleton.app.board.ConveyorBelts.ConveyorTurn;
import inf112.skeleton.app.board.Direction;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ConveyorTurnTest {
    private ConveyorTurn testConveyorTurn;
    private Direction dir  = Direction.NORTH;
    private int moveValue = 2;
    private int [] position = {1, 2};
    private boolean turnDirection = true;

    @Before
    public void setup(){
        testConveyorTurn = new ConveyorTurn(dir, moveValue, position, turnDirection);

    }
    @Test
    public void DirectionTest() {assertEquals(dir,testConveyorTurn.getDirection());
    }

    @Test
    public void positionTest(){ assertEquals(position,testConveyorTurn.getPosition());}


    @Test
    public void expressTypeTest(){
        assert(testConveyorTurn.isExpressType());
    }
    @Test
    public void getTurnDirectionTest(){
        int[] testPosition = new int[2];
        testPosition[0] = 2;
        testPosition[1] = 2;
        assert (testConveyorTurn.getTurnDirection());
    }



}
