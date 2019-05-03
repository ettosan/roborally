package inf112.skeleton.app.robot;

import inf112.skeleton.app.board.IProgramRegister;

/**
 * Chooses random cards, never powers down
 */
public class SimpleBraveAI extends SimpleAI{
    @Override
    public void decideIfPowerDown(IProgramRegister register) {
        //Does nothing, because the AI is never supposed to power down
    }
}
