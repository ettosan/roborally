package inf112.skeleton.app;

import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.robot.*;
import inf112.skeleton.app.robot.Robot;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class RobotTest {
    IRobot robot;
    @Before
    public void initialize(){
        int[] testCoordinates = {1,1};
        robot = new Robot(1, testCoordinates);
    }


    @Test
    public void getDirTrue(){
        robot.setDir(Direction.EAST);
        assertEquals("E", robot.getDir().getSymbol());
        robot.setDir(Direction.SOUTH);
        assertEquals("S",robot.getDir().getSymbol());

    }
    @Test
    public void getDirFails(){
        assertFalse("W".equals(robot.getDir().getSymbol()));
    }

    @Test
    public void getIdTrue(){
        assertEquals(1,robot.getID());
    }

    @Test
    public void getIdFails(){
        assertFalse(2 == robot.getID());
    }

}