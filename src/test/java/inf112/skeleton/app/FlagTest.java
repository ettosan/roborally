package inf112.skeleton.app;

import inf112.skeleton.app.board.Flag;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FlagTest {
    private Flag testFlag;
    private int flagId;
    private int x = 0;
    private int y = 1;

    @Before
    public void setup(){ testFlag = new Flag(flagId, x, y); }

    @Test
    public void flagIdTest(){assertEquals(flagId,testFlag.getFlagId());
    }
    @Test
    public void xTest(){assertEquals(x,testFlag.getPosition()[0]);
    }
    @Test
    public void yTest(){assertEquals(y,testFlag.getPosition()[1]);
    }

}

