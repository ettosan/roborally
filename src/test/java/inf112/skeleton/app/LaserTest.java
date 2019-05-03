package inf112.skeleton.app;

import inf112.skeleton.app.board.Laser;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class LaserTest {
    private Laser testLaser1;
    private Laser testLaser2;
    private int x =  0;
    private int y =  1;
    private String direction = "horizontal";
    private boolean isVertical = true;
    private int damage = 2;


    @Test
    public void DamageTest(){

        testLaser1 = new Laser(damage,isVertical,x,y);
        testLaser2 = new Laser(damage,direction,x,y);

        assertEquals(damage,testLaser1.getDamage());
        assertEquals(damage,testLaser2.getDamage());
    }

    @Test
    public void isVerticalTest (){
        testLaser1 = new Laser(damage,isVertical,x,y);
        assertTrue(testLaser1.isVertical());
    }

    @Test
    public void getPositionTest() {
        testLaser1 = new Laser(damage,isVertical,x,y);
       int [] positionFromMethod = testLaser1.getPosition();
       int [] position = {0,1};

       assertEquals(position[1],positionFromMethod[1]);
        assertEquals(position[0],positionFromMethod[0]);
    }
}
