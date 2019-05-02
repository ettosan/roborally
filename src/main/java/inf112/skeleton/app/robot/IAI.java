package inf112.skeleton.app.robot;

import inf112.skeleton.app.board.IProgramRegister;

/**
 * Interface for an AI class
 */
public interface IAI {
    /**
     * The AI decides whether it should power down, and performs a power down if it wants to
     * @param register the register to be judged
     */
    public void decideIfPowerDown(IProgramRegister register);

    /**
     * The AI activates the cards it thinks is best
     * @param register the register to be judged
     */
    public void activateCards(IProgramRegister register);
}
