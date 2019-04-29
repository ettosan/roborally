package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.card.ICardMovement;
import inf112.skeleton.app.card.ICardRotation;
import inf112.skeleton.app.card.MovementCard;
import inf112.skeleton.app.card.RotationCard;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.robot.IRobot;
import inf112.skeleton.app.robot.Robot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    /**

    private IRobot robot;
    private Game game;
    private ICardMovement moveTwoSteps;
    private ICardMovement moveOneStep;
    private ICardMovement moveThreeSteps;
    private ICardMovement moveOneBackwards;
    private ICardRotation moveRight;
    private ICardRotation moveLeft;
    private ICardRotation uTurn;

    @Before
    public void setUp() {
        int[] testCoordinates = {0,0};
        robot = new Robot(0,testCoordinates);
        robot.setDir(Direction.EAST);
        TiledMap map = new TiledMap();
        game = new Game(map, 1);
    }
+
    @Test
    public void testMoveTwoSteps() {
        moveTwoSteps = new MovementCard(1,2);
        game.relativeMove(robot, moveTwoSteps);
        robot.setPosition(robot.getPosition());
        int[] robotPos = robot.getPosition();
        assertTrue(robot.getPosition().equals(robotPos[0] == 0 && robotPos[1] == 2));
    }

    @Test
    public void testMoveOneStep() {
        moveThreeSteps = new MovementCard(3,1);
        game.relativeMove(robot, moveThreeSteps);
        robot.setPosition(robot.getPosition());
        int[] robotPos = robot.getPosition();
        assertTrue(robot.getPosition().equals(robotPos[0] == 0 && robotPos[1] == 3));
    }


    @Test
    public void testMoveThreeStep() {
        moveOneStep = new MovementCard(2,3);
        game.relativeMove(robot, moveOneStep);
        robot.setPosition(robot.getPosition());
        int[] robotPos = robot.getPosition();
        assertTrue(robot.getPosition().equals(robotPos[0] == 0 && robotPos[1] == 1));
    }

    @Test
    public void testMoveOneBackwards() {
        moveOneBackwards = new MovementCard(1,-1);
        game.relativeMove(robot, moveOneBackwards);
        robot.setPosition(robot.getPosition());
        int[] robotPos = robot.getPosition();
        assertTrue(robot.getPosition().equals(robotPos[0] == 0 && robotPos[1] == -1));
    }

    @Test
    public void testRotateRight() {
        System.out.println("Robot has direction " + robot.getDir() + " before rotate-right-card.");
        moveRight = new RotationCard(2,true,1);
        game.rotationMove(robot, moveRight);

        System.out.println("Robot has direction " + robot.getDir() + " after rotate-right-card.");
        assertEquals(robot.getDir(), Direction.SOUTH);
    }

    @Test
    public void testRotateLeft() {
        System.out.println("Robot has direction " + robot.getDir() + " before rotate-left-card.");
        moveLeft = new RotationCard(1,false,1);
        game.rotationMove(robot, moveLeft);

        System.out.println("Robot has direction " + robot.getDir() + " after rotate-left-card.");
        assertEquals(robot.getDir(), Direction.NORTH);
    }

    @Test
    public void testUTurn() {
        System.out.println("Robot has direction " + robot.getDir() + " before u-turn-card.");
        uTurn = new RotationCard(1,true,2);
        game.rotationMove(robot, uTurn);

        System.out.println("Robot has direction " + robot.getDir() + " after u-turn-card.");
        assertEquals(robot.getDir(), Direction.WEST);
    }
*/
}