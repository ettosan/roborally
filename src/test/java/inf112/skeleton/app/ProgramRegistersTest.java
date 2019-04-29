
package inf112.skeleton.app;

import inf112.skeleton.app.board.IProgramRegister;
import inf112.skeleton.app.board.ProgramRegister;
import inf112.skeleton.app.robot.IRobot;
import inf112.skeleton.app.robot.Robot;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 * Created by mari on 08.03.2019.
 */
public class ProgramRegistersTest {
    IRobot robot;
    IProgramRegister pR;

    @Before
    public void initialize(){
        int[] testCoordinates = {1,1};
        robot = new Robot(1,testCoordinates);
        pR = new ProgramRegister(robot);
    }

    @Test
    public void isCardSlotsFilledTest(){
        assertEquals(false, pR.isCardSlotsFilled());


        assertFalse(true == pR.isCardSlotsFilled());

    }


    @Test
    public void powerDownTest() {
        assertEquals(false, pR.isPoweredDown());
        pR.powerDown();
        assertEquals(true, pR.isPoweredDown());
        assertFalse(false == pR.isPoweredDown());
    }
    @Test
    public void LivesTests(){
        assertEquals(3, pR.getLives());
        pR.removeLife();
        pR.removeLife();
        assertEquals(1, pR.getLives());
    }

    @Test
    public void amountOfDamageTest(){
        assertEquals(0, pR.getDamage());
        pR.changeDamage(4);
        assertEquals(4, pR.getDamage());
        assertFalse(9 == pR.getDamage());
    }
}