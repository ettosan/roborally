package inf112.skeleton.app.board.conveyorBelts;

import inf112.skeleton.app.board.IProgramRegister;
import inf112.skeleton.app.board.ProgramRegister;
import inf112.skeleton.app.robot.*;
import org.junit.Test;
import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.card.ICard;
import inf112.skeleton.app.card.MovementCard;
import inf112.skeleton.app.robot.*;
import inf112.skeleton.app.robot.Robot;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class AITest {

    private int [] position = {2,3};
    private IRobot robot = new Robot(2,position);
    private IAI simpleComputer = new SimpleAI();
    private IAI advancedComputer = new SimpleBraveAI();
    private IProgramRegister programRegister = new ProgramRegister(robot,false);

    @Test
    public void decideIfSimplePowerDownTest () {


    }

    @Test
    public void activateCardsTest () {

    }

    @Test
    public void decideIfAdvancedPowerDownTest () {

        advancedComputer.decideIfPowerDown(programRegister);


    }
}
