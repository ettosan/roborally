package inf112.skeleton.app.board.conveyorBelts;

import inf112.skeleton.app.board.ConveyorBelts.ConveyorInto;
import inf112.skeleton.app.board.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ConveyorIntoTest {
    private ConveyorInto testConveyorInto ;
    private int moveValue = 2;
    private Direction dir = Direction.NORTH;
    private int[] position = {1,2};
    private boolean turnDirection = true;

    @Before
    public void setup (){

        testConveyorInto = new ConveyorInto(dir,moveValue,position,turnDirection);
    }

    @Test
    public void DirectionTest () {
        assertEquals(dir, testConveyorInto.getDirection());
    }

    @Test
    public void positionTest(){
        assertEquals(position,testConveyorInto.getPosition());
    }


    @Test
    public void turnDirectionTest(){
        assert(testConveyorInto.getTurnDirection());


    }
    @Test
    public void expresstypeTest(){
        assert(testConveyorInto.isExpressType());

    }
    @Test
    public void shouldrotateTest() {
        int[] testPosition = new int[2];
        testPosition[0] = 2;
        testPosition[1] = 2;
        assert (testConveyorInto.shouldRotate(testPosition));
    }

}
