package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.card.ICard;
import inf112.skeleton.app.card.MovementCard;
import inf112.skeleton.app.robot.*;
import inf112.skeleton.app.robot.Robot;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
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

    @Test
    public void rotateRightTest () {

        robot.setDir(Direction.NORTH);

        robot.rotate(true);

        assertEquals(robot.getDir(),Direction.EAST);

    }

    @Test
    public void rotateLeftTest () {

        robot.setDir(Direction.SOUTH);

        robot.rotate(false);

        assertEquals(robot.getDir(),Direction.EAST);

    }


    @Test
    public void equalsFail () {

        ICard card = new MovementCard(560,2);

        assertFalse(robot.equals(card));

    }

    @Test
    public void equalsTest () {

        int [] poisition = {3,4};
        IRobot robotManual = new Robot(1,poisition);

        assertTrue(robot.equals(robotManual));

    }



    @Test
    public void backupTest () {

    int [] listOfBackup = {1,2,3,4,5,6};

    robot.setBackup(listOfBackup);

    assertEquals(listOfBackup,robot.getBackup());

    }

    @Test
    public void positionTest () {
        int [] position = {2,3};
        robot.setPosition(position);

        assertEquals(position,robot.getPosition());

    }

    @Test
    public void hashCodeTest () {

    }

}